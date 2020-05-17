package org.com.code.serverAspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.com.code.tool.TranscationTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class TransactionAspect {


    private TranscationTool transcationTool;


    @Autowired
    public TransactionAspect(TranscationTool transcationTool){
        this.transcationTool = transcationTool;
    }

    @Pointcut("execution(* org.com.code.servers.*.*(..))")
    public void DoTransaction(){
    }

    @AfterThrowing(pointcut = "DoTransaction()", throwing = "e")
    public void rollback(JoinPoint point, Exception e) {
        e.printStackTrace();
        transcationTool.rollbackTranscation();
    }

    @Before("DoTransaction()")
    public void startTranscation() {
        transcationTool.beginTranscation();
    }

    @AfterReturning("DoTransaction()")
    public void endTranscation() {
        transcationTool.commitTranscation();
    }


}
