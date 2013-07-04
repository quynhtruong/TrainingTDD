package com.qsoft.persistence.model;

/**
 * Created with IntelliJ IDEA.
 * User: quynhtq
 * Date: 6/27/13
 * Time: 1:42 PM
 * To change this template use File | Settings | File Templates.
 */

public class BankAccountModel
{
    private String accountNumber;
    private double balance;
    private Long openTimestamp;

    public BankAccountModel(String accountNumber, double balance)
    {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    public Double getBalance()
    {
        return balance;
    }

    public void setBalance(Double balance)
    {
        this.balance = balance;
    }

    public Long getOpenTimestamp()
    {
        return openTimestamp;
    }

    public void setOpenTimestamp(Long openTimestamp)
    {
        this.openTimestamp = openTimestamp;
    }
}
