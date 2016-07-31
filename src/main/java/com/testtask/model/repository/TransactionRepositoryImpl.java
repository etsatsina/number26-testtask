package com.testtask.model.repository;

import com.testtask.model.TransactionDatasource;
import com.testtask.model.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Using a {@link HashMap} representation of datasource.
 *
 * Created by etsatsina on 30-Jul-16.
 */
@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    private TransactionDatasource datasource;

    @Autowired
    public TransactionRepositoryImpl(TransactionDatasource datasource) {
        this.datasource = datasource;
    }

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

    @Override
    public Boolean contains(Long id) {
        return datasource.getStorage().containsKey(id);
    }

    @Override
    public void clear() {
        datasource.getStorage().clear();
    }
}
