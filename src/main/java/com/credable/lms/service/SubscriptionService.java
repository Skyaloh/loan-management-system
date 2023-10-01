package com.credable.lms.service;

import com.credable.lms.domain.enumeration.CustomerStatus;
import com.credable.lms.domain.enumeration.Gender;
import com.credable.lms.domain.enumeration.IDType;
import com.credable.lms.service.dto.CustomerDTO;
import com.credable.lms.service.soap.api.generated.CustomerRequest;
import com.credable.lms.service.soap.api.generated.CustomerResponse;
import com.credable.lms.util.SOAPConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubscriptionService {
    private final Logger log = LoggerFactory.getLogger(SubscriptionService.class);
    private final CustomerQueryService customerQueryService;
    private final CustomerService customerService;

    private final SOAPConnector soapConnector;

    @Value("${customer.kyc.url}")
    private String customerKYCUrl;

    public SubscriptionService( CustomerQueryService customerQueryService, CustomerService customerService, SOAPConnector soapConnector) {
        this.customerQueryService = customerQueryService;
        this.customerService = customerService;
        this.soapConnector = soapConnector;
    }

    public CustomerDTO subscribe(String customerNumber) {
        log.debug("Request to subscribe: {}", customerNumber);
       return customerQueryService.findByCustomerNumber(customerNumber).orElseGet(() -> {
            CustomerRequest kycRequest = new CustomerRequest();
            kycRequest.setCustomerNumber(customerNumber);
            CustomerResponse customerKYCResponse = soapConnector.callWebService(kycRequest,"Customer KYC", customerKYCUrl);
            CustomerDTO newCustomer = new CustomerDTO();
            newCustomer.setCustomerNumber(customerKYCResponse.getCustomer().getCustomerNumber());
            newCustomer.setCreatedAt(customerKYCResponse.getCustomer().getCreatedAt().toGregorianCalendar().toZonedDateTime().toLocalDateTime());
            newCustomer.setCreatedDate(customerKYCResponse.getCustomer().getCreatedDate().toGregorianCalendar().toZonedDateTime().toLocalDateTime());
            newCustomer.setDob(customerKYCResponse.getCustomer().getDob().toGregorianCalendar().toZonedDateTime().toLocalDateTime());
            newCustomer.setEmail(customerKYCResponse.getCustomer().getEmail());
            newCustomer.setFirstName(customerKYCResponse.getCustomer().getFirstName());
            newCustomer.setGender(Gender.valueOf(customerKYCResponse.getCustomer().getGender().value()));
            newCustomer.setIdNumber(customerKYCResponse.getCustomer().getIdNumber());
            newCustomer.setLastName(customerKYCResponse.getCustomer().getLastName());
            newCustomer.setIdType(IDType.valueOf(customerKYCResponse.getCustomer().getIdType().value()));
            newCustomer.setStatus(CustomerStatus.valueOf(customerKYCResponse.getCustomer().getStatus().value()));
            newCustomer.setMonthlyIncome(customerKYCResponse.getCustomer().getMonthlyIncome());
            newCustomer.setUpdatedAt(customerKYCResponse.getCustomer().getUpdatedAt().toGregorianCalendar().toZonedDateTime().toLocalDateTime());
            newCustomer.setMobile(customerKYCResponse.getCustomer().getMobile());
            newCustomer.setMiddleName(customerKYCResponse.getCustomer().getMiddleName());
            return customerService.save(newCustomer);
        });
    }
}
