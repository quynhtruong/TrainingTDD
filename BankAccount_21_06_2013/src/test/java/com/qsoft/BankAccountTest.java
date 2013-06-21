package com.qsoft;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
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

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        reset(mockBankAccountDAO);
        BankAccount.bankAccountDAO = mockBankAccountDAO;
    }
    @Test
    public void testOpenAccount(){
        BankAccountDTO bankAccountDTO = BankAccount.openAccount("123456789");
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockBankAccountDAO,times(1)).save(argumentCaptor.capture());
        assertEquals("123456789",argumentCaptor.getValue());
        assertTrue(0 == bankAccountDTO.getBalance());
    }
}
