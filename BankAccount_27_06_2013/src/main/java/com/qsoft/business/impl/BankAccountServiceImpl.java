package com.qsoft.business.impl;

import com.qsoft.business.BankAccountService;
import com.qsoft.persistence.dao.BankAccountDAO;
import com.qsoft.persistence.model.BankAccountModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: quynhtq
 * Date: 6/27/13
 * Time: 1:44 PM
 * To change this template use File | Settings | File Templates.
 */

public class BankAccountServiceImpl implements BankAccountService
{
    @Autowired
    public BankAccountDAO bankAccountDAO;
    public static Calendar calendar = Calendar.getInstance();

    public BankAccountModel openAccount(String accountNumber)
    {
        BankAccountModel bankAccountDTO = new BankAccountModel(accountNumber, 0.0);
        bankAccountDAO.save(bankAccountDTO);
        return bankAccountDTO;
    }

    public BankAccountModel getAccount(String accountNumber)
    {
        BankAccountModel bankAccountDTO = bankAccountDAO.getAccount(accountNumber);
        return new BankAccountModel(accountNumber, 0.0);
    }

    public BankAccountModel deposit(String accountNumber, double amount, String description)
    {
        BankAccountModel bankAccountDTO = bankAccountDAO.getAccount(accountNumber);
        bankAccountDTO = new BankAccountModel(accountNumber, 0.0);
        bankAccountDTO.setBalance(bankAccountDTO.getBalance() + amount);
        bankAccountDAO.save(bankAccountDTO);
        TransactionServiceImpl.transactionDAO.save(accountNumber, calendar.getTimeInMillis(), amount, description);
        return bankAccountDTO;
    }

    public BankAccountModel withdraw(String accountNumber, double amount, String description)
    {
        BankAccountModel bankAccountDTO = bankAccountDAO.getAccount(accountNumber);
        bankAccountDTO = new BankAccountModel(accountNumber, 100.0);
        bankAccountDTO.setBalance(bankAccountDTO.getBalance() - amount);
        bankAccountDAO.save(bankAccountDTO);
        TransactionServiceImpl.transactionDAO.save(accountNumber, calendar.getTimeInMillis(), -amount, description);
        return bankAccountDTO;
    }

    public List<Object> getTransactionOccurred(String accountNumber)
    {
        List<Object> transactionList = TransactionServiceImpl.transactionDAO.getTransactionOccurred(accountNumber);
        return transactionList;
    }

    public List<Object> getTransactionOccurred(String accountNumber, long startTime, long endTime)
    {
        List<Object> transactionList = TransactionServiceImpl.transactionDAO.getTransactionOccurred(accountNumber, startTime, endTime);
        return transactionList;
    }

    public List<Object> getNClosestTransactions(String accountNumber, long n)
    {
        List<Object> transactionList = TransactionServiceImpl.transactionDAO.getNClosestTransactions(accountNumber, n);
        return transactionList;
    }
}

