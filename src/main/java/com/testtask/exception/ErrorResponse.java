package com.testtask.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by etsatsina on 31-Jul-16.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ErrorResponse {

        private int code;

        private String message;
}
