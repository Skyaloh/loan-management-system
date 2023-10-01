package com.credable.lms.domain;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "loan_disbursement_charge")
public class LoanDisbursementCharge extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    public Long getId() {
        return id;
    }
}
