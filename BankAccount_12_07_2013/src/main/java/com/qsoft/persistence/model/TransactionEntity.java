package com.qsoft.persistence.model;

import javax.persistence.*;

/**
 * User: quynhtq
 * Date: 7/11/13
 * Time: 1:35 PM
 */
@Entity
@Table(name = "transaction")
@SequenceGenerator(name = "seq_id1", sequenceName = "seq_id1", initialValue = 1, allocationSize = 1)
public class TransactionEntity
{

    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_id1")
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "timestamp")
    private Long timestamp;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "description")
    private String description;

    public TransactionEntity()
    {
    }

    public TransactionEntity(String accountNumber, Long timestamp, Double amount, String description)
    {
        this.accountNumber = accountNumber;
        this.timestamp = timestamp;
        this.amount = amount;
        this.description = description;
    }


    public String getAccountNumber()
    {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    public Long getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(Long timestamp)
    {
        this.timestamp = timestamp;
    }

    public Double getAmount()
    {
        return amount;
    }

    public void setAmount(Double amount)
    {
        this.amount = amount;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }


    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }


}
