package com.example.fa_blog_study.common.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

/**
 * AOP实现日志功能
 */
@Component
@Aspect
public class LogAspect {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(com.example.fa_blog_study.common.aop.LogAnnotation)")
    public void pc(){}

    @Around("pc()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        long before = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long time = System.currentTimeMillis() - before;
        this.recordLog(joinPoint,time);
        return result;
    }

    public void recordLog(ProceedingJoinPoint joinPoint,long time){

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        log.info("=====================log start================================");
        log.info("module:{}",logAnnotation.module());
        log.info("operation:{}",logAnnotation.operator());

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.info("request method:{}",className + "." + methodName + "()");

        Object[] args = joinPoint.getArgs();
        String params = JSON.toJSONString(args[0]);
        log.info("params:{}",params);



        log.info("excute time : {} ms",time);
        log.info("=====================log end================================");

    }
}
