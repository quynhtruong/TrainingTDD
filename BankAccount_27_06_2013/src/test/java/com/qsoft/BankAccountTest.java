package com.qsoft;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    BankAccountDAO mockBankAccountDAO;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        reset(mockBankAccountDAO);
        BankAccount.bankAccountDAO = mockBankAccountDAO;
    }

    @Test
    public void testOpenAccount()
    {
        BankAccountDTO bankAccountDTO = BankAccount.openAccount("123456789");
        ArgumentCaptor<BankAccountDTO> argumentCaptor = ArgumentCaptor.forClass(BankAccountDTO.class);
        verify(mockBankAccountDAO, times(1)).save(argumentCaptor.capture());
        assertEquals("123456789", argumentCaptor.getValue().getAccountNumber());
        assertEquals((Double) 0.0, argumentCaptor.getValue().getBalance());
    }

    @Test
    public void testGetAccount()
    {
        BankAccountDTO bankAccountDTO = BankAccount.openAccount("123456789");
        BankAccountDTO bankAccountDTO1 = BankAccount.getAccount("123456789");

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockBankAccountDAO, times(1)).getAccount(argumentCaptor.capture());
        assertEquals(bankAccountDTO.getAccountNumber(), bankAccountDTO1.getAccountNumber());
        assertEquals(bankAccountDTO.getBalance(), bankAccountDTO1.getBalance());
    }

    @Test
    public void testDeposit()
    {
        BankAccountDTO bankAccountDTO = BankAccount.openAccount("123456789");
        BankAccountDTO bankAccountDTO1 = BankAccount.deposit("123456789", 100.0, "just a test of deposit process");
        ArgumentCaptor<BankAccountDTO> argumentCaptor = ArgumentCaptor.forClass(BankAccountDTO.class);
        verify(mockBankAccountDAO, times(2)).save(argumentCaptor.capture());
        assertEquals((Double)100.0,bankAccountDTO1.getBalance());
    }
}
