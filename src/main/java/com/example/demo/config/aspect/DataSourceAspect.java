package com.example.demo.config.aspect;

import com.example.demo.config.DataSourceNames;
import com.example.demo.config.DynamicDataSource;
import com.example.demo.config.annotation.useDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 数据源切面处理类
 */
@Aspect
@Component
public class DataSourceAspect implements Ordered{

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut(value = "@annotation(com.example.demo.config.annotation.useDataSource)")
    public void setDataSource(){

    }

    @Around("setDataSource()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
            Method method = signature.getMethod();

            useDataSource ds = method.getAnnotation(useDataSource.class);
            if(ds == null){
                DynamicDataSource.setDataSource(DataSourceNames.FIRST);
                logger.info("set datasource is " + DataSourceNames.FIRST);
            }else {
                DynamicDataSource.setDataSource(ds.name());
                logger.info("set datasource is " + ds.name());
            }

            try {
                return point.proceed();
            } finally {
                DynamicDataSource.clearDataSource();
            logger.info("clean datasource");
        }
    }


    @Override
    public int getOrder() {
        return 1;
    }
}
