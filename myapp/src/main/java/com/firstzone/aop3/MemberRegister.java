package com.firstzone.aop3;

import org.springframework.stereotype.Component;

@Component
public class MemberRegister {
	public void register() {
		System.out.println("회원등록");
	}
	
	public void memberInfo() {
		System.out.println("회원정보");
	}
	
	public void print() {
		System.out.println("회원정보 print");
	}
}
