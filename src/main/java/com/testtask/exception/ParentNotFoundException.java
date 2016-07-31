package com.testtask.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by etsatsina on 30-Jul-16.
 */
@Getter
public class ParentNotFoundException extends ApplicationException {

    private static final long serialVersionUID = -9112915121708215955L;

    private String message;

    public ParentNotFoundException(Long id) {
        this.message="Parent with id=" + id + " is not found in storage";
    }
}
