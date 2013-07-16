package com.qsoft.persistence.dao.impl;

import com.qsoft.persistence.dao.BankAccountDAO;
import com.qsoft.persistence.model.BankAccountEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        return null;
    }

    @Override
    public void create(BankAccountEntity bankAccountEntity)
    {

    }
}
