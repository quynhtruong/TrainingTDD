package com.qsoft.business.impl;

import com.qsoft.business.TransactionService;
import com.qsoft.persistence.dao.TransactionDAO;

/**
 * Created with IntelliJ IDEA.
 * User: quynhtq
 * Date: 6/28/13
 * Time: 1:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionServiceImpl implements TransactionService
{
    public TransactionDAO transactionDAO;
    @Override
    public void setTransactionDAO(TransactionDAO transactionDAO)
    {
        this.transactionDAO = transactionDAO;
    }
}
