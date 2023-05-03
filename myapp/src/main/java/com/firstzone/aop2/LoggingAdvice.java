package com.firstzone.aop2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(2)
public class LoggingAdvice {
	@Pointcut("execution(* add(int)) || execution(* add(int, int))") // 여러 개의 함수에 적용하고자 할 때 ||로 연결
	public void targetMethod2() {} // 실제로 실행되지는 않음! 

	// After: 주업무 후 수행
	@After("targetMethod2()") 
	public void afterTarget() {
		System.out.println("***** After 주업무 후 수행 *****");
	}
	
	// AfterReturning: return 후 수행
	@AfterReturning("targetMethod2()")
	public void afterReturn() {
		System.out.println("-*-*-* @AfterReturning *-*-*-");
	}
	
	// AfterThrowing: 예외처리 후 수행
	@AfterThrowing("targetMethod2()")
	public void afterThrow() {
		System.out.println("-*-*-* @AfterThrowing *-*-*-");
	}
	
	// Before: 주업무 전 수행
	@Before("targetMethod2()")
	public void beforeTarget(JoinPoint joinPoint) {
		System.out.println("----- Before 주업무 전 수행: " + joinPoint.getSignature().getName() + " -----");
	}
	
	// Around: 주업무 전, 후에 수행
	@Around("targetMethod2()") // Pointcut에 해당하는 지점이 오면 함수를 실행
	public Object aroundTarget2(ProceedingJoinPoint joinPoint) throws Throwable {
		// 주업무 수행 전
		System.out.println("----- Around 주업무 전 수행: " + joinPoint.getSignature().getName() + " -----");
		
		Object obj = joinPoint.proceed(); // 주업무 수행
		
		// 주업무 수행 후
		System.out.println("***** Around 주업무 후 수행: " + joinPoint.getSignature().getName() + " *****");
		
		return obj;
	}
}
