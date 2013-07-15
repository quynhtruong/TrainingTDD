package com.qsoft.persistence.dao.impl;

import com.qsoft.persistence.dao.TransactionDAO;
import com.qsoft.persistence.model.TransactionEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: quynhtq
 * Date: 6/28/13
 * Time: 1:43 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
@Transactional
public class TransactionDAOImpl implements TransactionDAO
{
    @PersistenceContext
    public EntityManager entityManager;

    public void save(String accountNumber, Long timeStamp, Double amount, String description)
    {
    }

    public List<TransactionEntity> getTransactionOccurred(String accountNumber)
    {
        return null;
    }

    public List<TransactionEntity> getTransactionOccurred(String accountNumber, Long startTime, Long endTime)
    {
        return null;
    }

    public List<TransactionEntity> getNClosestTransactions(String accountNumber, Long n)
    {
        return null;
    }
}
