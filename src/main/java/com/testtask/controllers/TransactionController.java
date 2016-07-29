package com.testtask.controllers;


import com.testtask.model.dto.TotalTransactionAmountDto;
import com.testtask.model.dto.TransactionDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etsatsina on 29-Jul-16.
 */

@RestController
@RequestMapping("/transactionservice")
public class TransactionController {

    @RequestMapping(value = "/transaction/{transactionId}", method = RequestMethod.PUT)
    public void create(@PathVariable("transactionId") Long id, TransactionDto transactionDto) {

    }

    @RequestMapping(value = "/transaction/{transactionId}", method = RequestMethod.GET)
    public TransactionDto getById(@PathVariable("transactionId") Long id) {
        return new TransactionDto();
    }

    @RequestMapping(value = "/types/{type}", method = RequestMethod.GET)
    public List<Long> getByType(@PathVariable("type") String type) {
        return new ArrayList<>(0);
    }

    @RequestMapping(value = "/sum/{transactionId}", method = RequestMethod.GET)
    public TotalTransactionAmountDto getTotalTransactionAmount(@PathVariable("transactionId") Long id) {
        return new TotalTransactionAmountDto();
    }

}
