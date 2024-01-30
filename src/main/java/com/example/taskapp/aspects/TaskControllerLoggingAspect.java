package com.example.taskapp.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TaskControllerLoggingAspect {

    @Before("execution(* com.example.taskapp.controllers.TaskController.*(..))")
    public void logBeforeMethodExecution(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        System.out.println("Entering method: " + methodName);
    }

    @AfterReturning(pointcut = "execution(* com.example.taskapp.controllers.TaskController.*(..))", returning = "result")
    public void logAfterMethodExecution(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().toShortString();
        System.out.println("Exiting method: " + methodName);
    }
}
