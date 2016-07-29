package com.testtask.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by etsatsina on 29-Jul-16.
 */
@Getter
@Setter
public class TotalTransactionAmountDto {

    @NotNull
    private BigDecimal sum;
}
