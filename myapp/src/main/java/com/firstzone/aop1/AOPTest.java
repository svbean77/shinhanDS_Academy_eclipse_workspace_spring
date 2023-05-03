package com.firstzone.aop1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("aop1.xml");
		Calculator cal = context.getBean("proxyCal", Calculator.class);
		cal.add(7, 3);
		System.out.println();
		
		cal.subtract(15, 2);
		System.out.println();
		
		cal.divide(12, 4);
		
	}

}
