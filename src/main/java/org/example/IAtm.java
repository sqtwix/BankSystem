package org.example;

public interface IAtm {
    String getBalance();
    void increaseBalance();
    void takeMoneyFromDeposit();
    void transferMoney(BankAccount account1, BankAccount account2);
}
