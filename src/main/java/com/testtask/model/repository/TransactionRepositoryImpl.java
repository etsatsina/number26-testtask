package com.testtask.model.repository;

import com.testtask.model.TransactionDatasource;
import com.testtask.model.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by etsatsina on 30-Jul-16.
 */
public class TransactionRepositoryImpl implements TransactionRepository {

    @Autowired
    private TransactionDatasource datasource;

    @Override
    public Transaction save(Transaction transaction) {
        datasource.getStorage().put(transaction.getId(), transaction);
        return transaction;
    }

    @Override
    public Transaction get(Long id) {
        return datasource.getStorage().get(id);
    }

    @Override
    public List<Long> findAllByType(String type) {
        return datasource.getStorage().values().stream()
                .filter(t -> t.getType().equals(type))
                .map(Transaction::getId)
                .collect(Collectors.toList());
    }

}
