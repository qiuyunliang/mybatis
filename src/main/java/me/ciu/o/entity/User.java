package me.ciu.o.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.joda.money.Money;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Alias("user")
public class User implements Serializable {
    long id;
    String userName;
    String password;
    Money salary;
    int age;
}