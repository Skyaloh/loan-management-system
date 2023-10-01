package com.credable.lms.service.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;

public class LoanRequestDTO implements Serializable {
    @NotNull
    private String customerNumber;
    @NotNull
    private String loanAmount;

    public LoanRequestDTO() {
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanRequestDTO that = (LoanRequestDTO) o;
        return Objects.equals(customerNumber, that.customerNumber) && Objects.equals(loanAmount, that.loanAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerNumber, loanAmount);
    }

    @Override
    public String toString() {
        return "LoanRequestDTO{" +
                "customerNumber='" + customerNumber + '\'' +
                ", loanAmount='" + loanAmount + '\'' +
                '}';
    }
}
