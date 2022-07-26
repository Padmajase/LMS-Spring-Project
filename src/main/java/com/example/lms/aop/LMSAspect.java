package com.example.lms.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LMSAspect {

    @Before("execution(* com.example.lms.service.UserService.loginUser(..))")
    public String beforeLogin(JoinPoint joinPoint){
        System.out.println("fetching login details ");
        return "details are fetched";
    }



    @After("execution(* com.example.lms.service.UserService.loginUser(..))")
    public String afterLogin(){
        System.out.println("verifying user ");
        return "user verified ";
    }

    @AfterReturning("execution(* com.example.lms.service.UserService.loginUser(..))")
    public String afterReturnLogin(JoinPoint joinPoint){
        System.out.println("user logging ");
        return "user has been logged in";
    }
}
