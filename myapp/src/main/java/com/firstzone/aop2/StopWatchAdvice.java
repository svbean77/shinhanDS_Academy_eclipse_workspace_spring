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
import org.springframework.util.StopWatch;

@Component
@Aspect
@Order(1)
public class StopWatchAdvice {
//	@Pointcut("execution(* add(int)) || execution(* add(int, int))")
	@Pointcut("within(com.firstzone.aop2.Calculator)")
	public void targetStopWatch() {}
	
	@Before("targetStopWatch()")
	public void before(JoinPoint joinPoint) {
		System.out.println("[Stopwatch] @Before의 주인공: " + joinPoint.getSignature().getName());
	}
	
	@After("targetStopWatch()")
	public void after() {
		System.out.println("[Stopwatch] @After이다~");
	}
	
	@Around("targetStopWatch()")
	public Object aroundStopWatch(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("[Stopwatch] @Around 수행 전 주인공: " + joinPoint.getSignature().getName());
		StopWatch watch = new StopWatch("계산시간");
		watch.start();
		
		Object obj = joinPoint.proceed();
		
		watch.stop();
		System.out.println(watch.prettyPrint());
		System.out.println("[Stopwatch] @Around 수행 후");
		
		return obj;
	}

}
