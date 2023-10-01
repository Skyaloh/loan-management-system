package com.credable.lms.resource.errors;

public class CustomerHasExistingLoanException extends BadRequestAlertException{
    private static final long serialVersionUID = 1L;

    public CustomerHasExistingLoanException(String defaultMessage) {
        super(defaultMessage, "loan", "");
    }
}
