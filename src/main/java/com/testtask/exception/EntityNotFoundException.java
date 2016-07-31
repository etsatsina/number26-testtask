package com.testtask.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by etsatsina on 31-Jul-16.
 */
@Getter
public class EntityNotFoundException extends ApplicationException {

    private static final long serialVersionUID = -8469296692439647059L;

    private String message;

    public EntityNotFoundException(Long id) {
        this.message = "Transaction with this id=" + id + " is not found";
    }
}
