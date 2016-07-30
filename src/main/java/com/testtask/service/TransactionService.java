package com.testtask.service;

import com.testtask.model.dto.TotalTransactionAmountDto;
import com.testtask.model.dto.TransactionDto;

import java.util.List;

/**
 * Created by etsatsina on 29-Jul-16.
 */
public interface TransactionService {

    void save(Long id, TransactionDto transactionDto);

    TransactionDto get(Long id);

    List<Long> findAllByType(String type);

    TotalTransactionAmountDto getTotalTransactionAmount(Long id);
}
