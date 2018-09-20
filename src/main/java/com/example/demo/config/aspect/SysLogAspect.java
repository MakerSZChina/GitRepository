package com.example.demo.config.aspect;

import com.example.demo.common.HttpContextUtils;
import com.example.demo.common.IPUtils;
import com.example.demo.config.annotation.SysLog;
import com.example.demo.modules.entity.SysLogEntity;
import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class SysLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(SysLogAspect.class);

    @Pointcut("@annotation(com.example.demo.config.annotation.SysLog)")
    public void SysLogInfo(){

    }

    @Around("SysLogInfo()")
    public Object AroundMethod(ProceedingJoinPoint point) throws Throwable {
         long begin = System.currentTimeMillis();
         Object result = point.proceed();
         long end = System.currentTimeMillis() - begin;
         saveLogs(point,end);
         return result;
    }

    private void saveLogs(ProceedingJoinPoint joinPoint, long end) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLogEntity sysLog = new SysLogEntity();
        //方法的注解对象
        SysLog syslog = method.getAnnotation(SysLog.class);
        if(syslog != null){
            //注解上的描述
            sysLog.setOperation(syslog.name());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        try{
            String params = new Gson().toJson(args[0]);
            sysLog.setParams(params);
        }catch (Exception e){

        }

        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));

        //用户名
//        String username = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
//        sysLog.setUsername(username);

        sysLog.setTime(end);
        sysLog.setCreateDate(new Date());
        //保存系统日志
//        sysLogService.insert(sysLog);
        if(logger.isDebugEnabled()){
            logger.info(new Gson().toJson(sysLog));
        }
    }


}
