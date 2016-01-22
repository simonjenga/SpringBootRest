package com.spring.boot.rest.service.exception;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Assert;

/**
 * Test case for the exception classes:
 *
 * {@link AmountDoesNotExist},
 * {@link TransactionAlreadyExists},
 * {@link TransactionDoesNotExist},
 * {@link TypeDoesNotExist}.
 * 
 * @author Simon Njenga
 * @version 0.1
 */
 @RunWith(MockitoJUnitRunner.class)
public class ExceptionTest {

    /**
     * This is a test for the exception class {@link AmountDoesNotExist}.
     * 
     * @throws Exception If something goes wrong
     */
    @Test
    public void testAmountDoesNotExist() throws Exception {
        String message = "AmountDoesNotExist";
        AmountDoesNotExist exception = new AmountDoesNotExist(message);
        Assert.assertNull(exception.getCause());
        Assert.assertEquals(exception.getMessage(), message);
    }

    /**
     * This is a test for the exception class {@link TransactionAlreadyExists}.
     * 
     * @throws Exception If something goes wrong
     */
    @Test
    public void testTransactionAlreadyExists() throws Exception {
        String message = "TransactionAlreadyExists";
        String causeMessage = "RootCause";
        Exception rootCause = new Exception(causeMessage);
        TransactionAlreadyExists exception = new TransactionAlreadyExists(message, rootCause);
        Assert.assertNotNull(exception.getCause());
        Assert.assertEquals(exception.getCause(), rootCause);
        Assert.assertEquals(exception.getMessage(), message);
    }

    /**
     * This is a test for the exception class {@link TransactionDoesNotExist}.
     * 
     * @throws Exception If something goes wrong
     */
    @Test
    public void testTransactionDoesNotExist() throws Exception {
        String message = "TransactionDoesNotExist";
        String causeMessage = "RootCause";
        Exception rootCause = new Exception(causeMessage);
        TransactionDoesNotExist exception = new TransactionDoesNotExist(message, rootCause);
        Assert.assertNotNull(exception.getCause());
        Assert.assertEquals(exception.getCause(), rootCause);
        Assert.assertEquals(exception.getMessage(), message);

        //Check printStackTrace
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintWriter pw = new PrintWriter(baos);
        exception.printStackTrace(pw);
        pw.flush();
        pw.close();

        String stackTrace = new String(baos.toByteArray());

        Assert.assertFalse(stackTrace.indexOf(message) == -1);
        Assert.assertFalse(stackTrace.indexOf(rootCause.getClass().getName()) == -1);
        Assert.assertFalse(stackTrace.indexOf(causeMessage) == -1);
    }

    /**
     * This is a test for the exception class {@link TypeDoesNotExist}.
     * 
     * @throws Exception If something goes wrong
     */
    @Test
    public void testTypeDoesNotExist() throws Exception {
        String message = "TypeDoesNotExist";
        TypeDoesNotExist exception = new TypeDoesNotExist(message);
        Assert.assertTrue(exception.getCause() == null);
        Assert.assertFalse(exception.getCause() != null);		
        Assert.assertNull(exception.getCause());
        Assert.assertEquals(exception.getMessage(), message);
    }
}