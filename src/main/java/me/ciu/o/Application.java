package me.ciu.o;

import lombok.extern.slf4j.Slf4j;
import me.ciu.o.dao.mapper.DeviceMapper;
import me.ciu.o.dao.mapper.UserMapper;
import me.ciu.o.entity.Device;
import me.ciu.o.entity.User;
import me.ciu.o.kafka.KafkaProducer;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@MapperScan("me.ciu.o.dao.mapper")
@SpringBootApplication
public class Application implements ApplicationRunner {

    @Autowired
    UserMapper userMapper;

    @Autowired
    DeviceMapper deviceMapper;

    @Autowired
    KafkaProducer producer;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
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

        producer.start();
    }
}