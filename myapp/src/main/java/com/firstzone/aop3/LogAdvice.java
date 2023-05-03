package com.firstzone.aop3;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAdvice {
	@Pointcut("execution(* print(..))")
	public void cut1() {}
	
	@Pointcut("execution(* *Info())")
	public void cut2() {}
	
	@Around("cut1()")
	public Object aroundLog(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("[cut1 LoggingAdvice] 주 업무 전: " + joinPoint.getTarget().getClass().getSimpleName() + " " + joinPoint.getSignature().getName() + " ---");
		
		Object obj = joinPoint.proceed();
		
		System.out.println("[cut1 LoggingAdvice] 주 업무 후: " + Arrays.toString(joinPoint.getArgs()));
		
		return obj;
	}
	@Around("cut2()")
	public Object aroundLog2(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("[cut2 LoggingAdvice] 주 업무 전: " + joinPoint.getTarget().getClass().getSimpleName() + " " + joinPoint.getSignature().getName() + " ---");
		
		Object obj = joinPoint.proceed();
		
		System.out.println("[cut2 LoggingAdvice] 주 업무 후: " + Arrays.toString(joinPoint.getArgs()));
		
		return obj;
	}
}
