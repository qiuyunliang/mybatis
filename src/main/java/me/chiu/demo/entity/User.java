package me.chiu.demo.entity;

import org.apache.ibatis.type.Alias;
import org.joda.money.Money;

@Alias("user")
public class User {
    long id;
    String userName;
    String password;
    Money salary;
    int age;

    public User(long id, String userName, String password, Money salary, int age) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.salary = salary;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}