package com.spring.boot.rest.service;

import java.util.Collection;
import java.util.List;

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
import com.spring.boot.rest.service.exception.TransactionDoesNotExist;
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
     * This test should save a new transaction and given that there does not exist one with the
     * same Id, then the saved transaction should be returned.
     * 
     * @throws Exception If something goes wrong
     */
    @Test
    public void testSaveAndReturnTransaction() throws Exception {
        final Transaction savedTransaction = this.stubRepositoryToReturnTransactionOnSave();
        final Transaction transaction = TransactionUtil.createTransaction();
        final Transaction returnedTransaction = transactionService.saveTransaction(transaction);
        // verify transactionRepository was called with transaction
        Mockito.verify(transactionRepository, Mockito.times(1)).save(transaction);
        String message = "Returned transaction should come from the repository";
        Assert.assertEquals(message, savedTransaction, returnedTransaction);
    }
    
    /**
     * This test should save a new transaction and given that there exists one with the
     * same Id, then an exception should be thrown.
     * 
     * @throws Exception If something goes wrong
     */
    @Test
    public void testSaveNewTransactionWithTheSameId() throws Exception {
    	this.stubRepositoryToReturnExistingTransaction();
    	final Transaction transaction = TransactionUtil.createTransaction();
        try {
        	transactionService.saveTransaction(transaction);        	
        	Assert.fail("Expected exception here!");        	
        } catch (TransactionAlreadyExists e) {
        	String message = "There already exists a transaction with id=" + transaction.getId();
        	Assert.assertNotNull(e);
        	Assert.assertEquals(e.getMessage(), message);
        }
        Mockito.verify(transactionRepository, Mockito.never()).save(Mockito.any(Transaction.class));
    }
    
    /**
     * This test should update a transaction and given that there exists one with the
     * same Id, then an exception should not be thrown.
     * 
     * @throws Exception If something goes wrong
     */
    @Test
    public void testUpdateTransactionUsingTheSameId() throws Exception {
    	this.stubRepositoryToReturnExistingTransaction();
    	final Transaction newTransaction = TransactionUtil.createTransaction();
    	final Transaction updateTransaction = TransactionUtil.updateTransaction(newTransaction);
        try {
        	transactionService.updateTransaction(newTransaction.getId(), updateTransaction);
        } catch (TransactionDoesNotExist e) {
        	Assert.assertNull(e.getCause());
        	Assert.assertTrue(e.getMessage().isEmpty());
        }
        Mockito.verify(transactionRepository, Mockito.never()).findById(newTransaction.getId());
    }
    
    /**
     * This test should update a transaction and given that there exists none with the
     * same Id, then an exception should be thrown.
     * 
     * @throws Exception If something goes wrong
     */
    @Test
    public void testUpdateTransactionUsingADifferentId() throws Exception {
    	this.stubRepositoryToReturnExistingTransaction();
    	final Transaction newTransaction = TransactionUtil.createTransaction();
    	final Long newId = newTransaction.getId() + 1L;
    	final Transaction updateTransaction = TransactionUtil.updateTransaction(newTransaction);
        try {
        	transactionService.updateTransaction(newId, updateTransaction);        	
        	Assert.fail("Expected exception here!");        	
        } catch (TransactionDoesNotExist e) {
        	String message = "There is not a transaction with id=" + newId;
        	Assert.assertNotNull(e.getStackTrace());
        	Assert.assertEquals(e.getMessage(), message);
        }
        Mockito.verify(transactionRepository, Mockito.never()).findById(newId);
    }
    
    /**
     * This test should list all transactions and given that there exists some,
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
     * This test should list all transactions and given that there exists none,
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
        Assert.assertEquals(0, list.size());
        Mockito.verify(transactionRepository, Mockito.times(1)).findAll();
    }       
    
    /**
     * Stub method.
     */
    private Transaction stubRepositoryToReturnTransactionOnSave() {
    	final Transaction transaction = TransactionUtil.createTransaction();
        //Mockito.when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(transaction);
        Mockito.doReturn(transaction).when(transactionRepository).save(Mockito.any(Transaction.class));
        return transaction;
    }
    
    /**
     * Stub method.
     */
    private void stubRepositoryToReturnExistingTransactions(int howMany) {
        final List<Transaction> transactions = TransactionUtil.transactionList(howMany);
    	//Mockito.when(transactionRepository.findAll()).thenReturn(transactions);
    	Mockito.doReturn(transactions).when(transactionRepository).findAll();
    }
    
    /**
     * Stub method.
     */
    private void stubRepositoryToReturnExistingTransaction() {
    	final Transaction transaction = TransactionUtil.createTransaction();
    	//Mockito.when(transactionRepository.getOne(transaction.getId())).thenReturn(transaction);
        Mockito.doReturn(transaction).when(transactionRepository).getOne(transaction.getId());
    }

    @After
    public void tearDown(){
    }
}
