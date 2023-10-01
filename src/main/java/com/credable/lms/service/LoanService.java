package com.credable.lms.service;

import com.credable.lms.domain.Loan;
import com.credable.lms.repository.LoanRepository;
import com.credable.lms.service.dto.LoanDTO;
import com.credable.lms.service.mapper.LoanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class LoanService {
    private final Logger log = LoggerFactory.getLogger(LoanService.class);

    private final LoanRepository loanRepository;

    private final LoanMapper loanMapper;

    private final LoanQueryService loanQueryService;

    public LoanService(LoanRepository loanRepository, LoanMapper loanMapper, LoanQueryService loanQueryService) {
        this.loanRepository = loanRepository;
        this.loanMapper = loanMapper;
        this.loanQueryService = loanQueryService;
    }

    /**
     * Save a loan.
     *
     * @param loanDTO the entity to save
     * @return the persisted entity
     */
    public LoanDTO save(LoanDTO loanDTO) {
        log.debug("Request to save Loan : {}", loanDTO);

        Loan loan = loanMapper.toEntity(loanDTO);
        loan = loanRepository.save(loan);
        return loanMapper.toDto(loan);
    }

    /**
     * Get all the loans.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<LoanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Loans");
        return loanRepository.findAll(pageable)
                .map(loanMapper::toDto);
    }


    /**
     * Get one loan by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<LoanDTO> findOne(Long id) {
        log.debug("Request to get Loan : {}", id);
        return loanRepository.findById(id)
                .map(loanMapper::toDto);

    }

    /**
     * Delete the loan by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Loan : {}", id);
        loanRepository.deleteById(id);
    }

}
