package com.testtask.service;

import com.testtask.exception.ParentNotFoundException;
import com.testtask.exception.TransactionAlreadyExistsException;
import com.testtask.model.domain.Transaction;
import com.testtask.model.dto.TotalTransactionAmountDto;
import com.testtask.model.dto.TransactionDto;
import com.testtask.model.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by etsatsina on 29-Jul-16.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void save(Long id, TransactionDto transactionDto) {
        if (transactionRepository.get(id) != null) {
            throw new TransactionAlreadyExistsException(id);
        }

        if (transactionDto.getParentId() != null && transactionRepository.get(id) == null) {
            throw new ParentNotFoundException(transactionDto.getParentId());
        }
        else {
            Long parentId = transactionDto.getParentId();
            transactionRepository.get(parentId).getChildTransactionsIds().add(id);
        }

        Transaction transaction = new Transaction();
        transaction.setId(id);
        transaction.setAmount(transactionDto.getAmount());
        transaction.setParentId(transactionDto.getParentId());
        transaction.setType(transactionDto.getType());

        transactionRepository.save(transaction);
    }

    @Override
    public TransactionDto get(Long id) {
        return new TransactionDto(transactionRepository.get(id));
    }

    @Override
    public List<Long> findAllByType(String type) {
        return transactionRepository.findAllByType(type);
    }

    @Override
    public TotalTransactionAmountDto getTotalTransactionAmount(Long id) {
        return new TotalTransactionAmountDto(getTotalAmount(transactionRepository.get(id)));
    }

    private Double getTotalAmount(Transaction transaction) {
        Set<Long> childrenIds = transaction.getChildTransactionsIds();

        if (childrenIds.isEmpty()) {
            return transaction.getAmount();
        } else {
            return childrenIds.stream()
                    .map(id -> transactionRepository.get(id))
                    .map(child -> this.getTotalAmount(child))
                    .reduce(transaction.getAmount(), (partialSum, sum) -> partialSum + sum);
        }
    }
}
