package me.chiu.demo;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import me.chiu.demo.dao.mapper.DeviceMapper;
import me.chiu.demo.dao.mapper.UserMapper;
import me.chiu.demo.entity.Device;
import me.chiu.demo.entity.User;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@MapperScan("me.chiu.demo.dao.mapper")
@SpringBootApplication
public class DemoApplication implements ApplicationRunner {

    @Autowired
    UserMapper userMapper;

    @Autowired
    DeviceMapper deviceMapper;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        User newUser = User.builder().userName("lin").password("xxx").salary(Money.of(CurrencyUnit.of("CNY"), 30.0)).age(30).build();
        userMapper.insert(newUser);
        User user = userMapper.selectUser(1);
        System.out.println(user.toString());
        user = userMapper.selectUser(1);

        Device device = deviceMapper.selectDevice(2);
        System.out.println(device.toString());
        log.debug(device.toString());
        deviceMapper.insertDevice(new Device());
    }
}