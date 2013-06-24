package com.qsoft;

import java.util.Calendar;

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
}
