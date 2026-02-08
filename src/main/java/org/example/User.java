package org.example;

// Создать класс Пользователь с полями Фамилия,
// имя, id, pin, поле с типом класса Счёт.

public class User {
    private Integer id;
    private String surname;
    private String name;
    private Integer pin;
    private BankAccount bankAccount;

    public User(Integer _id, String _surname, String _name,
                Integer _pin, BankAccount _bankAccount)  {
        id = _id;
        surname = _surname;
        name = _name;
        pin = _pin;
        bankAccount = _bankAccount;
    }
}
