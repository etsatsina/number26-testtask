package com.testtask.controllers;


import com.testtask.model.dto.TotalTransactionAmountDto;
import com.testtask.model.dto.TransactionDto;
import com.testtask.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @RequestMapping(value = "/transaction/{transactionId}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable("transactionId") Long id,
                       @Valid @RequestBody TransactionDto transactionDto) {
        transactionService.create(id, transactionDto);
    }

    @RequestMapping(value = "/transaction/{transactionId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public TransactionDto getById(@PathVariable("transactionId") Long id) {
        return transactionService.get(id);
    }

    @RequestMapping(value = "/types/{type}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Long> getByType(@PathVariable("type") String type) {
        return transactionService.findAllByType(type);
    }

    @RequestMapping(value = "/sum/{transactionId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public TotalTransactionAmountDto getTotalTransactionAmount(@PathVariable("transactionId") Long id) {
        return transactionService.getTotalTransactionAmount(id);
    }

}
