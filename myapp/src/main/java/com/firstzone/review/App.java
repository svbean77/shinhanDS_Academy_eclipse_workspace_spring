package com.firstzone.review;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class App {

	public static void main(String[] args) {
//		f1();
		// BeanFactory 복습
		
//		f2();
//		 BeanFactory, ApplicationContext 생성 시기
		
		f3();
		// Annotation 사용
	}

	private static void f3() {
		ApplicationContext context = new ClassPathXmlApplicationContext("review.xml");
		Student s1 = context.getBean("my", Student.class);
		System.out.println(s1);
	}

	private static void f2() {
		Resource resource = new ClassPathResource("review.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		System.out.println("getBean 이전");
		Student s1 = factory.getBean("student3", Student.class);
		System.out.println("getBean 이후");
		System.out.println(s1);
		
		ApplicationContext context = new ClassPathXmlApplicationContext("review.xml");
		System.out.println("getBean 이전");
		Student s2 = context.getBean("student3", Student.class);
		System.out.println("getBean 이후");
		System.out.println(s2);
	}

	private static void f1() {
		Resource resource = new ClassPathResource("review.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		Student s1 = factory.getBean("student1", Student.class);
		System.out.println(s1);
		
		Student s2 = factory.getBean("student2", Student.class);
		System.out.println(s2);
		System.out.println(s2.getStudentId());
		
		Student s3 = factory.getBean("student3", Student.class);
		System.out.println(s3);
	}

}
