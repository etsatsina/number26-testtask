package com.testtask.model;

import com.testtask.model.domain.Transaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Datasource for storing {@link Transaction}.
 *
 * Created by etsatsina on 30-Jul-16.
 */
@Component
@Getter
@NoArgsConstructor
public class TransactionDatasource {

    private final HashMap<Long, Transaction> storage = new HashMap<>();
}
