package com.credable.lms.service;

import com.credable.lms.domain.enumeration.LoanStatus;
import com.credable.lms.resource.errors.CustomerHasExistingLoanException;
import com.credable.lms.service.criteria.LoanCriteria;
import com.credable.lms.service.dto.CustomerDTO;
import com.credable.lms.service.dto.LoanDTO;
import com.credable.lms.service.dto.LoanRequestDTO;
import com.credable.lms.service.dto.SystemConfigDTO;
import com.credable.lms.service.helper.filter.LongFilter;
import com.credable.lms.service.mapper.CustomerMapper;
import com.credable.lms.service.scoringengine.ScoringEngineService;
import com.credable.lms.service.scoringengine.response.CustomerScoreResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;


@Service
@Transactional
public class LoanManagementService {

    private final Logger log = LoggerFactory.getLogger(LoanManagementService.class.getName());

    private final CustomerQueryService customerQueryService;
    private final LoanQueryService loanQueryService;
    private final SystemConfigQueryService systemConfigQueryService;
    private static final String KEY_SCORE_ENGINE_ACCESS_TOKEN = "SCORE_ENGINE_ACCESS_TOKEN";
    private final ScoringEngineService scoringEngineService;
    private final LoanService loanService;
    private final CustomerMapper customerMapper;
    private final SubscriptionService subscriptionService;

    public LoanManagementService(CustomerQueryService customerQueryService, LoanQueryService loanQueryService, SystemConfigQueryService systemConfigQueryService, ScoringEngineService scoringEngineService, LoanService loanService, CustomerMapper customerMapper, SubscriptionService subscriptionService) {
        this.customerQueryService = customerQueryService;
        this.loanQueryService = loanQueryService;
        this.systemConfigQueryService = systemConfigQueryService;
        this.scoringEngineService = scoringEngineService;
        this.loanService = loanService;
        this.customerMapper = customerMapper;
        this.subscriptionService = subscriptionService;
    }

    public LoanDTO applyForLoan(LoanRequestDTO loanRequest){

        Optional<CustomerDTO> customerOpt = customerQueryService.findByCustomerNumber(loanRequest.getCustomerNumber());

        //Get customer if exists or subscribe new customer
        CustomerDTO customerDTO = customerOpt.flatMap(c -> customerOpt).orElseGet(()-> subscriptionService.subscribe(loanRequest.getCustomerNumber()));

        if(checkIfCustomerEligibleForLoan(customerDTO)){
            //Save Pending Customer Loan
            LoanDTO loanDTO = saveCustomerLoan(customerDTO, Integer.parseInt(loanRequest.getLoanAmount()), LoanStatus.PENDING);
            //Get customers score
            CustomerScoreResponse customerScore = getCustomerScore(customerDTO.getCustomerNumber());

            //Check if customers limit is greater than loan request amount
            if(customerScore.getLimitAmount() >= Integer.parseInt(loanRequest.getLoanAmount())){
                //Update loan status to approved
                loanDTO.setLoanStatus(LoanStatus.APPROVED);
                loanDTO.setDisbursementDate(Instant.now());
                loanDTO.setApprovalDate(Instant.now());

            }else{
                //Update loan status to rejected
                loanDTO.setLoanStatus(LoanStatus.REJECTED);
                loanDTO.setRejectionDate(Instant.now());
                loanDTO.setRejectionReason("Customer did not have enough credit score");

            }
            return loanService.save(loanDTO);
        }else {
            LoanDTO loanDTO = getActiveLoanForCustomer(customerDTO).get();
            throw new CustomerHasExistingLoanException(String.format("Customer as a PENDING loan of %s ",loanDTO.getAmount()));
        }

    }

    //Fetch customer score from scoring engine
    private CustomerScoreResponse getCustomerScore(String customerNumber){
        Optional<SystemConfigDTO> clientAccessToken = systemConfigQueryService.findOneByCode(KEY_SCORE_ENGINE_ACCESS_TOKEN);
        String token = clientAccessToken.map(SystemConfigDTO::getValue).orElseGet(scoringEngineService::getClientAccessTokenFromMiddleware);
        return scoringEngineService.getCustomerScore(customerNumber,token);
    }

    private LoanDTO saveCustomerLoan(CustomerDTO customerDTO, int loanAmount, LoanStatus loanStatus){
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setCustomer(customerMapper.toEntity(customerDTO));
        loanDTO.setAmount(new BigDecimal(loanAmount));
        loanDTO.setLoanStatus(loanStatus);
        return loanService.save(loanDTO);
    }

    private Optional<LoanDTO> getActiveLoanForCustomer(CustomerDTO customerDTO){
        LoanCriteria criteria = getLoanCriteria(customerDTO);
        return loanQueryService.findOneCriteria(criteria);
    }

    private boolean checkIfCustomerEligibleForLoan(CustomerDTO customerDTO) {
        //Check if customer is eligible for loan
        LoanCriteria criteria = getLoanCriteria(customerDTO);
        return loanQueryService.countByCriteria(criteria) == 0;
    }

    private LoanCriteria getLoanCriteria(CustomerDTO customerDTO) {
        LoanCriteria criteria = new LoanCriteria();
        criteria.setCustomerId(new LongFilter());
        criteria.getCustomerId().setEquals(customerDTO.getId());

        LoanCriteria.LoanStatusFilter loanStatusFilter = new LoanCriteria.LoanStatusFilter();
        loanStatusFilter.setEquals(LoanStatus.PENDING);
        criteria.setStatus(loanStatusFilter);
        return criteria;
    }
}
