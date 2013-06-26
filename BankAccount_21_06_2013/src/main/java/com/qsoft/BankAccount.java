package com.qsoft;

import java.util.List;

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
        Transaction.save(accountNumber, amout, description);
        return new BankAccountDTO(accountNumber,amout);
    }

    public static BankAccountDTO withDraw(String accountNumber, double amount, String description)
    {
        Transaction.save(accountNumber,-amount,description);
        BankAccountDTO bankAccountDTO  = bankAccountDAO.withDraw(accountNumber,amount);
        return new BankAccountDTO(accountNumber,amount);
    }

    public static List<BankAccountDTO> getTransactionOccoured(String accountNumber) {
        List<BankAccountDTO> bankAccountDTOs = Transaction.getTransactionOccured(accountNumber);
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
    public static List<BankAccountDTO> getTransactionOccoured(String accountNumber,Long startTime,Long endTime) {
        List<BankAccountDTO> bankAccountDTOs = Transaction.getTransactionOccured(accountNumber,startTime,endTime);
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public static List<BankAccountDTO> getNClosestTransactions(Long n)
    {
        List<BankAccountDTO> bankAccountDTOs  = Transaction.getNClosestTransactions(n);
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}
