package com.qsoft.persistence.dao;

import com.qsoft.persistence.model.TransactionEntity;

import java.util.List;

/**
 * User: quynhtq
 * Date: 7/4/13
 * Time: 1:59 PM
 */
public interface TransactionDAO
{
    public void save(String accountNumber, Long timeStamp, Double amount, String description);
    public List<TransactionEntity> getTransactionOccurred(String accountNumber);
    public List<TransactionEntity> getTransactionOccurred(String accountNumber, Long startTime, Long endTime);
    public List<TransactionEntity> getNClosestTransactions(String accountNumber, Long n);
}
