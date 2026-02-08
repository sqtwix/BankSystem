package org.example;

// Создать класс Счёт с полями название(вклада), баланс,
// поле с типом класса Пользователь, название банка

import java.math.BigDecimal;

public class BankAccount {

    private String depositName;
    private BigDecimal balance;
    private User owner;
    private String bankName;

    public BankAccount(String depositName,
                       BigDecimal balance,
                       User owner,
                       String bankName) {

        if (balance == null || balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Некорректный баланс");
        }

        this.depositName = depositName;
        this.balance = balance;
        this.owner = owner;
        this.bankName = bankName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getBankName() {
        return bankName;
    }

    public User getOwner() {
        return owner;
    }

    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Некорректная сумма");


        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Некорректная сумма");

        if (balance.compareTo(amount) < 0)
            throw new IllegalArgumentException("Недостаточно средств");

        balance = balance.subtract(amount);
    }

}
