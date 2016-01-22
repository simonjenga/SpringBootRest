package com.spring.boot.rest.controller;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.spring.boot.rest.domain.Amount;
import com.spring.boot.rest.domain.Transaction;
import com.spring.boot.rest.service.TransactionService;
import com.spring.boot.rest.util.TransactionUtil;

/**
 * Test case for {@link AmountController}.
 * 
 * @author Simon Njenga
 * @version 0.1
 */
@RunWith(MockitoJUnitRunner.class)
public class AmountControllerTest {

	@Mock
    private TransactionService transactionService;
    
    /**
     * This test should create, save a new transaction, and get the amounts within it.
     * 
     * @throws Exception If something goes wrong
     */
    @Test
    public void testTransactionShouldCreateAmounts() throws Exception {
        final List<Amount> listAmount = this.stubRepositoryToReturnStoredAmounts();
        Assert.assertFalse(listAmount == null);
        Assert.assertTrue(listAmount.size() > 0);
        Assert.assertFalse(listAmount.get(0).getAmount().equals(null));
        Assert.assertTrue(listAmount.get(0).getAmount().equals(5000D));
    }
    
    /**
     * Stub method.
     */
    private List<Amount> stubRepositoryToReturnStoredAmounts() throws Exception {
    	final Transaction transaction = TransactionUtil.createTransaction();
        Mockito.when(transactionService.saveTransaction(Mockito.any(Transaction.class))).thenReturn(transaction);
        return transaction.getAmount();
    }
}
