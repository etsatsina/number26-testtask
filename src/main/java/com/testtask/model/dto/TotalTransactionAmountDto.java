package com.testtask.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by etsatsina on 29-Jul-16.
 */
@AllArgsConstructor
public class TotalTransactionAmountDto {

    @NotNull
    private final Double sum;
}
