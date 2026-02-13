package org.example;

import java.math.BigDecimal;

/*При выполнении всех операций реализовывается проверка pin.
При снятии и переводе денег необходимо учесть, достаточно ли средств.
В основной программе(или в отдельном файле) создать класс Банкомат банка MyBank,
реализующий интерфейс банкомат. В этом классе необходимо представить реализацию всех методов интерфейса
Банкомат. При снятии денег со счета необходимо сделать проверку,
является ли клиент банкомата клиентом банка MyBank.
Если да, то при снятии денег комиссии на операцию нет, если нет, то комиссия 2 % от суммы. */

public class Atm implements IAtm {
    public Atm() {
    }

    // Проверка пина
    private void checkPin(User user, int pin) {
        if (user.getPin() != pin) {
            throw new IllegalArgumentException("Неверный PIN");
        }
    }

    @Override
    public BigDecimal getBalance(User user, int pin) {
        checkPin(user, pin);
        return user.getBankAccount().getBalance();
    }

    @Override
    public void increaseDeposit(User user, int pin, BigDecimal amount) {
        checkPin(user, pin);

        if ((amount == null || amount.compareTo(BigDecimal.ZERO) <= 0)) {
            throw new IllegalArgumentException("Сумма пополнения должна быть больше 0");
        }

        user.getBankAccount().deposit(amount);
    }

    @Override
    public void withdraw(User user, int pin, BigDecimal amount) {
        checkPin(user, pin);

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Сумма должна быть больше 0");
        }

        BankAccount account = user.getBankAccount();
        BigDecimal finalAmount = amount;

        if (!account.getBankName().equals("MyBank")) {
            BigDecimal commission = amount.multiply(new BigDecimal("0.02"));
            finalAmount = amount.add(commission);
        }

        if (account.getBalance().compareTo(finalAmount) < 0) {
            throw new IllegalArgumentException(
                    "Недостаточно средств. Баланс: " + account.getBalance()
            );
        }

        account.withdraw(finalAmount);
    }

    @Override
    public void transfer(User fromUser, int pin, BankAccount toAccount, BigDecimal amount) {
        checkPin(fromUser, pin);

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Сумма перевода должна быть больше 0");
        }

        BankAccount fromAccount = fromUser.getBankAccount();

        BigDecimal finalAmount = amount;

        // проверка достаточности средств с учётом комиссии
        if (fromAccount.getBalance().compareTo(finalAmount) < 0) {
            throw new IllegalArgumentException(
                    "Недостаточно средств. Баланс: " + fromAccount.getBalance()
            );
        }

        // списываем с учётом комиссии
        fromAccount.withdraw(amount);

        // перечесляем другому пользователю
        toAccount.deposit(amount);
    }
}
