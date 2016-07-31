package com.testtask.service;

import com.testtask.model.domain.Transaction;
import com.testtask.model.dto.TotalTransactionAmountDto;
import com.testtask.model.dto.TransactionDto;
import com.testtask.model.repository.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by etsatsina on 31-Jul-16.
 */
@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceImplTest {

    @Mock
    private TransactionRepository repository;

    private TransactionService service;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        this.service = new TransactionServiceImpl(repository);
    }

    @Test
    public void shouldCreate() throws Exception {
        Long id = 1L;
        Transaction t = new Transaction(id, 500.0, "cars", null);
        TransactionDto dto = new TransactionDto(t);
        given(this.repository.save(t)).willReturn(t);

        Transaction resultTransaction = this.service.create(id, dto);

        assertEquals(resultTransaction.getId(), t.getId());
        assertEquals(resultTransaction.getAmount(), t.getAmount());
        assertEquals(resultTransaction.getType(), t.getType());
        assertEquals(resultTransaction.getParentId(), t.getParentId());
    }

    @Test
    public void shouldGetTransaction() throws Exception {
        Long id = 1L;
        Transaction t = new Transaction(id, 500.0, "cars", null);
        given(this.repository.get(id)).willReturn(t);
        given(this.repository.contains(id)).willReturn(true);

        TransactionDto resultDto = this.service.get(id);

        assertEquals(resultDto.getAmount(), t.getAmount());
        assertEquals(resultDto.getType(), t.getType());
        assertEquals(resultDto.getParentId(), t.getParentId());
    }

    @Test
    public void shouldFindAllByTransactionType() throws Exception {
        Transaction t1 = new Transaction(1L, 500.0, "cars", null);
        Transaction t2 = new Transaction(2L, 500.0, "cars", null);
        given(this.repository.findAllByType("cars")).willReturn(asList(1L, 2L));

        List<Long> resultList = this.service.findAllByType("cars");

        assertFalse(resultList.isEmpty());
        assertEquals(resultList.size(), 2);
        assertTrue(resultList.contains(1L));
        assertTrue(resultList.contains(2L));
    }

    @Test
    public void shouldReturnEmptyList() throws Exception {
        given(this.repository.findAllByType("cars")).willReturn(asList());

        List<Long> resultList = this.service.findAllByType("cars");

        assertTrue(resultList.isEmpty());
        assertEquals(resultList.size(), 0);
    }

    @Test
    public void shouldReturnTotalAmount() throws Exception {
        Transaction t1 = new Transaction(1L, 500.0, "cars", null);
        Transaction t2 = new Transaction(2L, 500.0, "cars", 1L);
        t1.getChildTransactionsIds().add(2L);
        given(this.repository.contains(1L)).willReturn(true);
        given(this.repository.get(1L)).willReturn(t1);
        given(this.repository.get(2L)).willReturn(t2);


        TotalTransactionAmountDto dto = this.service.getTotalTransactionAmount(1L);

        assertTrue(dto.getSum().equals(t1.getAmount()+t2.getAmount()));
    }

}