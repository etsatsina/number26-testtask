package com.testtask.service;

import com.testtask.model.dto.TotalTransactionAmountDto;
import com.testtask.model.dto.TransactionDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by etsatsina on 29-Jul-16.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Override
    public void save(Long id, TransactionDto transactionDto) {

    }

    @Override
    public TransactionDto getById(Long id) {
        return null;
    }

    @Override
    public List<Long> getByType(String type) {
        return null;
    }

    @Override
    public TotalTransactionAmountDto getTotalTransactionAmount(Long id) {
        return null;
    }
}
