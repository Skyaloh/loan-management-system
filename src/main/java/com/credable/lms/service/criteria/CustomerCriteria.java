package com.credable.lms.service.criteria;

import com.credable.lms.domain.enumeration.CustomerStatus;
import com.credable.lms.service.helper.filter.Filter;
import com.credable.lms.service.helper.filter.LongFilter;

import java.io.Serializable;
import java.util.Objects;

public class CustomerCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    public static class CustomerStatusFilter extends Filter<CustomerStatus> {
    }

    private LongFilter id;

    private CustomerStatusFilter status;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public CustomerStatusFilter getStatus() {
        return status;
    }

    public void setStatus(CustomerStatusFilter status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerCriteria that = (CustomerCriteria) o;
        return Objects.equals(id, that.id) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }

    @Override
    public String toString() {
        return "CustomerCriteria{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}
