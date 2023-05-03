package com.firstzone.aop2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("aop2.xml");
		Calculator cal = context.getBean("proxyCal", Calculator.class);
		int result1 = cal.add(7, 3);
		System.out.println("return한 결과는 " + result1); // Advice에서 return obj가 없다면 결과가 나오지 않아..
		
		System.out.println();
		System.out.println();
		
		cal.add(7);
		
		System.out.println();
		System.out.println();
		
		cal.divide(10, 4);
	}

}
