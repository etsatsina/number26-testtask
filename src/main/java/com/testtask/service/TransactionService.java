package com.testtask.service;

import com.testtask.model.domain.Transaction;
import com.testtask.model.dto.TotalTransactionAmountDto;
import com.testtask.model.dto.TransactionDto;

import java.util.List;

/**
 * Created by etsatsina on 29-Jul-16.
 */
public interface TransactionService {

    /**
     * Create a new {@link Transaction}
     *
     * @param id of transaction
     * @param transactionDto representation of transaction object from view
     * @return added transaction
     * @throws com.testtask.exception.EntityExistsException
     * @throws com.testtask.exception.ParentNotFoundException
     */
    Transaction create(Long id, TransactionDto transactionDto);

    TransactionDto get(Long id);

    List<Long> findAllByType(String type);

    /**
     * Return sum of all transactions that are transitively (recursively)
     * linked by their parent_id
     *
     * @param id of parent transaction
     * @return TotalTransactionAmountDto representation for response
     */
    TotalTransactionAmountDto getTotalTransactionAmount(Long id);
}
