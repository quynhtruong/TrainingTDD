package com.qsoft.persistence.dao.impl;

import com.qsoft.persistence.dao.BankAccountDAO;
import com.qsoft.persistence.model.BankAccountEntity;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: quynhtq
 * Date: 6/27/13
 * Time: 1:44 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class BankAccountDAOImpl implements BankAccountDAO
{

    public void save(BankAccountEntity bankAccountModel)
    {
        //To change body of created methods use File | Settings | File Templates.
    }

    public BankAccountEntity getAccount(String accountNumber)
    {
        return null;
    }

}
