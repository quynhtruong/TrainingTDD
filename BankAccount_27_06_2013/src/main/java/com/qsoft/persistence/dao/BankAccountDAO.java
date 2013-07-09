package com.qsoft.persistence.dao;

import com.qsoft.persistence.model.BankAccountEntity;

/**
 * User: quynhtq
 * Date: 7/4/13
 * Time: 1:58 PM
 */
public interface BankAccountDAO
{
    public BankAccountEntity getAccountByAccountNumber(String accountNumber);
}
