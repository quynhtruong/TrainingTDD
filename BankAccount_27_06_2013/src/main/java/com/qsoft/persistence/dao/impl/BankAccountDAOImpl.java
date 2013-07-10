package com.qsoft.persistence.dao.impl;

import com.qsoft.persistence.dao.BankAccountDAO;
import com.qsoft.persistence.model.BankAccountEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: quynhtq
 * Date: 6/27/13
 * Time: 1:44 PM
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public class BankAccountDAOImpl implements BankAccountDAO
{
    @PersistenceContext
    public EntityManager entityManager;

    public void save(BankAccountEntity bankAccountModel)
    {
        //To change body of created methods use File | Settings | File Templates.
    }

    public BankAccountEntity getAccountByAccountNumber(String accountNumber)
    {
        Query query = entityManager.createQuery("select o from BankAccountEntity o where o.accountNumber = :qAccountNumber", BankAccountEntity.class);
        query.setParameter("qAccountNumber", accountNumber);
        List<BankAccountEntity> list = query.getResultList();
        if (list.size() == 0)
        {
            return null;
        }
        else
        {
            return list.get(0);
        }
    }

    @Override
    public BankAccountEntity getAccountById(Long id)
    {
        Query query = entityManager.createQuery("select o from BankAccountEntity o where o.id = :qid", BankAccountEntity.class);
        query.setParameter("qid", id);
        List<BankAccountEntity> list = query.getResultList();
        if (list.size() == 0)
        {
            return null;
        }
        else
        {
            return list.get(0);
        }
    }

    @Override
    public List<BankAccountEntity> getLAllAccount()
    {
        List<BankAccountEntity> result = new ArrayList<BankAccountEntity>();
        result.add(new BankAccountEntity("0123456781",1D,12345678L));
        result.add(new BankAccountEntity("0123456782",10D,12345678L));
        return result;
    }

}
