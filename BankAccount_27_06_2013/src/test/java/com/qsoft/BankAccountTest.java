package com.qsoft;

import com.qsoft.business.impl.BankAccountServiceImpl;
import com.qsoft.business.impl.TransactionServiceImpl;
import com.qsoft.persistence.dao.impl.TransactionDAOImpl;
import com.qsoft.persistence.model.BankAccountModel;
import com.qsoft.persistence.dao.impl.BankAccountDAOImpl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: quynhtq
 * Date: 6/27/13
 * Time: 1:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountTest
{
    @Mock
    BankAccountDAOImpl mockBankAccountDAO;
    @Mock
    TransactionDAOImpl mocTransactionDAO;

    ApplicationContext applicationContext;
    @BeforeClass
    public void loadApplicationContext()
    {
        applicationContext = new ClassPathXmlApplicationContext("testContext.xml");
    }

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        reset(mockBankAccountDAO);
        reset(mocTransactionDAO);
        BankAccountServiceImpl.bankAccountDAO = mockBankAccountDAO;
        TransactionServiceImpl.transactionDAO = mocTransactionDAO;
    }

    @Test
    public void testOpenAccount()
    {
        BankAccountModel bankAccountDTO = BankAccountServiceImpl.openAccount("123456789");
        ArgumentCaptor<BankAccountModel> argumentCaptor = ArgumentCaptor.forClass(BankAccountModel.class);
        verify(mockBankAccountDAO, times(1)).save(argumentCaptor.capture());
        assertEquals("123456789", argumentCaptor.getValue().getAccountNumber());
        assertEquals((Double) 0.0, argumentCaptor.getValue().getBalance());
    }

    @Test
    public void testGetAccount()
    {
        BankAccountModel bankAccountDTO = BankAccountServiceImpl.openAccount("123456789");
        BankAccountModel bankAccountDTO1 = BankAccountServiceImpl.getAccount("123456789");

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockBankAccountDAO, times(1)).getAccount(argumentCaptor.capture());
        assertEquals(bankAccountDTO.getAccountNumber(), bankAccountDTO1.getAccountNumber());
        assertEquals(bankAccountDTO.getBalance(), bankAccountDTO1.getBalance());
    }

    @Test
    public void testDeposit()
    {
        BankAccountModel bankAccountDTO = BankAccountServiceImpl.openAccount("123456789");
        BankAccountModel bankAccountDTO1 = BankAccountServiceImpl.deposit("123456789", 100.0, "just a test of deposit process");
        ArgumentCaptor<BankAccountModel> argumentCaptor = ArgumentCaptor.forClass(BankAccountModel.class);
        verify(mockBankAccountDAO, times(2)).save(argumentCaptor.capture());
        assertEquals((Double) 100.0, bankAccountDTO1.getBalance());
    }

    @Test
    public void testWhetherTransactionSavedOrNotInDepositProcess()
    {
        BankAccountModel bankAccountDTO = BankAccountServiceImpl.openAccount("123456789");
        Calendar mockCalendar = mock(Calendar.class);
        BankAccountServiceImpl.calendar = mockCalendar;
        when(mockCalendar.getTimeInMillis()).thenReturn(1000L);
        BankAccountModel bankAccountDTO1 = BankAccountServiceImpl.deposit("123456789", 100.0, "just a test of deposit process");

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<Double> doubleArgumentCaptor = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<String> stringArgumentCaptor1 = ArgumentCaptor.forClass(String.class);

        verify(mocTransactionDAO, times(1)).save(stringArgumentCaptor.capture(), longArgumentCaptor.capture(), doubleArgumentCaptor.capture(), stringArgumentCaptor1.capture());
        assertEquals("123456789", stringArgumentCaptor.getValue());
        assertEquals((Double) 100.0, doubleArgumentCaptor.getValue());
        assertEquals("just a test of deposit process", stringArgumentCaptor1.getValue());
        assertEquals((Long) 1000L, longArgumentCaptor.getValue());
    }

    @Test
    public void testWithdraw()
    {
        BankAccountModel bankAccountDTO = BankAccountServiceImpl.openAccount("123456789");
        bankAccountDTO = BankAccountServiceImpl.deposit("123456789", 100.0, "just a test of deposit process");
        bankAccountDTO = BankAccountServiceImpl.withdraw("123456789", 50.0, "just a test for withdraw process");

        ArgumentCaptor<BankAccountModel> argumentCaptor = ArgumentCaptor.forClass(BankAccountModel.class);
        verify(mockBankAccountDAO, times(3)).save(argumentCaptor.capture());
        assertEquals((Double) 50.0, bankAccountDTO.getBalance());
    }

    @Test
    public void testWhetherTransactionSavedOrNotInWithdrawProcess()
    {
        BankAccountModel bankAccountDTO = BankAccountServiceImpl.openAccount("123456789");
        Calendar mockCalendar = mock(Calendar.class);
        BankAccountServiceImpl.calendar = mockCalendar;
        when(mockCalendar.getTimeInMillis()).thenReturn(1000L);
        bankAccountDTO = BankAccountServiceImpl.deposit("123456789", 100.0, "just a test of deposit process");
        bankAccountDTO = BankAccountServiceImpl.withdraw("123456789", 50.0, "just a test for withdraw process");


        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<Double> doubleArgumentCaptor = ArgumentCaptor.forClass(Double.class);
        ArgumentCaptor<String> stringArgumentCaptor1 = ArgumentCaptor.forClass(String.class);

        verify(mocTransactionDAO, times(2)).save(stringArgumentCaptor.capture(), longArgumentCaptor.capture(), doubleArgumentCaptor.capture(), stringArgumentCaptor1.capture());
        assertEquals("123456789", stringArgumentCaptor.getValue());
        Double aDouble = -50.0d;
        assertEquals(aDouble, doubleArgumentCaptor.getValue());
        assertEquals("just a test for withdraw process", stringArgumentCaptor1.getValue());
        assertEquals((Long) 1000L, longArgumentCaptor.getValue());
    }

    @Test
    public void testGetTransactionOccurred()
    {
        List<Object> list = BankAccountServiceImpl.getTransactionOccurred("123456789");

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        verify(mocTransactionDAO, times(1)).getTransactionOccurred(stringArgumentCaptor.capture());
    }

    @Test
    public void testGetTransactionWithAnIntervalOfTime()
    {
        List<Object> list = BankAccountServiceImpl.getTransactionOccurred("123456789", 1L, 2L);

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<Long> longArgumentCaptor1 = ArgumentCaptor.forClass(Long.class);

        verify(mocTransactionDAO, times(1)).getTransactionOccurred(stringArgumentCaptor.capture(), longArgumentCaptor.capture(), longArgumentCaptor1.capture());
        assertEquals("123456789", stringArgumentCaptor.getValue());
        assertEquals((Long) 1L, longArgumentCaptor.getValue());
        assertEquals((Long) 2L, longArgumentCaptor1.getValue());
    }

    @Test
    public void testGetNClosestTransactions()
    {
        List<Object> list = BankAccountServiceImpl.getNClosestTransactions("123456789", 3L);
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);

        verify(mocTransactionDAO, times(1)).getNClosestTransactions(stringArgumentCaptor.capture(), longArgumentCaptor.capture());
        assertEquals("123456789", stringArgumentCaptor.getValue());
        assertEquals((Long) 3L, longArgumentCaptor.getValue());
    }

}
