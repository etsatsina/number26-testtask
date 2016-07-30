package com.testtask.exception;

/**
 * Created by etsatsina on 30-Jul-16.
 */
public class ApplicationException extends RuntimeException {

    public ApplicationException() {
        super();
    }

    public ApplicationException(String msg) {
        super(msg);
    }
}
