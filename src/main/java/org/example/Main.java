package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("===РАБОТА БАНКА И БАНКОМАТА===");

            // СПИСОК АККАУНТОВ (СЧЁТОВ)
            BankAccount bankAccount1 = new BankAccount("Основной счёт",
                    BigDecimal.valueOf(1000),
                    "D bank");

            BankAccount bankAccount2 = new BankAccount("Базовый счёт",
                    BigDecimal.valueOf(1000),
                    "Green bank");

            BankAccount bankAccount3 = new BankAccount("Мой счёт",
                    BigDecimal.valueOf(1000),
                    "MyBank");

            BankAccount bankAccount4 = new BankAccount("Основной счёт",
                    BigDecimal.valueOf(1000),
                    "Yellow bank");

            BankAccount bankAccount5 = new BankAccount("Основной счёт",
                    BigDecimal.valueOf(1000),
                    "D bank");

            // СПИСОК ПОЛЬЗОВАТЕЛЕЙ
            User user1 = new User(1,
                    "Белов",
                    "Иван",
                    123456,
                    bankAccount1);

            User user2 = new User(2,
                    "Комаров",
                    "Иосиф",
                    123456,
                    bankAccount2);


            User user3 = new User(3,
                    "Демидов",
                    "Матвей",
                    123456,
                    bankAccount3);

            User user4 = new User(4,
                    "Иванова",
                    "Елена",
                    123456,
                    bankAccount4);

            User user5 = new User(5,
                    "Б",
                    "Елена",
                    123456,
                    bankAccount4);

            // Список для хранения пользователей
            List<User> userList = new ArrayList<>();

            // Инициализация объекта банкомат
            Atm atm = new Atm();

            // Бесконечный диалог
            while (true) {
                printActions();
                int choosenAction = sc.nextInt();

                if (choosenAction < 0 || choosenAction > 5) {
                    System.out.println("Ошибка! Введите корректный номер действия");
                    continue;
                }

                System.out.println("Какой вы пользователь (выберите цифру)");

                for(int i = 0; i < userList.size(); i++) {
                    System.out.println(i + ". " + userList.get(i).getSurname()
                        + userList.get(i).getName());
                }

                int choosenUser = sc.nextInt();
                User currentUser = userList.get(choosenUser);

                if (choosenUser < 0 || choosenUser > userList.size()) {
                    System.out.println("Ошибка! Введите корректный индекс пользователя!");
                    continue;
                }

                System.out.print("Введите пин-код от вашего счёта: ");
                int pin = sc.nextInt();

                switch (choosenAction) {
                    case 1:
                        BigDecimal balance = atm.getBalance(currentUser, pin);
                        System.out.println("Ваш баланс:" + balance);
                        break;
                    case 2:
                        System.out.println("===ПОПОЛНЕНИЕ БАЛАНСА===");
                        System.out.print("Введите сумму пополнения:");
                        BigDecimal amounте = BigDecimal.valueOf(sc.nextInt());
                        atm.increaseDeposit(currentUser, pin, amounте);
                        break;
                    case 3:
                        System.out.println("===СНЯТИЕ ДЕНЕГ===");
                        System.out.print("Введите сумму :");
                        BigDecimal withdraw_amounте = BigDecimal.valueOf(sc.nextInt());
                        atm.withdraw(currentUser, pin, withdraw_amounте );
                        break;
                    case 4:
                        System.out.println("===ПЕРЕВОД ДЕНЕГ===");
                        System.out.print("Введите сумму:");
                        BigDecimal transferAmount = BigDecimal.valueOf(sc.nextInt());

                        System.out.println("Какому пользователю вы хоитет перевести деньги (выберите цифру)");

                        for(int i = 0; i < userList.size(); i++) {
                            System.out.println(i + ". " + userList.get(i).getSurname()
                                    + userList.get(i).getName());
                        }

                        int toUserIndex = sc.nextInt();
                        User toUser = userList.get(toUserIndex);

                        atm.transfer(currentUser, pin, toUser.getBankAccount(), transferAmount);
                        break;
                    case 5:
                       break;
                    default:
                        System.out.println("Заглушка");
                }
            }
        }catch (Exception ex) {
            System.out.println("Ошибка в функции main:" + ex);
        }
    }

    public static void printActions(){
        System.out.println("Вы включили банкомат.Выберите действие:" +
                "\n 1. Получить баланс" +
                "\n 2. Пополнить счёт" +
                "\n 3. Снять деньги" +
                "\n 4. Перевести деньги на другой счёт" +
                "\n 5. ВЫЙТИ ИЗ ДИАЛОГА!" +
                "\n Выберите действие (введите цифру):");
    }
}
