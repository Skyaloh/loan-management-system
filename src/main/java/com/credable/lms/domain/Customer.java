package com.credable.lms.domain;

import com.credable.lms.domain.enumeration.CustomerStatus;
import com.credable.lms.domain.enumeration.Gender;
import com.credable.lms.domain.enumeration.IDType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    public Long getId() {
        return id;
    }

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "customer_number", nullable = false, unique = true)
    private String customerNumber;

    @Size(max = 50)
    @Column(name = "email", unique = true)
    private String email;

    @Size( max = 150)
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 150)
    @Column(name = "last_name")
    private String lastName;

    @Size(max = 150)
    @Column(name = "middle_name")
    private String middleName;

    @Size( max = 20)
    @Column(name = "mobile")
    private String mobile;

    @Size( max = 100)
    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "monthly_income")
    private Double monthlyIncome;

    @Column(name = "dob")
    private LocalDateTime dob;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

    @Column(name = "id_type")
    @Enumerated(EnumType.STRING)
    private IDType idType;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(customerNumber, customer.customerNumber) && Objects.equals(email, customer.email) && Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(middleName, customer.middleName) && Objects.equals(mobile, customer.mobile) && Objects.equals(idNumber, customer.idNumber) && Objects.equals(monthlyIncome, customer.monthlyIncome) && Objects.equals(dob, customer.dob) && gender == customer.gender && status == customer.status && idType == customer.idType && Objects.equals(createdAt, customer.createdAt) && Objects.equals(createdDate, customer.createdDate) && Objects.equals(updatedAt, customer.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerNumber, email, firstName, lastName, middleName, mobile, idNumber, monthlyIncome, dob, gender, status, idType, createdAt, createdDate, updatedAt);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", customerNumber='" + customerNumber + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", monthlyIncome=" + monthlyIncome +
                ", dob=" + dob +
                ", gender=" + gender +
                ", status=" + status +
                ", idType=" + idType +
                ", createdAt=" + createdAt +
                ", createdDate=" + createdDate +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
