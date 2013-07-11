package com.qsoft.business.impl;

import com.qsoft.business.TransactionService;
import com.qsoft.persistence.dao.TransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: quynhtq
 * Date: 6/28/13
 * Time: 1:39 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class TransactionServiceImpl implements TransactionService
{
    @Autowired
    public TransactionDAO transactionDAO;

    @Override
    public void setTransactionDAO(TransactionDAO transactionDAO)
    {
        this.transactionDAO = transactionDAO;
    }
}
