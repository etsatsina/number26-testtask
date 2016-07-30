package com.testtask.controllers;


import com.testtask.model.dto.TotalTransactionAmountDto;
import com.testtask.model.dto.TransactionDto;
import com.testtask.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by etsatsina on 29-Jul-16.
 */
@RestController
@RequestMapping("/transactionservice")
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @RequestMapping(value = "/transaction/{transactionId}", method = RequestMethod.PUT)
    public void create(@PathVariable("transactionId") Long id, TransactionDto transactionDto) {
        transactionService.save(id, transactionDto);
    }

    @RequestMapping(value = "/transaction/{transactionId}", method = RequestMethod.GET)
    public TransactionDto getById(@PathVariable("transactionId") Long id) {
        return transactionService.get(id);
    }

    @RequestMapping(value = "/types/{type}", method = RequestMethod.GET)
    public List<Long> getByType(@PathVariable("type") String type) {
        return transactionService.findAllByType(type);
    }

    @RequestMapping(value = "/sum/{transactionId}", method = RequestMethod.GET)
    public TotalTransactionAmountDto getTotalTransactionAmount(@PathVariable("transactionId") Long id) {
        return transactionService.getTotalTransactionAmount(id);
    }

}
