package com.firstzone.aop1;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class MyAdvice implements MethodBeforeAdvice{

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("------------ MethodBeforeAdvice -------------");
		System.out.println("함수명: " + method.getName());
		System.out.println("---------------------------------------------");
	}
	
}
