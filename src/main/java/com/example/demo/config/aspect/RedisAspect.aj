package com.example.demo.config.aspect;

import com.example.demo.common.RRException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RedisAspect implements Ordered{

    @Value(value = "${spring.redis.open: false}")
    private boolean open;

    @Pointcut(value = "execution(* com.example.demo.common.RedisUtils.*(..))")
    public void RedisConnection(){

    }

    @Around("RedisConnection()")
    public Object AroundMethod(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        if(open){
            try{
                result = point.proceed();
            }catch (Exception e){
                throw new RRException("Redis服务异常");
            }
        }
        return result;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
