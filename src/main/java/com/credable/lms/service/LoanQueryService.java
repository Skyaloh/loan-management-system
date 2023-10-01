package com.credable.lms.service;


import com.credable.lms.domain.Loan;
import com.credable.lms.domain.Loan_;
import com.credable.lms.repository.LoanRepository;
import com.credable.lms.service.criteria.LoanCriteria;
import com.credable.lms.service.dto.LoanDTO;
import com.credable.lms.service.helper.QueryService;
import com.credable.lms.service.mapper.LoanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;



@Service
@Transactional(readOnly = true)
public class LoanQueryService extends QueryService<Loan> {
    private final Logger log = LoggerFactory.getLogger(LoanQueryService.class.getName());

    private final LoanMapper loanMapper;

    private final LoanRepository loanRepository;

    public LoanQueryService(LoanMapper loanMapper, LoanRepository loanRepository) {
        this.loanMapper = loanMapper;
        this.loanRepository = loanRepository;
    }


    /**
     * Return a {@link List} of {@link LoanDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<LoanDTO> findByCriteria(LoanCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Loan> specification = createSpecification(criteria);
        return loanMapper.toDto(loanRepository.findAll(specification));
    }

    /**
     * Return a {@link Optional} of {@link LoanDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Optional<LoanDTO> findOneCriteria(LoanCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Loan> specification = createSpecification(criteria);
        return loanRepository.findOne(specification).map(loanMapper::toDto);
    }

    /**
     * Return a {@link Page} of {@link LoanDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<LoanDTO> findByCriteria(LoanCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Loan> specification = createSpecification(criteria);
        return loanRepository.findAll(specification, page)
                .map(loanMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(LoanCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Loan> specification = createSpecification(criteria);
        return loanRepository.count(specification);
    }

    /**
     * Function to convert LoanCriteria to a {@link Specification}
     */
    private Specification<Loan> createSpecification(LoanCriteria criteria) {
        Specification<Loan> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Loan_.id));
            }

        }
        return specification;
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
        return loanRepository.findById(id).map(loanMapper::toDto);
    }
    

}
