package com.qsoft;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertEquals;
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

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        BankAccount.bankAccountDAO = mockBankAccountDAO;
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

}
