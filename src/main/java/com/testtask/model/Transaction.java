package com.testtask.model;

import lombok.EqualsAndHashCode;

/**
 * Created by etsatsina on 29-Jul-16.
 */
@EqualsAndHashCode(exclude = "id")
public class Transaction {

    private Long id;

    private Double amount;

    private String type;

    private Long parentId;
}
