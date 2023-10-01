package com.credable.lms.service.scoringengine.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;


public class CustomerScoreResponse implements Serializable {

    @JsonProperty("customerNumber")
    private String customerNumber;
    @JsonProperty("id")
    private Long id;

    @JsonProperty("score")
    private int score;

    @JsonProperty("limitAmount")
    private int limitAmount;

    @JsonProperty("exclusion")
    private String exclusion;

    @JsonProperty("exclusionReason")
    private String exclusionReason;

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(int limitAmount) {
        this.limitAmount = limitAmount;
    }

    public String getExclusion() {
        return exclusion;
    }

    public void setExclusion(String exclusion) {
        this.exclusion = exclusion;
    }

    public String getExclusionReason() {
        return exclusionReason;
    }

    public void setExclusionReason(String exclusionReason) {
        this.exclusionReason = exclusionReason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerScoreResponse that = (CustomerScoreResponse) o;
        return score == that.score && limitAmount == that.limitAmount && Objects.equals(customerNumber, that.customerNumber) && Objects.equals(id, that.id) && Objects.equals(exclusion, that.exclusion) && Objects.equals(exclusionReason, that.exclusionReason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerNumber, id, score, limitAmount, exclusion, exclusionReason);
    }

    @Override
    public String toString() {
        return "CustomerScoreResponse{" +
                "customerNumber='" + customerNumber + '\'' +
                ", id=" + id +
                ", score=" + score +
                ", limitAmount=" + limitAmount +
                ", exclusion='" + exclusion + '\'' +
                ", exclusionReason='" + exclusionReason + '\'' +
                '}';
    }
}
