package ru.job4j.thread;
/**
 * Класс USER.
 */
public class User {
    private int id;
    private String name;
    private int amount;

    public User() {
    }

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public static User of(String name) {
        User user = new User();
        user.name = name;
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public void sendAmount(int coins) {
        this.amount -= coins;
    }

    public void takeAmount(int coins) {
        this.amount += coins;
    }

    public void setName(String name) {
        this.name = name;
    }
}