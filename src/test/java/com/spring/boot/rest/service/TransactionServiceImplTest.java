package com.spring.boot.rest.service;

import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.spring.boot.rest.domain.Transaction;
import com.spring.boot.rest.repository.TransactionRepository;
import com.spring.boot.rest.service.exception.TransactionAlreadyExists;
import com.spring.boot.rest.util.TransactionUtil;

/**
 * Test case for {@link TransactionServiceImpl}.
 * 
 * @author Simon Njenga
 * @version 0.1
 */
@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    private TransactionService transactionService;

    @Before
    public void setUp() throws Exception {
    	transactionService = new TransactionServiceImpl(transactionRepository);
    }
    
    /**
     * This test should save a new transaction and given there does not exist one with the
     * same Id, then the saved transaction should be returned.
     * 
     * @throws Exception If something goes wrong
     */
    @Test
    public void testSaveAndReturnTransaction() throws Exception {
        final Transaction savedTransaction = stubRepositoryToReturnTransactionOnSave();
        final Transaction transaction = TransactionUtil.createTransaction();
        final Transaction returnedTransaction = transactionService.saveTransaction(transaction);
        // verify transactionRepository was called with transaction
        Mockito.verify(transactionRepository, Mockito.times(1)).save(transaction);
        Assert.assertEquals("Returned transaction should come from the repository", savedTransaction, returnedTransaction);
    }
    
    /**
     * This test should save a new transaction and given there exists one with the
     * same Id, then an exception should be thrown.
     * 
     * @throws Exception If something goes wrong
     */
    @Test
    public void testSaveNewTransactionWithTheSameId() throws Exception {
    	this.stubRepositoryToReturnExistingTransaction();
        try {
        	transactionService.saveTransaction(TransactionUtil.createTransaction());        	
        	Assert.fail("Expected exception here!");        	
        } catch (TransactionAlreadyExists e) {
        	Assert.assertNotNull(e);
        }
        Mockito.verify(transactionRepository, Mockito.never()).save(Mockito.any(Transaction.class));
    }
    
    /**
     * This test should list all transactions and given there exists some,
     * then a collection should be returned.
     * 
     * @throws Exception If something goes wrong
     */
    @Test
    public void testListAllTransactions() throws Exception {
    	this.stubRepositoryToReturnExistingTransactions(20);
    	Collection<Transaction> list = transactionService.getTransactionList();
    	Assert.assertNotNull(list);
    	Assert.assertEquals(20, list.size());
    	Mockito.verify(transactionRepository, Mockito.times(1)).findAll();
    } 
    
    /**
     * This test should list all transactions and given there exists none,
     * then an empty collection should be returned.
     * 
     * @throws Exception If something goes wrong
     */
    @Test
    public void testListNotAnyTransactions() throws Exception {
    	this.stubRepositoryToReturnExistingTransactions(0);
        Collection<Transaction> list = transactionService.getTransactionList();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.isEmpty());
        Mockito.verify(transactionRepository, Mockito.times(1)).findAll();
    }       
    
    /**
     * Stub method.
     */
    private Transaction stubRepositoryToReturnTransactionOnSave() {
    	final Transaction transaction = TransactionUtil.createTransaction();
        Mockito.when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(transaction);
        return transaction;
    }
    
    /**
     * Stub method.
     */
    private void stubRepositoryToReturnExistingTransactions(int howMany) {
    	Mockito.when(transactionRepository.findAll()).thenReturn(TransactionUtil.transactionList(howMany));
    }
    
    /**
     * Stub method.
     */
    private void stubRepositoryToReturnExistingTransaction() {
    	final Transaction transaction = TransactionUtil.createTransaction();
    	Mockito.when(transactionRepository.getOne(transaction.getId())).thenReturn(transaction);
    }

    @After
    public void tearDown(){
    }
}
