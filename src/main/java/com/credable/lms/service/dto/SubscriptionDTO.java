package com.credable.lms.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class SubscriptionDTO implements Serializable {
    private String customerNumber;

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionDTO that = (SubscriptionDTO) o;
        return Objects.equals(customerNumber, that.customerNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerNumber);
    }

    @Override
    public String toString() {
        return "SubscriptionDTO{" +
                "customerNumber='" + customerNumber + '\'' +
                '}';
    }
}
