package com.qsoft;
/**
 * Created with IntelliJ IDEA.
 * User: Quynh
 * Date: 11/06/2013
 * Time: 11:21
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountDTO {
    private final String accountNumber;
    private double balance;
    public long openTimestamp;

    public BankAccountDTO(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    public double getBalance(){
        return balance;
    }

    public void changeBalance(double amount) {
        balance+=amount;
    }

    public void deposit(String accountNumber,double amount){
        return;
    }

}
