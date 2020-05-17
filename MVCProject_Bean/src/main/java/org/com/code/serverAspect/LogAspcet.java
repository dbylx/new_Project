package org.com.code.serverAspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
//@Component
public class LogAspcet {

    @Pointcut("execution(* org.com.code.servers.*.*(..))")
    public void DoAspect(){

    }

    @Before("DoAspect()")
    public void beforeService(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature() + " start execute");
        System.out.println("time:" + new Date());
    }


    @After("DoAspect()")
    public void afterService(JoinPoint joinPoint){

        System.out.println(joinPoint.getSignature() + " end execute\n");
        System.out.println("time:" + new Date());
    }

}
