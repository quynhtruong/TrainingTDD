package com.qsoft.persistence.dao;

import java.util.List;

/**
 * User: quynhtq
 * Date: 7/4/13
 * Time: 1:59 PM
 */
public interface TransactionDAO
{
    public void save(String accountNumber, Long timeStamp, Double amount, String description);
    public List<Object> getTransactionOccurred(String accountNumber);
    public List<Object> getTransactionOccurred(String accountNumber, long startTime, long endTime);
    public List<Object> getNClosestTransactions(String accountNumber, long n);

}
