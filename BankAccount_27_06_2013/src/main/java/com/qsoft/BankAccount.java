package com.qsoft;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: quynhtq
 * Date: 6/27/13
 * Time: 1:44 PM
 * To change this template use File | Settings | File Templates.
 */


public class BankAccount
{
    public static BankAccountDAO bankAccountDAO;
    public static Calendar calendar = Calendar.getInstance();

    public static BankAccountDTO openAccount(String accountNumber)
    {
        BankAccountDTO bankAccountDTO = new BankAccountDTO(accountNumber, 0.0);
        bankAccountDAO.save(bankAccountDTO);
        return bankAccountDTO;
    }

    public static BankAccountDTO getAccount(String accountNumber)
    {
        BankAccountDTO bankAccountDTO = bankAccountDAO.getAccount(accountNumber);
        return new BankAccountDTO(accountNumber, 0.0);
    }

    public static BankAccountDTO deposit(String accountNumber, double amount, String description)
    {
        BankAccountDTO bankAccountDTO = bankAccountDAO.getAccount(accountNumber);
        bankAccountDTO = new BankAccountDTO(accountNumber, 0.0);
        bankAccountDTO.setBalance(bankAccountDTO.getBalance() + amount);
        bankAccountDAO.save(bankAccountDTO);
        Transaction.transactionDAO.save(accountNumber,calendar.getTimeInMillis(),amount,description);
        return bankAccountDTO;
    }

    public static BankAccountDTO withdraw(String accoutNumber, double amount, String description)
    {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}

