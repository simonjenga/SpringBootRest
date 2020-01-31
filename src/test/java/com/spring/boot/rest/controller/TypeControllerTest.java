package com.spring.boot.rest.controller;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.spring.boot.rest.domain.Transaction;
import com.spring.boot.rest.domain.Type;
import com.spring.boot.rest.service.TransactionService;
import com.spring.boot.rest.util.TransactionUtil;

/**
 * Test case for {@link TransactionController}.
 * 
 * @author Simon Njenga
 * @version 0.1
 */
@RunWith(MockitoJUnitRunner.class)
public class TypeControllerTest {

    @Mock
    private TransactionService transactionService;
    
    /**
     * This test should create, save a new transaction, and get the types within it.
     * 
     * @throws Exception If something goes wrong
     */
    @Test
    public void testTransactionShouldCreateTypes() throws Exception {
        final List<Type> listTypes = this.stubRepositoryToReturnStoredTypes();
        Assert.assertFalse(listTypes == null);
        Assert.assertTrue(listTypes.size() > 0);
        Assert.assertFalse(listTypes.get(0).getType().isEmpty());
        Assert.assertTrue(listTypes.get(0).getType().toString() != null);
        Assert.assertTrue(listTypes.get(0).getType().equals("cars"));
    }
    
    /**
     * Stub method.
     */
    private List<Type> stubRepositoryToReturnStoredTypes() throws Exception {
    	final Transaction transaction = TransactionUtil.createTransaction();
    	Mockito.lenient().when(transactionService.saveTransaction(Mockito.any(Transaction.class))).thenReturn(transaction);
        return transaction.getType();
    }
}
