package com.shinhan.section02;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.shinhan.section01.TV;

public class App {

	public static void main(String[] args) {
//		f1();
		// BeanFactory 사용 -> 변화가 생겼을 때 java는 그대로 두고 spring만 바꾸면 됨!
		
//		f2();
		// Application Context 사용
		
//		f3();
		// Application Context 사용 - setter
		
//		f4();
		// Application Context 사용 - TV 예제
		
//		f5();
		// list 사용 (String)
		
//		f6();
		// list 사용 (객체)
		
//		f7();
		// map 사용 (String, 객체)
		
//		f8();
		// set, properties 사용
		
//		f9();
		// autowire - byName
		
		f10();
		// 같은 bean으로부터 얻은 객체는 동일 객체일까? => 같음!
		// scope를 통해 새로운 객체 얻기 가능
	}

	private static void f10() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("section02.xml");
		People p1 = ctx.getBean("p6", People.class);
		People p2 = ctx.getBean("p6", People.class);
		// p1과 p2는 같은 객체일까?
		// getBean은 '얻어라'이기 때문에 동일한 객체임!
		
		System.out.println(p1 == p2); // 객체 주소 비교
		
		People p3 = ctx.getBean("p7", People.class);
		People p4 = ctx.getBean("p7", People.class);
		
		System.out.println(p3 == p4);
	}

	private static void f9() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("section02.xml");
		People p = ctx.getBean("p6", People.class);
		System.out.println(p);
	}

	private static void f8() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("section02.xml");
		People p = ctx.getBean("p5", People.class);
		System.out.println(p);
		System.out.println(p.getFriend());
	}

	private static void f7() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("section02.xml");
		People p = ctx.getBean("p4", People.class);
		System.out.println(p);
	}

	private static void f6() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("section02.xml");
		People p = ctx.getBean("p3", People.class);
		System.out.println(p);
	}

	private static void f5() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("section02.xml");
		People p = ctx.getBean("p2", People.class);
		System.out.println(p);
	}

	private static void f4() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("section02.xml");
		TV tv = ctx.getBean("samsung", TV.class);
		tv.powerOn();
		tv.powerOff();
		
		TV tv2 = ctx.getBean("lg", TV.class);
		tv2.powerOn();
		tv2.powerOff();
	}

	private static void f3() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("section02.xml");
		People p1 = ctx.getBean("p1", People.class);
		System.out.println(p1);
		
	}

	private static void f2() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("section02.xml");
		// 사용을 하지 않아도 이미 생성! (클래스 생성자의 코드들이 이미 콘솔에 찍힌다)
		Car c1 = ctx.getBean("car1", Car.class);
		System.out.println(c1);
		
		Car c2 = ctx.getBean("car2", Car.class);
		System.out.println(c2);
		
		Car c3 = ctx.getBean("car3", Car.class);
		System.out.println(c3);
	}

	private static void f1() {
		Resource resource = new ClassPathResource("section02.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		Car c1 = (Car) factory.getBean("car1"); // getBean의 리턴타입은 Object -> 형 변환 필요
		System.out.println(c1);
		
		Car c2 = factory.getBean("car2", Car.class); // 이 방식을 사용하면 형 변환 필요 없음
		System.out.println(c2);
		
		Car c3 = factory.getBean("car3", Car.class);
		System.out.println(c3);
	}

}
