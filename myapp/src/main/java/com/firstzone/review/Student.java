package com.firstzone.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component("my")

@ToString
@Setter @Getter
public class Student {
	private String studentId;
	private String studentName;
	private String major; 
	@Autowired
	private Address addr;
	
	public Student() {
		System.out.println("student 기본 생성자 지난다");
	}

	public Student(String studentId, String studentName, String major, Address addr) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.major = major;
		this.addr = addr;
		
		System.out.println("student 매개변수 생성자 지난다");
	}
	
	public Student(String studentId, String studentName, String major) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.major = major;
		
		System.out.println("student 3개 매개변수 생성자 지난다");
	}
	
}
