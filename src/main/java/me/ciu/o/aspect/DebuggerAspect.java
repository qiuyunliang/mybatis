package me.ciu.o.aspect;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class DebuggerAspect {

    @Pointcut("@annotation(me.ciu.o.aspect.Debugger)")
    public void Timer() {
    }

    @Around("Timer()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        long time = System.currentTimeMillis() - beginTime;
        print(point, time);
        return result;
    }

    private void print(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        Object[] args = joinPoint.getArgs();
        String params = JSONObject.toJSONString(args);
        log.info("---------------------------------------------------------------------------------------------------");
        log.info("Cost: " + time + "ms" + ", class: " + className + ", method: " + methodName + ", parameter: " + params);
        log.info("---------------------------------------------------------------------------------------------------");
    }
}