package com.spring.boot.rest.controller;

import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.spring.boot.rest.domain.Transaction;
import com.spring.boot.rest.service.TransactionService;
import com.spring.boot.rest.util.TransactionUtil;

/**
 * Test case for {@link TransactionController}.
 * 
 * @author Simon Njenga
 * @version 0.1
 */
@RunWith(MockitoJUnitRunner.class)
public class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    private TransactionController transactionController;

    @Before
    public void setUp() throws Exception {
    	transactionController = new TransactionController(transactionService);
    }    
      
    /**
     * This test should create and save a new transaction.
     * 
     * @throws Exception If something goes wrong
     */
    @Test
    public void testShouldCreateTransaction() throws Exception {
        final Transaction storedTransaction = this.stubRepositoryToReturnStoredTransaction();
        final Transaction transaction = TransactionUtil.createTransaction();
        Transaction returnedTransaction = transactionController.addTransaction(transaction);
        // verify transaction was passed to TransactionService
        Mockito.verify(transactionService, Mockito.times(1)).saveTransaction(transaction);
        Assert.assertEquals("Returned transaction should come from the service", storedTransaction, returnedTransaction);
    }

    /**
     * This test should list all transactions.
     * 
     * @throws Exception If something goes wrong
     */
    @Test
    public void testListAllTransactions() throws Exception {
    	this.stubServiceToReturnExistingTransaction(20);
        Collection<Transaction> transactions = transactionController.listTransactions();
        Assert.assertNotNull(transactions);
        Assert.assertEquals(20, transactions.size());
        // verify transaction was passed to TransactionService
        Mockito.verify(transactionService, Mockito.times(1)).getTransactionList();
    }
    
    /**
     * Stub method.
     */
    private Transaction stubRepositoryToReturnStoredTransaction() throws Exception {
    	final Transaction transaction = TransactionUtil.createTransaction();
        Mockito.when(transactionService.saveTransaction(Mockito.any(Transaction.class))).thenReturn(transaction);
        return transaction;
    }
    
    /**
     * Stub method.
     */
    private void stubServiceToReturnExistingTransaction(int howMany) {
    	Mockito.when(transactionService.getTransactionList()).thenReturn(TransactionUtil.createTransactionList(howMany));
    }
    
    @After
    public void tearDown(){
    }
}
