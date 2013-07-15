package com.qsoft.business.impl;

import com.qsoft.business.BankAccountService;
import com.qsoft.persistence.dao.BankAccountDAO;
import com.qsoft.persistence.model.BankAccountEntity;
import com.qsoft.persistence.model.TransactionEntity;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: quynhtq
 * Date: 6/27/13
 * Time: 1:44 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class BankAccountServiceImpl implements BankAccountService
{
    public BankAccountDAO bankAccountDAO;
    public TransactionServiceImpl transactionService;

    public void setTransactionService(TransactionServiceImpl transactionService)
    {
        this.transactionService = transactionService;
    }

    public static Calendar calendar = Calendar.getInstance();

    public void setBankAccountDAO(BankAccountDAO bankAccountDAO)
    {
        this.bankAccountDAO = bankAccountDAO;
    }

    public BankAccountEntity openAccount(String accountNumber)
    {
        BankAccountEntity bankAccountDTO = new BankAccountEntity(accountNumber, 0.0);
        bankAccountDAO.save(bankAccountDTO);
        return bankAccountDTO;
    }

    public BankAccountEntity getAccount(String accountNumber)
    {
        BankAccountEntity bankAccountDTO = bankAccountDAO.getAccountByAccountNumber(accountNumber);
        return new BankAccountEntity(accountNumber, 0.0);
    }

    public BankAccountEntity deposit(String accountNumber, double amount, String description)
    {
        BankAccountEntity bankAccountDTO = bankAccountDAO.getAccountByAccountNumber(accountNumber);
        bankAccountDTO = new BankAccountEntity(accountNumber, 0.0);
        bankAccountDTO.setBalance(bankAccountDTO.getBalance() + amount);
        bankAccountDAO.save(bankAccountDTO);
        transactionService.transactionDAO.save(accountNumber, calendar.getTimeInMillis(), amount, description);
        return bankAccountDTO;
    }

    public BankAccountEntity withdraw(String accountNumber, double amount, String description)
    {
        BankAccountEntity bankAccountDTO = bankAccountDAO.getAccountByAccountNumber(accountNumber);
        bankAccountDTO = new BankAccountEntity(accountNumber, 100.0);
        bankAccountDTO.setBalance(bankAccountDTO.getBalance() - amount);
        bankAccountDAO.save(bankAccountDTO);
        transactionService.transactionDAO.save(accountNumber, calendar.getTimeInMillis(), -amount, description);
        return bankAccountDTO;
    }

    public List<TransactionEntity> getTransactionOccurred(String accountNumber)
    {
        List<TransactionEntity> transactionList = transactionService.transactionDAO.getTransactionOccurred(accountNumber);
        return transactionList;
    }

    public List<TransactionEntity> getTransactionOccurred(String accountNumber, long startTime, long endTime)
    {
        List<TransactionEntity> transactionList = transactionService.transactionDAO.getTransactionOccurred(accountNumber, startTime, endTime);
        return transactionList;
    }

    public List<TransactionEntity> getNClosestTransactions(String accountNumber, long n)
    {
        List<TransactionEntity> transactionList = transactionService.transactionDAO.getNClosestTransactions(accountNumber, n);
        return transactionList;
    }
}

