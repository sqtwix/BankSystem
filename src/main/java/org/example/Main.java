package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

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
                    1234,
                    bankAccount1);

            User user2 = new User(2,
                    "Комаров",
                    "Иосиф",
                    4321,
                    bankAccount2);


            User user3 = new User(3,
                    "Демидов",
                    "Матвей",
                    1234,
                    bankAccount3);

            User user4 = new User(4,
                    "Иванова",
                    "Елена",
                    4321,
                    bankAccount4);

            User user5 = new User(5,
                    "Зайцева",
                    "Елена",
                    1234,
                    bankAccount5);

            // Список для хранения пользователей
            List<User> userList = new ArrayList<>();

            userList.add(user1);
            userList.add(user2);
            userList.add(user3);
            userList.add(user4);
            userList.add(user5);

            // Инициализация объекта банкомат
            Atm atm = new Atm();

            // Бесконечный диалог
            while (true) {
                try {

                    printActions();
                    int choosenAction = sc.nextInt();

                    if (choosenAction < 0 || choosenAction > 5) {
                        System.out.println("Ошибка! Введите корректный номер действия");
                        continue;
                    }

                    if (choosenAction == 5) {
                        System.out.println("Выход из банкомата...");
                        return;
                    }

                    System.out.println("Какой вы пользователь (выберите цифру)");

                    for (int i = 0; i < userList.size(); i++) {
                        System.out.println(i + ". " + userList.get(i).getSurname()
                                + " " + userList.get(i).getName());
                    }

                    int choosenUser = sc.nextInt();

                    if (choosenUser < 0 || choosenUser >= userList.size()) {
                        System.out.println("Ошибка! Введите корректный индекс пользователя!");
                        continue;
                    }

                    User currentUser = userList.get(choosenUser);

                    System.out.print("Введите пин-код от вашего счёта: ");
                    int pin = sc.nextInt();

                    switch (choosenAction) {
                        case 1:
                            BigDecimal balance = atm.getBalance(currentUser, pin);
                            System.out.println("ВАШ БАЛАНС:" + balance);
                            printSuccessMsg();
                            printLine();
                            break;

                        case 2:
                            System.out.println("===ПОПОЛНЕНИЕ БАЛАНСА===");

                            System.out.println("ВАШ БАЛАНС:" + atm.getBalance(currentUser, pin));

                            System.out.print("Введите сумму пополнения:");
                            BigDecimal amount = new BigDecimal(sc.next());
                            atm.increaseDeposit(currentUser, pin, amount);
                            System.out.println("ВАШ БАЛАНС:" + atm.getBalance(currentUser, pin));
                            printSuccessMsg();
                            printLine();
                            break;

                        case 3:
                            System.out.println("===СНЯТИЕ ДЕНЕГ===");

                            System.out.println("ВАШ БАЛАНС:" + atm.getBalance(currentUser, pin));

                            System.out.print("Введите сумму :");
                            BigDecimal withdraw_amounте = new BigDecimal(sc.next());
                            atm.withdraw(currentUser, pin, withdraw_amounте);
                            System.out.println("ВАШ БАЛАНС:" + atm.getBalance(currentUser, pin));
                            printSuccessMsg();
                            printLine();
                            break;

                        case 4:
                            System.out.println("===ПЕРЕВОД ДЕНЕГ===");

                            System.out.println("ВАШ БАЛАНС:" + atm.getBalance(currentUser, pin));

                            System.out.print("Введите сумму:");
                            BigDecimal transferAmount = new BigDecimal(sc.next());

                            System.out.println("Какому пользователю вы хоитет перевести деньги (выберите цифру)");

                            for (int i = 0; i < userList.size(); i++) {
                                System.out.println(i + ". " + userList.get(i).getSurname()
                                        + " " + userList.get(i).getName());
                            }

                            int toUserIndex = sc.nextInt();

                            if (toUserIndex < 0 || toUserIndex >= userList.size()) {
                                System.out.println("Ошибка! Некорректный индекс получателя.");
                                continue;
                            }

                            User toUser = userList.get(toUserIndex);

                            if (toUser == currentUser) {
                                System.out.println("Нельзя перевести деньги самому себе!");
                                continue;
                            }

                            atm.transfer(currentUser, pin, toUser.getBankAccount(), transferAmount);
                            System.out.println("ВАШ БАЛАНС:" + atm.getBalance(currentUser, pin));
                            printSuccessMsg();
                            printLine();
                            break;

                        default:
                            System.out.println("ОШИБКА! Вы ввели неверное действие!");
                    }
                } catch (Exception e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
            }
        }

    public static void printActions() {
        System.out.println("Вы включили банкомат.Выберите действие:" +
                "\n 1. Получить баланс" +
                "\n 2. Пополнить счёт" +
                "\n 3. Снять деньги" +
                "\n 4. Перевести деньги на другой счёт" +
                "\n 5. ВЫЙТИ ИЗ ДИАЛОГА!" +
                "\n Выберите действие (введите цифру):");
    }

    public static void printLine(){
        System.out.println("-".repeat(40));
    }

    public static void printSuccessMsg(){
        System.out.println("ДЕЙСТВИЕ УСПЕШНО ЗАВЕРШЕНО1");
    }
}
