package com.credable.lms.service.dto;

import com.credable.lms.domain.Customer;

import com.credable.lms.domain.enumeration.LoanStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;


public class LoanDTO implements Serializable {

    private Long id;

    @NotNull
    private BigDecimal amount;

    private Instant repaymentDate;

    private Instant disbursementDate;

    private BigDecimal interestAmount;

    private BigDecimal overdueFeePercentage;

    private BigDecimal disbursementChargeAmount;

    private String loanReferenceNumber;

    private String rejectionReason;

    private Instant rejectionDate;

    private Instant approvalDate;

    @NotNull
    private Customer customer;


    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus;

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
        LoanDTO loanDTO = (LoanDTO) o;
        return Objects.equals(id, loanDTO.id) && Objects.equals(amount, loanDTO.amount) && Objects.equals(repaymentDate, loanDTO.repaymentDate) && Objects.equals(disbursementDate, loanDTO.disbursementDate) && Objects.equals(interestAmount, loanDTO.interestAmount) && Objects.equals(overdueFeePercentage, loanDTO.overdueFeePercentage) && Objects.equals(disbursementChargeAmount, loanDTO.disbursementChargeAmount) && Objects.equals(loanReferenceNumber, loanDTO.loanReferenceNumber) && Objects.equals(rejectionReason, loanDTO.rejectionReason) && Objects.equals(rejectionDate, loanDTO.rejectionDate) && Objects.equals(approvalDate, loanDTO.approvalDate) && Objects.equals(customer, loanDTO.customer) && loanStatus == loanDTO.loanStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, repaymentDate, disbursementDate, interestAmount, overdueFeePercentage, disbursementChargeAmount, loanReferenceNumber, rejectionReason, rejectionDate, approvalDate, customer, loanStatus);
    }

    @Override
    public String toString() {
        return "LoanDTO{" +
                "id=" + id +
                ", amount=" + amount +
                ", repaymentDate=" + repaymentDate +
                ", disbursementDate=" + disbursementDate +
                ", interestAmount=" + interestAmount +
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
