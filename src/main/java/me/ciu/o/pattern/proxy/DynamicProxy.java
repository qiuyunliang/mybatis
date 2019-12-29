package me.ciu.o.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {

    public interface Subject {
        void execute();
    }

    public static class DynamicInvocationHandler implements InvocationHandler {
        private Object object;

        public DynamicInvocationHandler(Object object) {
            this.object = object;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return method.invoke(object, args);
        }
    }

    public static class RealSubject implements Subject {
        @Override
        public void execute() {
            System.out.println("RealSubject.execute");
        }
    }

    public static void main(String[] args) {
        Subject subject = new RealSubject();
        DynamicInvocationHandler handler = new DynamicInvocationHandler(subject);
        Subject proxy = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(), new Class[]{Subject.class}, handler);
        proxy.execute();
    }
}