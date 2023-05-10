package net.shinhan.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
@Order(1)
public class StopWatchAdvice {
	@Pointcut("within(com.shinhan.model.EmpDAOMybatis)")
	public void targetStopWatch() {}
	
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
