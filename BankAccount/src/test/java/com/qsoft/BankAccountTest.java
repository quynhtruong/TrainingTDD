package com.qsoft;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created with IntelliJ IDEA.
 * User: Quynh
 * Date: 11/06/2013
 * Time: 11:13
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountTest {
    @Mock BankAccountDAO mockBankAccountDAO;
    @Mock TransactionDAO mockTransactionDAO;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        reset(mockBankAccountDAO);
        reset(mockTransactionDAO);
        BankAccount.bankAccountDAO = mockBankAccountDAO;
        Transaction.transactionDAO = mockTransactionDAO;
    }


    @Test
    public void testOpenAccount(){
        BankAccountDTO bankAccountDTO = BankAccount.openAccount("1234567890");
        assertEquals("1234567890",bankAccountDTO.getAccountNumber());
        assertEquals(0.0,bankAccountDTO.getBalance());
        ArgumentCaptor<BankAccountDTO> bankAccountDTOArgumentCaptor = ArgumentCaptor.forClass(BankAccountDTO.class);
        verify(mockBankAccountDAO,times(1)).save(bankAccountDTOArgumentCaptor.capture());
    }

    @Test
    public void testGetAccount(){
        BankAccountDTO bankAccountDTO = BankAccount.getAccount("1234567890");
        assertEquals("1234567890",bankAccountDTO.getAccountNumber());
        assertEquals(0.0,bankAccountDTO.getBalance());
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockBankAccountDAO,times(1)).getAccount(argumentCaptor.capture());
        assertEquals("1234567890",argumentCaptor.getValue());
    }

    @Test
    public void testDeposit(){
        BankAccount.deposit("1234567890",100.0,"just a small test");
        ArgumentCaptor<String> stringArgumentCaptor  = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Double> doubleArgumentCaptor  = ArgumentCaptor.forClass(Double.class);
        verify(mockBankAccountDAO,times(1)).deposit(stringArgumentCaptor.capture(),doubleArgumentCaptor.capture());
        assertEquals("1234567890", stringArgumentCaptor.getValue());
        assertEquals((Double) 100.0, doubleArgumentCaptor.getValue());
    }

    @Test
    public void testTransactionIsSavedInDeposit(){
        BankAccount.deposit("1234567890",100.0,"just a small test");
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Long> longArgumentCaptor  = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<Double> doubleArgumentCaptor = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<String> stringArgumentCaptor1 = ArgumentCaptor.forClass(String.class);
        verify(mockTransactionDAO, times(1)).save(stringArgumentCaptor.capture(), longArgumentCaptor.capture(), doubleArgumentCaptor.capture(), stringArgumentCaptor1.capture());
        assertEquals("1234567890",stringArgumentCaptor.getValue());
        assertEquals(100.0,doubleArgumentCaptor.getValue());
        assertEquals("just a small test",stringArgumentCaptor1.getValue());
    }


    @Test
    public void testWithdraw(){
        BankAccount.withdraw("1234567890",50.0,"just a small test second time");
        ArgumentCaptor<String> stringArgumentCaptor =  ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Double> doubleArgumentCaptor  = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<String> stringArgumentCaptor1  = ArgumentCaptor.forClass(String.class);
        verify(mockBankAccountDAO,times(1)).withdraw(stringArgumentCaptor.capture(),doubleArgumentCaptor.capture(),stringArgumentCaptor1.capture());
        assertEquals("1234567890", stringArgumentCaptor.getValue());
        assertEquals((Double)50.0,doubleArgumentCaptor.getValue());
        assertEquals("just a small test second time",stringArgumentCaptor1.getValue());
    }

    @Test
     public void testTransactionIsSavedInWithdraw(){
        BankAccount.withdraw("1234567890",50.0,"just a test of withdraw");
        ArgumentCaptor<String> stringArgumentCaptor  = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Long> longArgumentCaptor  = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<Double> doubleArgumentCaptor  = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<String> stringArgumentCaptor1  = ArgumentCaptor.forClass(String.class);
        verify(mockTransactionDAO,times(1)).save(stringArgumentCaptor.capture(),longArgumentCaptor.capture(),doubleArgumentCaptor.capture(),stringArgumentCaptor1.capture());
        assertEquals("1234567890",stringArgumentCaptor.getValue());
        assertEquals((Long)100000001L,longArgumentCaptor.getValue());
        assertEquals(-50.0,doubleArgumentCaptor.getValue());
        assertEquals("just a test of withdraw",stringArgumentCaptor1.getValue());
    }

    @Test
    public void testGetTransactionHistory(){
        List<Object> listTransaction = BankAccount.getTransactionsOccurred("1234567890");
        ArgumentCaptor<String> stringArgumentCaptor =  ArgumentCaptor.forClass(String.class);
        verify(mockTransactionDAO,times(1)).getTransactionHistory(stringArgumentCaptor.capture());
        assertEquals("1234567890",stringArgumentCaptor.getValue());
    }

    @Test
    public void testGetTransactionInAPeriodOfTime(){
        List<Object> listTransaction = BankAccount.getTransactionsOccurred("1234567890",0,100001);
        ArgumentCaptor<String> stringArgumentCaptor =  ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<Long> longArgumentCaptor1 = ArgumentCaptor.forClass(Long.class);
        verify(mockTransactionDAO,times(1)).getTransactionHistory(stringArgumentCaptor.capture(),longArgumentCaptor.capture(),longArgumentCaptor1.capture());
        assertEquals("1234567890",stringArgumentCaptor.getValue());
        assertEquals((Long)0L,longArgumentCaptor.getValue());
        assertEquals((Long)100001L,longArgumentCaptor1.getValue());
    }

    @Test
    public void testKClosestTransactions(){
        List<Object> list = BankAccount.getKClosestTransactins(3);
        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(mockTransactionDAO,times(1)).getKClosestTransactions(argumentCaptor.capture());
        assertEquals(new Integer(3),argumentCaptor.getValue());
    }

}
