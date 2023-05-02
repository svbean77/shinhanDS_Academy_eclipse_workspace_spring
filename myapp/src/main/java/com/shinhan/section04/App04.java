package com.shinhan.section04;

import org.shinhan.section06.License;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shinhan.section05.Car;

public class App04 {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("section04.xml");
		
		Book b1 = context.getBean("book", Book.class);
		System.out.println(b1);
		
		Car c1 = context.getBean("myCar", Car.class);
		System.out.println(c1);
		
		License l1 = context.getBean("license", License.class);
		System.out.println(l1);
		
		People p1 = context.getBean("people", People.class);
		System.out.println(p1);
		
		System.out.println("*********************************");
		
		EmpService service = context.getBean("empSer", EmpService.class);
		service.empAll();
	}

}
