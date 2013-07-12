package com.qsoft.persistence.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: quynhtq
 * Date: 6/27/13
 * Time: 1:42 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "bank_account")
@SequenceGenerator(name = "seq_id1", sequenceName = "seq_id1", initialValue = 1, allocationSize = 1)
public class BankAccountEntity
{
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_id1")
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "balance")
    private double balance;
    @Column(name = "open_time_stamp")
    private Long openTimestamp;

    public BankAccountEntity(String accountNumber, Double balance, Long openTimestamp)
    {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.openTimestamp = openTimestamp;
    }

    public BankAccountEntity()
    {
    }

    public BankAccountEntity(String accountNumber, Double balance)
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
