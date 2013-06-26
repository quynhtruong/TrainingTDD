package com.qsoft;

import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: quynhtq
 * Date: 6/22/13
 * Time: 2:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class Transaction {
    public static TransactionDAO transactionDAO;
    public static Calendar calendar = Calendar.getInstance();

    public static void save(String accountNumber, double amout, String description)
    {
        transactionDAO.save(accountNumber,calendar.getTimeInMillis(),amout,description);
    }

    public static List<BankAccountDTO> getTransactionOccured(String accountNumber) {
        List<BankAccountDTO> bankAccountDTOs = transactionDAO.getTransactionOccured(accountNumber);
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public static List<BankAccountDTO> getTransactionOccured(String accountNumber, Long startTime, Long endTime) {
        List<BankAccountDTO> bankAccountDTOs = transactionDAO.getTransactionOccured(accountNumber,startTime,endTime);
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public static List<BankAccountDTO> getNClosestTransactions(Long n)
    {
        List<BankAccountDTO> bankAccountDTOs = transactionDAO.getNClosestTransactions(n);
        return null;
    }
}
