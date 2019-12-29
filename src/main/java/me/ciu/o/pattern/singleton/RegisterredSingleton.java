package me.ciu.o.pattern.singleton;

public class RegisterredSingleton {
    private static class SingletonHolder {
        private static final RegisterredSingleton INSTANCE = new RegisterredSingleton();
    }

    private RegisterredSingleton() {
    }

    public static final RegisterredSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}