package org.example;

// Создать класс Пользователь с полями Фамилия,
// имя, id, pin, поле с типом класса Счёт.

public class User {
    private int id;
    private String surname;
    private String name;
    private int pin;
    private BankAccount bankAccount;

    public User(int _id, String _surname, String _name,
                int _pin, BankAccount _bankAccount)  {
        id = _id;
        surname = _surname;
        name = _name;
        pin = _pin;
        bankAccount = _bankAccount;

        if (pin < 1000 || pin > 9999)
            throw new IllegalArgumentException("PIN должен быть 4-значным");
    }

    public int getId(){
        return id;
    }

    public String getSurname(){
        return surname;
    }

    public String getName(){
        return name;
    }

    public int getPin(){
        return pin;
    }

    public BankAccount getBankAccount(){
        return bankAccount;
    }
}
