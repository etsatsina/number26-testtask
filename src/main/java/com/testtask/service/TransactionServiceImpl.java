package com.testtask.service;

import com.testtask.exception.EntityExistsException;
import com.testtask.exception.EntityNotFoundException;
import com.testtask.exception.ParentNotFoundException;
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
    public Transaction create(Long id, TransactionDto transactionDto) {
        if (transactionRepository.contains(id)) {
            throw new EntityExistsException(id);
        }

        Long parentId = transactionDto.getParentId();

        if (parentId != null) {
            if (!transactionRepository.contains(parentId)) {
                throw new ParentNotFoundException(transactionDto.getParentId());
            } else {
                transactionRepository.get(parentId).getChildTransactionsIds().add(id);
            }
        }

        Transaction transaction = new Transaction();
        transaction.setId(id);
        transaction.setAmount(transactionDto.getAmount());
        transaction.setParentId(transactionDto.getParentId());
        transaction.setType(transactionDto.getType());

        return transactionRepository.save(transaction);
    }

    @Override
    public TransactionDto get(Long id) {
        if (!transactionRepository.contains(id)) {
            throw new EntityNotFoundException(id);
        }

        return new TransactionDto(transactionRepository.get(id));
    }

    @Override
    public List<Long> findAllByType(String type) {
        return transactionRepository.findAllByType(type);
    }

    @Override
    public TotalTransactionAmountDto getTotalTransactionAmount(Long id) {
        if (!transactionRepository.contains(id)) {
            throw new EntityNotFoundException(id);
        }

        TotalTransactionAmountDto result =  new TotalTransactionAmountDto();
        result.setSum(getTotalAmount(transactionRepository.get(id)));
        return result;
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
