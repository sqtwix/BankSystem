package org.example;

import java.util.*;
import java.math.BigDecimal;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        System.out.println("===РАБОТА БАНКА И БАНКОМАТА===");

        // СПИСОК АККАУНТОВ (СЧЁТОВ)
        BankAccount bankAccount1 = new BankAccount("Основной счёт",
                BigDecimal.valueOf(1000),
                "D bank" );

        BankAccount bankAccount2 = new BankAccount("Базовый счёт",
                BigDecimal.valueOf(1000),
                "Green bank" );

        BankAccount bankAccount3 = new BankAccount("Мой счёт",
                BigDecimal.valueOf(1000),
                "MyBank" );

        BankAccount bankAccount4 = new BankAccount("Основной счёт",
                BigDecimal.valueOf(1000),
                "Yellow bank" );

        BankAccount bankAccount5 = new BankAccount("Основной счёт",
                BigDecimal.valueOf(1000),
                "D bank" );




    }
}
