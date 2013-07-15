package com.qsoft.persistence.dao.impl;

import com.qsoft.persistence.dao.TransactionDAO;
import com.qsoft.persistence.model.TransactionEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        TransactionEntity transactionEntity = new TransactionEntity(accountNumber,timeStamp,amount,description);
        entityManager.persist(transactionEntity);
    }

    public List<TransactionEntity> getTransactionOccurred(String accountNumber)
    {
        Query query = entityManager.createQuery("select o from TransactionEntity o where o.accountNumber = :qAccountNumber order by o.timestamp desc ", TransactionEntity.class);
        query.setParameter("qAccountNumber", accountNumber);
        List<TransactionEntity> resultList = query.getResultList();
        return resultList;
    }

    public List<TransactionEntity> getTransactionOccurred(String accountNumber, Long startTime, Long endTime)
    {
        Query query = entityManager.createQuery("select o from TransactionEntity o where o.accountNumber = :qAccountNumber and o.timestamp >=:qStartTime and o.timestamp<=:qEndTime order by o.timestamp desc ", TransactionEntity.class);
        query.setParameter("qAccountNumber", accountNumber);
        query.setParameter("qStartTime",startTime);
        query.setParameter("qEndTime",endTime);
        return query.getResultList();
    }

    public List<TransactionEntity> getNClosestTransactions(String accountNumber, Long n)
    {
        Query query = entityManager.createQuery("select o from TransactionEntity o where o.accountNumber = :qAccountNumber order by o.timestamp desc ", TransactionEntity.class);
        query.setParameter("qAccountNumber", accountNumber);
        query.setMaxResults((int)(Long.parseLong(n.toString())));
        return query.getResultList();
    }
}
