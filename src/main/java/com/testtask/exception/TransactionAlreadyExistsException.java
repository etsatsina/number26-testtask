package com.testtask.exception;

/**
 * Created by etsatsina on 30-Jul-16.
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Transaction already exists")
public class TransactionAlreadyExistsException extends ApplicationException {

    public TransactionAlreadyExistsException(Long id) {
        super("Transaction with this id=" + id + " is already exist");
    }
}
