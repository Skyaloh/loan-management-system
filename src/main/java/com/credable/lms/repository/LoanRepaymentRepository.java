package com.credable.lms.repository;

import com.credable.lms.domain.LoanRepayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepaymentRepository extends JpaRepository<LoanRepayment, Long>, JpaSpecificationExecutor<LoanRepayment> {
}
