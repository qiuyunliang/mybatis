package me.ciu.o;

import lombok.extern.slf4j.Slf4j;
import me.ciu.o.dao.mapper.DeviceMapper;
import me.ciu.o.dao.mapper.UserMapper;
import me.ciu.o.entity.Device;
import me.ciu.o.entity.User;
import me.ciu.o.kafka.KafkaProducer;
import me.ciu.o.service.impl.TransactionServiceImpl;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Slf4j
@MapperScan("me.ciu.o.dao.mapper")
@SpringBootApplication
@EnableTransactionManagement(mode = AdviceMode.PROXY)
public class Application implements ApplicationRunner {

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    UserMapper userMapper;

    @Autowired
    DeviceMapper deviceMapper;

    @Autowired
    KafkaProducer producer;

    @Autowired
    TransactionServiceImpl transactionServiceImpl;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        User newUser = User.builder().userName("lin").password("xxx").salary(Money.of(CurrencyUnit.of("CNY"), 30.0)).age(30).build();
        userMapper.insert(newUser);
        User user = userMapper.selectUser(1);

        Device device = deviceMapper.selectDevice(2);
        log.info(device.toString());

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                Device device = new Device();
                device.setId(1000);
                deviceMapper.insertDevice(device);
                log.info("save: " + deviceMapper.selectDevice(10));
                transactionStatus.setRollbackOnly();
            }
        });

        try {
            deviceMapper.deleteDevice(10001);
            transactionServiceImpl.invokeInsertThenRollbackByInstance();
        } catch (Exception e) {
            log.info("invokeInsertThenRollbackByInstance: " + deviceMapper.selectDevice(10001));
        }
        try {
            deviceMapper.deleteDevice(10001);
            transactionServiceImpl.invokeInsertThenRollbackByAopContext();
        } catch (Exception e) {
            log.info("invokeInsertThenRollbackByAopContext: " + deviceMapper.selectDevice(10001));
        }
        try {
            deviceMapper.deleteDevice(10001);
            transactionServiceImpl.invokeInsertThenRollbackByDirectlyCallMethod();
        } catch (Exception e) {
            log.info("invokeInsertThenRollbackByDirectlyCallMethod: " + deviceMapper.selectDevice(10001));
        }
        try {
            deviceMapper.deleteDevice(10001);
            transactionServiceImpl.invokeInsertThenRollbackAddTransactional();
        } catch (Exception e) {
            log.info("invokeInsertThenRollbackAddTransactional: " + deviceMapper.selectDevice(10001));
        }
        try {
            deviceMapper.deleteDevice(10001);
            transactionServiceImpl.insertThenRollback();
        } catch (Exception e) {
            log.info("insertThenRollback: " + deviceMapper.selectDevice(10001));
        }
        log.info("query: " + deviceMapper.selectDevice(10001));
    }
}