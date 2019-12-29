package me.ciu.o.pattern.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    private Object target;  // 业务类对象，供代理方法中进行真正的业务方法调用

    public Object getInstance(Object target) {  // 相当于JDK动态代理中的绑定
        this.target = target;
        Enhancer enhancer = new Enhancer();  // 创建加强器，用来创建动态代理类
        enhancer.setSuperclass(this.target.getClass());  // 为加强器指定要代理的业务类，即为下面生成的代理类指定父类
        enhancer.setCallback(this);  // 设置回调：对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实现intercept()方法进行拦
        return enhancer.create();  // 创建动态代理类对象并返回
    }

    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {  // 实现回调方法
        System.out.println("before execute");
        proxy.invokeSuper(obj, args);
        return null;
    }
}