package com.sample.ccm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VatAlreadyExistException extends Exception {
    private String vatNumber;

    public VatAlreadyExistException(String vatNumber) {
        super();
        this.vatNumber = vatNumber;
    }

    @Override
    public String getMessage() {
        return "The vat number " + vatNumber +  " already exists";
    }
}
