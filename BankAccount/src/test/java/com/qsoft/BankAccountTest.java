package com.qsoft;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
        BankAccount.bankAccountDAO = mockBankAccountDAO;
        Transaction.transactionDAO = mockTransactionDAO;
    }

    /**
     * Check the returned BankAccountDTO is the passed BankAccountDTO or not
     * Check whether save method of BankAccountDao is invoked or not
     */
    @Test
    public void testOpenAccount(){
        BankAccountDTO bankAccountDTO = BankAccount.openAccount("1234567890");
        assertEquals("1234567890",bankAccountDTO.getAccountNumber());
        assertEquals(0.0,bankAccountDTO.getBalance());
        ArgumentCaptor<BankAccountDTO> bankAccountDTOArgumentCaptor = ArgumentCaptor.forClass(BankAccountDTO.class);
        verify(mockBankAccountDAO,times(1)).save(bankAccountDTOArgumentCaptor.capture());
    }

    /**
        Check whether the accountNumbe and Balance and Date are correct
        Check whether getAccount of bankAccountDao is invoked
     */
    @Test
    public void testGetAccount(){
        BankAccountDTO bankAccountDTO = BankAccount.getAccount("1234567890");
        assertEquals("1234567890",bankAccountDTO.getAccountNumber());
        assertEquals(0.0,bankAccountDTO.getBalance());
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockBankAccountDAO,times(1)).getAccount(argumentCaptor.capture());
    }
    /**
     * Check whether the method deposit of mockBankAccountDao is invoke or not
     * Check wtether the  returned BankAccountDTO is the same to the passed BankAccountDTO
     */
    @Test
    public void testDeposit(){
        BankAccountDTO bankAccountDTO = BankAccount.deposit("1234567890",100.0,"just a small test");
        assertEquals("1234567890",bankAccountDTO.getAccountNumber());
        assertEquals(100.0,bankAccountDTO.getBalance());
        ArgumentCaptor<String> stringArgumentCaptor  = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Double> doubleArgumentCaptor  = ArgumentCaptor.forClass(Double.class);
        verify(mockBankAccountDAO,times(1)).deposit(stringArgumentCaptor.capture(),doubleArgumentCaptor.capture());

    }

    /**
     *  Check whether the returned result is correct
     *  Check whether the deposit of transactionDAO is invoked
     * Check whether the argument for deposit medthod of transactionDAO is correct
     */
    @Test
    public void testHistoryTransaction(){
        long dateOfTransaction = 100000001l;
        BankAccountDTO bankAccountDTO  = BankAccount.deposit("1234567890",100.0,"just a small test");
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Long> longArgumentCaptor  = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<Double> doubleArgumentCaptor = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<String> stringArgumentCaptor1 = ArgumentCaptor.forClass(String.class);
        verify(mockTransactionDAO, times(1)).save(stringArgumentCaptor.capture(), longArgumentCaptor.capture(), doubleArgumentCaptor.capture(), stringArgumentCaptor1.capture());
        assertEquals("1234567890",stringArgumentCaptor.getValue());
        assertTrue(longArgumentCaptor.getValue()==100000001);
        assertEquals(100.0,doubleArgumentCaptor.getValue());
        assertEquals("just a small test",stringArgumentCaptor1.getValue());
    }

    /*
     * Check whether the withdraw method of BankAcocountDAO is invoked
     * Check whether the transaction method of TransactionDao is invoked
     */
    @Test
    public void testWithdraw(){
        BankAccountDTO bankAccountDTO1  = BankAccount.withdraw("1234567890",50.0,"just a small test second time");
        ArgumentCaptor<String> stringArgumentCaptor =  ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Double> doubleArgumentCaptor  = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<String> stringArgumentCaptor1  = ArgumentCaptor.forClass(String.class);
        verify(mockBankAccountDAO, times(1)).withdraw(stringArgumentCaptor.capture(),doubleArgumentCaptor.capture(),stringArgumentCaptor1.capture());
        verify(mockBankAccountDAO,times(1)).withdraw(stringArgumentCaptor.capture(),doubleArgumentCaptor.capture(),stringArgumentCaptor1.capture());
    }

    /**
     * Change the deposit and withdraw medthod of TransactionDao into save()
     * test save() is invoked
     * test the arguments of save medthod in transactionDao are correct
     */
    @Test
     public void testSaveMethodOfTransaction(){
        //assume that deposit method was right
        BankAccountDTO bankAccountDTO = BankAccount.deposit("1234567890",100.0,"just a test of deposit");

        BankAccountDTO bankAccountDTO1 = BankAccount.withdraw("1234567890",50.0,"just a test of withdraw");
        ArgumentCaptor<String> stringArgumentCaptor  = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Long> longArgumentCaptor  = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<Double> doubleArgumentCaptor  = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<String> stringArgumentCaptor1  = ArgumentCaptor.forClass(String.class);
        List<String> listArgument1 = stringArgumentCaptor.getAllValues();
        List<Double> listArgument2 = doubleArgumentCaptor.getAllValues();
        List<String> listArgument3 = stringArgumentCaptor1.getAllValues();
        verify(mockTransactionDAO,times(2)).save(stringArgumentCaptor.capture(),longArgumentCaptor.capture(),doubleArgumentCaptor.capture(),stringArgumentCaptor1.capture());
        assertEquals("1234567890",stringArgumentCaptor.getAllValues().get(1));
        assertEquals((Long)100000001L,longArgumentCaptor.getAllValues().get(1));
        assertEquals(-50.0,doubleArgumentCaptor.getAllValues().get(1));
        assertEquals("just a test of withdraw",stringArgumentCaptor1.getAllValues().get(1));
    }

    /*
        test whether getTransactionsOccurred of transactionDao is invoked or not
        test the arguments for getTransactionsOccurred of transactionDAO
     */
    @Test
    public void testGetTransactionHistory(){
        List<Object> listTransaction = BankAccount.getTransactionsOccurred("1234567890");
        ArgumentCaptor<String> stringArgumentCaptor =  ArgumentCaptor.forClass(String.class);
        verify(mockTransactionDAO,times(1)).getTransactionHistory(stringArgumentCaptor.capture());
        assertEquals("1234567890",stringArgumentCaptor.getValue());
    }

    /*
        test whether getTransactionsOccurred of transactionDao is invoked or not
        test the arguments for getTransactionsOccurred of transactionDAO
     */
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
}
