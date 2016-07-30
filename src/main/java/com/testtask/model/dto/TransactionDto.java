package com.testtask.model.dto;

import com.testtask.model.domain.Transaction;
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

    public TransactionDto(Transaction transaction) {
        this.amount = transaction.getAmount();
        this.type = transaction.getType();
        this.parentId = transaction.getParentId();
    }
}
