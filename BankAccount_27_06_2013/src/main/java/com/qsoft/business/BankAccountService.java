package com.qsoft.business;

import com.qsoft.business.impl.TransactionServiceImpl;
import com.qsoft.persistence.dao.BankAccountDAO;
import com.qsoft.persistence.model.BankAccountEntity;
import com.qsoft.persistence.model.TransactionEntity;

import java.util.List;

/**
 * User: quynhtq
 * Date: 7/4/13
 * Time: 1:50 PM
 */
public interface BankAccountService
{
    public void setBankAccountDAO(BankAccountDAO bankAccountDAO);

    public void setTransactionService(TransactionServiceImpl transactionService);

    public BankAccountEntity openAccount(String accountNumber);

    public BankAccountEntity getAccount(String accountNumber);

    public BankAccountEntity deposit(String accountNumber, double amount, String description);

    public BankAccountEntity withdraw(String accountNumber, double amount, String description);

    public List<TransactionEntity> getTransactionOccurred(String accountNumber);

    public List<TransactionEntity> getTransactionOccurred(String accountNumber, long startTime, long endTime);

    public List<TransactionEntity> getNClosestTransactions(String accountNumber, long n);

}
