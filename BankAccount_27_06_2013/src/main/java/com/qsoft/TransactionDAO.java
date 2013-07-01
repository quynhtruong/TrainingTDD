package com.qsoft;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: quynhtq
 * Date: 6/28/13
 * Time: 1:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionDAO
{
    public void save(String accountNumber, Long timeStamp, Double amount, String description)
    {
        //To change body of created methods use File | Settings | File Templates.
    }

    public List<Object> getTransactionOccurred(String accountNumber)
    {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public List<Object> getTransactionOccurred(String accountNumber, long startTime, long endTime)
    {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public List<Object> getNClosestTransactions(String accountNumber, long n)
    {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}
