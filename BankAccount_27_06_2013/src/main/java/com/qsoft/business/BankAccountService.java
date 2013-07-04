package com.qsoft.business;

import com.qsoft.persistence.model.BankAccountModel;

import java.util.List;

/**
 * User: quynhtq
 * Date: 7/4/13
 * Time: 1:50 PM
 */
public interface BankAccountService
{
    public BankAccountModel openAccount(String accountNumber);

    public BankAccountModel getAccount(String accountNumber);

    public BankAccountModel deposit(String accountNumber, double amount, String description);

    public BankAccountModel withdraw(String accountNumber, double amount, String description);

    public List<Object> getTransactionOccurred(String accountNumber);

    public List<Object> getTransactionOccurred(String accountNumber, long startTime, long endTime);

    public List<Object> getNClosestTransactions(String accountNumber, long n);

}
