package org.example;

/*Создать интерфейс банкомат, в котором описаны следующие методы:
        	узнать баланс,
        	пополнить счёт,
        	снять деньги со счета,
        	перевести деньги с одного счета на другой.
*/

import java.math.BigDecimal;

public interface IAtm {

    BigDecimal getBalance(User user, int pin);

    void increaseDeposit(User user, int pin, BigDecimal amount);

    void withdraw(User user, int pin, BigDecimal amount);

    void transfer(User fromUser, int pin, BankAccount toAccount, BigDecimal amount);
}

