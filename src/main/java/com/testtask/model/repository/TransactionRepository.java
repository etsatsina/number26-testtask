package com.testtask.model.repository;

import com.testtask.model.domain.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by etsatsina on 30-Jul-16.
 */
public interface TransactionRepository {

    Transaction save(Transaction transaction);

    Transaction get(Long id);

    List<Long> findAllByType(String type);

    Boolean contains(Long id);
}
