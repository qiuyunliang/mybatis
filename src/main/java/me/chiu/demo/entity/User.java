package me.chiu.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.joda.money.Money;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Alias("user")
public class User {
    long id;
    String userName;
    String password;
    Money salary;
    int age;
}