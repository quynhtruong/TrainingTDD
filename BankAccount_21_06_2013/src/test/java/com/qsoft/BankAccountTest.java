package com.qsoft;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: quynhtq
 * Date: 6/21/13
 * Time: 1:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountTest {
    @Mock
    BankAccountDAO mockBankAccountDAO;
    @Mock
    TransactionDAO mockTransactionDAO;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        reset(mockBankAccountDAO);
        BankAccount.bankAccountDAO = mockBankAccountDAO;
        Transaction.transactionDAO = mockTransactionDAO;
    }
    @Test
    public void testOpenAccount(){
        BankAccountDTO bankAccountDTO = BankAccount.openAccount("123456789");
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockBankAccountDAO,times(1)).save(argumentCaptor.capture());
        assertEquals("123456789",argumentCaptor.getValue());
        assertEquals(0.0, bankAccountDTO.getBalance());
        assertEquals("123456789",bankAccountDTO.getAccountNumber());
    }

    @Test
    public void testGetAccount(){
        BankAccountDTO bankAccountDTO = BankAccount.openAccount("123456789");
        BankAccountDTO bankAccountDTO1 = BankAccount.getAccount("123456789");
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        assertEquals(bankAccountDTO.getAccountNumber(),bankAccountDTO1.getAccountNumber());
        assertEquals(bankAccountDTO.getBalance(),bankAccountDTO1.getBalance());
        verify(mockBankAccountDAO,times(1)).getAccount(argumentCaptor.capture());
        assertEquals("123456789",argumentCaptor.getValue());
    }

    @Test
    public void testDeposit(){
        BankAccountDTO bankAccountDTO = BankAccount.openAccount("123456789");
        bankAccountDTO = BankAccount.deposit("123456789",100.0);

        assertEquals("123456789",bankAccountDTO.getAccountNumber());
        assertEquals(bankAccountDTO.getBalance(),100.0);
        ArgumentCaptor<String> stringArgumentCaptor  = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Double> doubleArgumentCaptor = ArgumentCaptor.forClass(Double.class);
        verify(mockBankAccountDAO,times(1)).deposit(stringArgumentCaptor.capture(),doubleArgumentCaptor.capture());
        assertEquals("123456789",stringArgumentCaptor.getValue());
        assertEquals(100.0,doubleArgumentCaptor.getValue());
    }

    @Test
    public void testWhetherTransactionSaveDeposit(){
        Calendar mockCalendar = mock(Calendar.class);
        BankAccountDTO bankAccountDTO = BankAccount.openAccount("123456789");
        bankAccountDTO = BankAccount.deposit("123456789",100.0);
        when(mockCalendar.getTimeInMillis()).thenReturn(10000L);

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<Double> doubleArgumentCaptor  = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<String> stringArgumentCaptor1 = ArgumentCaptor.forClass(String.class);
        verify(mockTransactionDAO,times(1)).save(stringArgumentCaptor.capture(),longArgumentCaptor.capture(),doubleArgumentCaptor.capture(),stringArgumentCaptor1.capture());

    }
}
