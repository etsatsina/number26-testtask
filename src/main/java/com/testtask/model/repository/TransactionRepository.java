package com.testtask.model.repository;

import com.testtask.model.domain.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * Created by etsatsina on 30-Jul-16.
 */
public interface TransactionRepository {

    /**
     * Saves {@link Transaction} to datasource
     *
     * @param transaction which expected to be add
     * @return added transaction
     */
    Transaction save(Transaction transaction);

    /**
     * Get {@link Transaction} by its id
     *
     * @param id of requested {@link Transaction}
     * @return {@link Transaction} if found, otherwise - null
     */
    Transaction get(Long id);

    /**
     * Get list of transactions ids filtered by requested type
     *
     * @param type of transactions
     * @return list of ids
     */
    List<Long> findAllByType(String type);

    /**
     * Check if there is a {@link Transaction} with requested id
     * in the datasource
     *
     * @param id of transaction
     * @return true - if found, false - if not
     */
    Boolean contains(Long id);

    void clear();
}
