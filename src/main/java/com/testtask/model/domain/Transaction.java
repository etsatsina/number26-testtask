package com.testtask.model.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by etsatsina on 29-Jul-16.
 */
@EqualsAndHashCode(of = "id")
@Setter
@Getter
@NoArgsConstructor
public class Transaction {

    private Long id;

    private Double amount;

    private String type;

    private Long parentId;

    Set<Long> childTransactionsIds = new HashSet<>();

    public Transaction(Long id, Double amount, String type, Long parentId) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.parentId = parentId;
    }
}
