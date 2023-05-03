package com.firstzone.aop2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(3)
public class MyAdvice {
	@Pointcut("execution(* *(int, int))")
	public void targetMy() {}

	@Before("targetMy()")
	public void before(JoinPoint joinPoint) throws Throwable {
		System.out.println("------------ MyAdvice @Before -------------");
		System.out.println("함수명: " + joinPoint.getSignature().getName());
		System.out.println("-------------------------------------------");
	}

	@After("targetMy()")
	public void after(JoinPoint joinPoint) throws Throwable {
		System.out.println("------------ MyAdvice @After -------------");
		System.out.println("함수명: " + joinPoint.getSignature().getName());
		System.out.println("-------------------------------------------");
	}
	
	@Around("targetMy()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("*********** MyAdvice @Around 전 ***********");
		Object obj = joinPoint.proceed();
		System.out.println("*********** MyAdvice @Around 후 ***********");
		
		return obj;
	}
}
