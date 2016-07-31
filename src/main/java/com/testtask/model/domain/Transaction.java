package com.testtask.model.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by etsatsina on 29-Jul-16.
 */
@EqualsAndHashCode(of = "id")
@Setter
@Getter
public class Transaction {

    private Long id;

    private Double amount;

    private String type;

    private Long parentId;

    Set<Long> childTransactionsIds = new HashSet<>();
}
