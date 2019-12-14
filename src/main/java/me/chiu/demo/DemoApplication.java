package me.chiu.demo;

import me.chiu.demo.dao.SqlSessionUtil;
import me.chiu.demo.dao.mapper.DeviceMapper;
import me.chiu.demo.dao.mapper.UserMapper;
import me.chiu.demo.entity.Device;
import me.chiu.demo.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        try (SqlSession session = SqlSessionUtil.getInstance().openSession()) {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            User newUser = new User(3,"leon", "xxxx", Money.of(CurrencyUnit.of("CNY"), 20.0), 30);
            userMapper.insert(newUser);

            User user = userMapper.selectUser(1);
            System.out.println(user.toString());

            DeviceMapper deviceMapper = session.getMapper(DeviceMapper.class);
            Device device = session.selectOne("me.chiu.demo.dao.mapper.DeviceMapper.selectDevice", 2);
            System.out.println(device.toString());
            deviceMapper.insertDevice(new Device());
            session.commit();
        }
    }
}