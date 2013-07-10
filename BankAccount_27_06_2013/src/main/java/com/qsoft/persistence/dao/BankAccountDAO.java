package com.qsoft.persistence.dao;

import com.qsoft.persistence.model.BankAccountEntity;

import java.util.List;

/**
 * User: quynhtq
 * Date: 7/4/13
 * Time: 1:58 PM
 */
public interface BankAccountDAO
{
    public void save(BankAccountEntity bankAccountModel);

    public BankAccountEntity getAccountByAccountNumber(String accountNumber);

    public BankAccountEntity getAccountById(Long id);

    List<BankAccountEntity> getLAllAccount();

    void create(BankAccountEntity bankAccountEntity);

    void delete(BankAccountEntity bankAccountEntity);
}
