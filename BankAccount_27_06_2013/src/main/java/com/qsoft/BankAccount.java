package com.qsoft;

/**
 * Created with IntelliJ IDEA.
 * User: quynhtq
 * Date: 6/27/13
 * Time: 1:44 PM
 * To change this template use File | Settings | File Templates.
 */


public class BankAccount {
    public static BankAccountDAO bankAccountDAO;
    public static BankAccountDTO openAccount(String accountNumber) {
        BankAccountDTO bankAccountDTO = new BankAccountDTO(accountNumber,0.0);
        bankAccountDAO.save(bankAccountDTO);
        return bankAccountDTO;
    }

    public static BankAccountDTO getAccount(String accountNumber)
    {
        BankAccountDTO bankAccountDTO = bankAccountDAO.getAccount(accountNumber);
        return new BankAccountDTO(accountNumber,0.0);
    }

    public static BankAccountDTO deposit(String accountNumber, double amount, String description)
    {
        bankAccountDAO.save(new BankAccountDTO("1212",141));
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}

