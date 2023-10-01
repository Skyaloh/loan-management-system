package com.credable.lms.service.criteria;

import com.credable.lms.domain.enumeration.LoanStatus;
import com.credable.lms.service.helper.filter.Filter;
import com.credable.lms.service.helper.filter.LongFilter;


import java.io.Serializable;
import java.util.Objects;


public class LoanCriteria implements Serializable {
    private static final long serialVersionUID = 1L;

    public static class LoanStatusFilter extends Filter<LoanStatus> {
    }

    private LongFilter id;
    private LongFilter customerId;
    private LoanStatusFilter status;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getCustomerId() {
        return customerId;
    }

    public void setCustomerId(LongFilter customerId) {
        this.customerId = customerId;
    }

    public LoanStatusFilter getStatus() {
        return status;
    }

    public void setStatus(LoanStatusFilter status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanCriteria that = (LoanCriteria) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "LoanCriteria{" +
                "id=" + id +
                '}';
    }
}
