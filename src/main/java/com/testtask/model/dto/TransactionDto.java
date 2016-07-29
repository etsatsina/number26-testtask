package com.testtask.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Created by etsatsina on 29-Jul-16.
 */
@Getter
@Setter
public class TransactionDto {

    @NotNull
    private Double amount;

    @NotNull
    private String type;

    private Long parentId;
}
