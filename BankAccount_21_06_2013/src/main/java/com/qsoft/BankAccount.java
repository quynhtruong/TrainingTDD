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
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}
