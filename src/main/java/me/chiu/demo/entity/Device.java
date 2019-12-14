package me.chiu.demo.entity;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("device")
public class Device {
    long id;
    String name;
    Date borrowTime;
    long userId;

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", borrowTime=" + borrowTime +
                ", userId=" + userId +
                '}';
    }
}