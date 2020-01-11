package me.ciu.o.service.impl;

import me.ciu.o.dao.mapper.DeviceMapper;
import me.ciu.o.entity.Device;
import me.ciu.o.service.ITransactionService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private DeviceMapper mapper;

    @Autowired
    private ITransactionService service;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertThenRollback() throws Exception {
        Device device = new Device();
        device.setId(10001);
        mapper.insertDevice(device);
        throw new Exception();
    }

    @Override
    public void invokeInsertThenRollbackByDirectlyCallMethod() throws Exception {
        insertThenRollback();
    }

    @Override
    public void invokeInsertThenRollbackByInstance() throws Exception {
        service.insertThenRollback();
    }

    @Override
    public void invokeInsertThenRollbackByAopContext() throws Exception {
        ((ITransactionService) (AopContext.currentProxy())).insertThenRollback();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void invokeInsertThenRollbackAddTransactional() throws Exception {
        insertThenRollback();
    }
}