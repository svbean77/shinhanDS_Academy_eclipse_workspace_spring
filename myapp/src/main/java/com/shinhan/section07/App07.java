package com.shinhan.section07;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App07 {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("section04.xml");
		DeptController controller = context.getBean("deptController", DeptController.class);
		controller.f1();
	}

}
