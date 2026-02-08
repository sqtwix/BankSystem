package org.example;

import java.math.BigDecimal;

public interface IAtm {

    BigDecimal getBalance(User user, int pin);

    void deposit(User user, int pin, BigDecimal amount);

    void withdraw(User user, int pin, BigDecimal amount);

    void transfer(User fromUser, int pin, BankAccount toAccount, BigDecimal amount);
}

