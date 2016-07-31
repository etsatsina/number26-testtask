package com.testtask.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.testtask.model.domain.Transaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * Created by etsatsina on 29-Jul-16.
 */
@Getter
@Setter
@NoArgsConstructor
public class TransactionDto {

    @NotNull(message = "amount parameter must be provided")
    private Double amount;

    @NotNull(message = "type parameter must be provided")
    private String type;

    @JsonProperty(value = "parent_id")
    private Long parentId;

    public TransactionDto(Transaction transaction) {
        this.amount = transaction.getAmount();
        this.type = transaction.getType();
        this.parentId = transaction.getParentId();
    }
}
