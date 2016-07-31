package com.testtask;

import com.testtask.Number26TransactionsApplication;
import com.testtask.model.domain.Transaction;
import com.testtask.model.repository.TransactionRepository;
import com.testtask.service.TransactionService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by etsatsina on 31-Jul-16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class IntegrationTest {

    private MockMvc mvc;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldReturnStatusCreatedOnCreate() throws Exception {
        this.mvc.perform(put("/transactionservice/transaction/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"amount\":500,\"type\":\"cars\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnStatusPreconditionFailedOnCreateWithoutAmount() throws Exception {
        this.mvc.perform(put("/transactionservice/transaction/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"type\":\"cars\"}"))
                .andExpect(status().isPreconditionFailed());
    }

    @Test
    public void shouldReturnStatusPreconditionFailedOnCreateWithoutType() throws Exception {
        this.mvc.perform(put("/transactionservice/transaction/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"amount\":500}"))
                .andExpect(status().isPreconditionFailed());
    }

    @Test
    public void shouldReturnStatusBadRequestOnCreateWithoutExistingParent() throws Exception {
        this.mvc.perform(put("/transactionservice/transaction/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"amount\":500,\"type\":\"cars\", \"parent_id\": 2}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnStatusCreatedOnCreateWithExistingParent() throws Exception {
        this.mvc.perform(put("/transactionservice/transaction/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"amount\":500,\"type\":\"cars\"}"));

        this.mvc.perform(put("/transactionservice/transaction/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"amount\":500,\"type\":\"cars\", \"parent_id\": 1}"))
                .andExpect(status().isCreated());
    }


    @Test
    public void shouldReturnStatusBadRequestOnCreateWithExistingTransactionId() throws Exception {
        this.mvc.perform(put("/transactionservice/transaction/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"amount\":500,\"type\":\"cars\"}"));

        this.mvc.perform(put("/transactionservice/transaction/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"amount\":500,\"type\":\"cars\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnStatusOkAndDataOnValidIdGet() throws Exception {
        this.repository.save(new Transaction(1L, 500.0, "cars", null));

        this.mvc.perform(get("/transactionservice/transaction/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"amount\":500,\"type\":\"cars\"}"));
    }

    @Test
    public void shouldReturnStatusOkAndDataOnValidTypeGet() throws Exception {
        this.repository.save(new Transaction(1L, 500.0, "cars", null));

        this.mvc.perform(get("/transactionservice/types/cars"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnStatusOkAndEmptyListOnInvalidTypeGet() throws Exception {
        this.mvc.perform(get("/transactionservice/types/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnStatusOkAndSumOnValidIdGet() throws Exception {
        this.repository.save(new Transaction(1L, 500.0, "cars", null));

        this.mvc.perform(get("/transactionservice/sum/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"sum\":500}"));
    }

    @Test
    public void shouldReturnStatusOkAndSumOnValidIdGetTransitively() throws Exception {
        Transaction parent = new Transaction(1L, 500.0, "cars", null);
        Transaction child = new Transaction(2L, 1500.0, "cars", 1L);
        parent.getChildTransactionsIds().add(2L);
        this.repository.save(parent);
        this.repository.save(child);

        this.mvc.perform(get("/transactionservice/sum/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"sum\":2000}"));
    }

    @Test
    public void shouldReturnStatusNotFoundOnInvalidIdGetForSum() throws Exception {
        this.mvc.perform(get("/transactionservice/sum/1"))
                .andExpect(status().isNotFound());
    }

    @After
    public void tearDown() throws Exception {
        repository.clear();
    }
}