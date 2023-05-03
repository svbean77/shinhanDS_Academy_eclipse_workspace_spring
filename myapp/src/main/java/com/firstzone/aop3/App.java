package com.firstzone.aop3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("aop3.xml");
		MemberRegister member = context.getBean("memberRegister", MemberRegister.class);
		EmpRegister emp = context.getBean("emp", EmpRegister.class);
		member.memberInfo();
		member.register();
		member.print();
		
		System.out.println();
		
		emp.empInfo();
		emp.registerEmp();
		emp.print("홍길동");
	}
}
