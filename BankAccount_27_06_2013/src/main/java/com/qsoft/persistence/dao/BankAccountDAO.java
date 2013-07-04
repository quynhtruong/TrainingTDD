package com.qsoft.persistence.dao;

import com.qsoft.persistence.model.BankAccountModel;

/**
 * User: quynhtq
 * Date: 7/4/13
 * Time: 1:58 PM
 */
public interface BankAccountDAO
{
    public void save(BankAccountModel bankAccountModel);
    public BankAccountModel getAccount(String accountNumber);
}
