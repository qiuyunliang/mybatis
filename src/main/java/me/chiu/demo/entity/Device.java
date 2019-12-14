package me.chiu.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Alias("device")
public class Device {
    long id;
    String name;
    Date borrowTime;
    long userId;
}