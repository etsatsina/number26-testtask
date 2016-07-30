package com.testtask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by etsatsina on 30-Jul-16.
 */
@ResponseStatus(value=HttpStatus.PRECONDITION_FAILED, reason="Parent with such id is not found")
public class ParentNotFoundException extends ApplicationException {

    public ParentNotFoundException(Long id) {
        super("Parent with id=" + id + " is not found in storage");
    }
}
