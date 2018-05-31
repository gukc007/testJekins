package com.test;

/**
 * Created by admin on 2017/2/23.
 */
public class FruitName {
    String name;
    String password;

    public FruitName() {
    }

    public FruitName(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "FruitName{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
