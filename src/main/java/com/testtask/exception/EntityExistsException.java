package com.testtask.exception;

import lombok.Getter;

/**
 * Created by etsatsina on 30-Jul-16.
 */
@Getter
public class EntityExistsException extends ApplicationException {

    private static final long serialVersionUID = 5961176601046269640L;

    private String message;

    public EntityExistsException(Long id) {
        this.message = "Transaction with this id=" + id + " is already exist";
    }
}
