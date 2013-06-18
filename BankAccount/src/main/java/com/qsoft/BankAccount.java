package com.qsoft;

/**
 * Created with IntelliJ IDEA.
 * User: Quynh
 * Date: 11/06/2013
 * Time: 11:19
 * To change this template use File | Settings | File Templates.
 */
public class BankAccount {
    public static BankAccountDAO bankAccountDAO;

    public static BankAccountDTO openAccount(String accountName){
        BankAccountDTO bankAccountDTO = new BankAccountDTO(accountName);
        bankAccountDAO.save(bankAccountDTO);
        return bankAccountDTO;
    }

    public static BankAccountDTO getAccount(String s) {
        BankAccountDTO bankAccountDTO = new BankAccountDTO(s);
        bankAccountDAO.getAccount(s);
        return bankAccountDTO;
    }

    public static BankAccountDTO deposit(String accountNumber, double amount, String decription) {
        BankAccountDTO bankAccountDTO = new BankAccountDTO(accountNumber);
        bankAccountDAO.deposit(accountNumber,amount);
        Transaction.transactionDAO.save(accountNumber,100000001l,amount,decription);
        bankAccountDTO.changeBalance(amount);
        return bankAccountDTO;
    }

    public static BankAccountDTO withdraw(String accountNumber, double amount, String decription) {
        BankAccountDTO bankAccountDTO = new BankAccountDTO(accountNumber);
        bankAccountDAO.withdraw(accountNumber,amount,decription);
        Transaction.transactionDAO.save(accountNumber,100000001l,-amount,decription);
        return bankAccountDTO;

    }
}
