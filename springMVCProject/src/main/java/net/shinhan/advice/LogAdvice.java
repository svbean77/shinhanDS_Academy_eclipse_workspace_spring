package net.shinhan.advice;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAdvice {
	@Pointcut("execution(* com.shinhan.model.*DAO*.select*(..))")
	public void cut1() {}
	
	
	@Around("cut1()")
	public Object aroundLog(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("[cut1 LoggingAdvice] 주 업무 전: " + joinPoint.getTarget().getClass() + " " + joinPoint.getSignature().getName() + " ---");
		
		Object obj = joinPoint.proceed();
		
		System.out.println("[cut1 LoggingAdvice] 주 업무 후: " + joinPoint.getTarget().getClass() + " " + Arrays.toString(joinPoint.getArgs()));
		
		return obj;
	}
}
