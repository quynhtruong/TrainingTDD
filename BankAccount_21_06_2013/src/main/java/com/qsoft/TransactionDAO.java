package com.qsoft;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: quynhtq
 * Date: 6/22/13
 * Time: 2:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionDAO {
    public void save(String capture, Long capture1, Double capture2, String capture3)
    {
        //bla bla bla
    }

    public List<BankAccountDTO> getTransactionOccured(String accountNumber) {
        return null;
    }

    public List<BankAccountDTO> getTransactionOccured(String accountNumber, Long startTime, Long endTime) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public List<BankAccountDTO> getNClosestTransactions(Long aLong)
    {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}
