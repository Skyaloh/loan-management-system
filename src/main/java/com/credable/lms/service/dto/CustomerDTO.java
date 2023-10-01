package com.credable.lms.service.dto;

import com.credable.lms.domain.enumeration.CustomerStatus;
import com.credable.lms.domain.enumeration.Gender;
import com.credable.lms.domain.enumeration.IDType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;


public class CustomerDTO {

    private Long id;

    @NotNull
    private String customerNumber;

    private String email;

    @Size( max = 150)
    private String firstName;

    @Size(max = 150)
    private String middleName;

    @Size(max = 150)
    private String lastName;

    @Size( max = 20)
    private String mobile;

    @Size( max = 100)
    private String idNumber;

    private Double monthlyIncome;

    private LocalDateTime dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

    @Enumerated(EnumType.STRING)
    private IDType idType;

    private LocalDateTime createdAt;

    private LocalDateTime createdDate;

    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public LocalDateTime getDob() {
        return dob;
    }

    public void setDob(LocalDateTime dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    public IDType getIdType() {
        return idType;
    }

    public void setIdType(IDType idType) {
        this.idType = idType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDTO that = (CustomerDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(customerNumber, that.customerNumber) && Objects.equals(email, that.email) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(mobile, that.mobile) && Objects.equals(idNumber, that.idNumber) && Objects.equals(monthlyIncome, that.monthlyIncome) && Objects.equals(dob, that.dob) && gender == that.gender && status == that.status && idType == that.idType && Objects.equals(createdAt, that.createdAt) && Objects.equals(createdDate, that.createdDate) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerNumber, email, firstName, lastName, mobile, idNumber, monthlyIncome, dob, gender, status, idType, createdAt, createdDate, updatedAt);
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id=" + id +
                ", customerNumber='" + customerNumber + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", monthlyIncome=" + monthlyIncome +
                ", dob=" + dob +
                ", gender=" + gender +
                ", status=" + status +
                ", idType=" + idType +
                ", createdAt=" + createdAt +
                ", createdDate=" + createdDate +
                '}';
    }
}
