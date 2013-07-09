package com.qsoft.business;

import com.qsoft.business.impl.TransactionServiceImpl;
import com.qsoft.persistence.dao.BankAccountDAO;
import com.qsoft.persistence.model.BankAccountEntity;

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

    public List<Object> getTransactionOccurred(String accountNumber);

    public List<Object> getTransactionOccurred(String accountNumber, long startTime, long endTime);

    public List<Object> getNClosestTransactions(String accountNumber, long n);

}
