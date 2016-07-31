package com.testtask.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Created by etsatsina on 29-Jul-16.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TotalTransactionAmountDto {

    @NotNull
    private Double sum;
}
