package me.ciu.o.service;

public interface ITransactionService {
    void insertThenRollback() throws Exception;

    void invokeInsertThenRollbackByAopContext() throws Exception;

    void invokeInsertThenRollbackByInstance() throws Exception;

    void invokeInsertThenRollbackByDirectlyCallMethod() throws Exception;

    void invokeInsertThenRollbackAddTransactional() throws Exception;
}