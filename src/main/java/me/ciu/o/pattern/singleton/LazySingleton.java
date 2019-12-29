package me.ciu.o.pattern.singleton;

public class LazySingleton {
    private static LazySingleton INSTANCE;

    public static LazySingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (LazySingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LazySingleton();
                }
            }
        }
        return INSTANCE;
    }
}