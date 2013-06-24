package com.qsoft;

/**
 * Created with IntelliJ IDEA.
 * User: quynhtq
 * Date: 6/21/13
 * Time: 1:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccount {
    public static BankAccountDAO bankAccountDAO;

    public static BankAccountDTO openAccount(String accountNumber) {
        BankAccountDTO bankAccountDTO = new BankAccountDTO(accountNumber,0);
        bankAccountDAO.save(accountNumber);
        return bankAccountDTO;
    }

    public static BankAccountDTO getAccount(String accountNumber) {
        BankAccountDTO bankAccountDTO = bankAccountDAO.getAccount(accountNumber);
        return new BankAccountDTO(accountNumber,0);
    }

    public static BankAccountDTO deposit(String accountNumber,double amout,String description) {
        BankAccountDTO bankAccountDTO  = bankAccountDAO.deposit(accountNumber,amout);
        Transaction.save(accountNumber,amout,description);
        return new BankAccountDTO(accountNumber,amout);
    }
}
