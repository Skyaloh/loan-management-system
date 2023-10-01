package com.credable.lms.domain;

import com.credable.lms.domain.enumeration.LoanStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "loan")
public class Loan extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "repayment_date")
    private Instant repaymentDate;

    @Column(name = "disbursement_date")
    private Instant disbursementDate;

    @Column(name = "interest_amount")
    private BigDecimal interestAmount;

    @Column(name = "interest_percentage")
    private BigDecimal interestPercentage;

    @Column(name = "overdue_fee_percentage")
    private BigDecimal overdueFeePercentage;

    @Column(name = "disbursement_charge_amount")
    private BigDecimal disbursementChargeAmount;

    @Size( max = 100)
    @Column(name = "loan_reference_number")
    private String loanReferenceNumber;

    @Column(name = "rejection_reason")
    private String rejectionReason;

    @Column(name = "rejection_date")
    private Instant rejectionDate;

    @Column(name = "approval_date")
    private Instant approvalDate;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnore
    private Customer customer;


    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus;


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(BigDecimal interestAmount) {
        this.interestAmount = interestAmount;
    }

    public BigDecimal getOverdueFeePercentage() {
        return overdueFeePercentage;
    }

    public void setOverdueFeePercentage(BigDecimal overdueFeePercentage) {
        this.overdueFeePercentage = overdueFeePercentage;
    }

    public BigDecimal getDisbursementChargeAmount() {
        return disbursementChargeAmount;
    }

    public void setDisbursementChargeAmount(BigDecimal disbursementChargeAmount) {
        this.disbursementChargeAmount = disbursementChargeAmount;
    }

    public String getLoanReferenceNumber() {
        return loanReferenceNumber;
    }

    public void setLoanReferenceNumber(String loanReferenceNumber) {
        this.loanReferenceNumber = loanReferenceNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Instant getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(Instant repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public Instant getDisbursementDate() {
        return disbursementDate;
    }

    public void setDisbursementDate(Instant disbursementDate) {
        this.disbursementDate = disbursementDate;
    }

    public BigDecimal getInterestPercentage() {
        return interestPercentage;
    }

    public void setInterestPercentage(BigDecimal interestPercentage) {
        this.interestPercentage = interestPercentage;
    }


    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public Instant getRejectionDate() {
        return rejectionDate;
    }

    public void setRejectionDate(Instant rejectionDate) {
        this.rejectionDate = rejectionDate;
    }

    public Instant getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Instant approvalDate) {
        this.approvalDate = approvalDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Objects.equals(id, loan.id) && Objects.equals(amount, loan.amount) && Objects.equals(repaymentDate, loan.repaymentDate) && Objects.equals(disbursementDate, loan.disbursementDate) && Objects.equals(interestAmount, loan.interestAmount) && Objects.equals(interestPercentage, loan.interestPercentage) && Objects.equals(overdueFeePercentage, loan.overdueFeePercentage) && Objects.equals(disbursementChargeAmount, loan.disbursementChargeAmount) && Objects.equals(loanReferenceNumber, loan.loanReferenceNumber) && loanStatus == loan.loanStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, repaymentDate, disbursementDate, interestAmount, interestPercentage, overdueFeePercentage, disbursementChargeAmount, loanReferenceNumber, loanStatus);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", amount=" + amount +
                ", repaymentDate=" + repaymentDate +
                ", disbursementDate=" + disbursementDate +
                ", interestAmount=" + interestAmount +
                ", interestPercentage=" + interestPercentage +
                ", overdueFeePercentage=" + overdueFeePercentage +
                ", disbursementChargeAmount=" + disbursementChargeAmount +
                ", loanReferenceNumber='" + loanReferenceNumber + '\'' +
                ", rejectionReason='" + rejectionReason + '\'' +
                ", rejectionDate=" + rejectionDate +
                ", approvalDate=" + approvalDate +
                ", loanStatus=" + loanStatus +
                '}';
    }
}
