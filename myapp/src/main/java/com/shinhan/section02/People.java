package com.shinhan.section02;

import java.util.List;
import java.util.Map;

public class People {
	private String name;
	private int age;
	private Car car;
	private List<String> major;
	private List<License> licenseList;
	private Map<String, Book> books;
	// 기본 생성자는 생성자가 정의되지 않은 경우 컴파일 시 자동 생성
	
	public People() {
		System.out.println("People의 default 생성자");
	}
	
	public People(String name, int age, Car car) {
		System.out.println("People의 arg 3개 생성자");
	}
	
	public void setName(String name) {
		System.out.println("People의 setter: name");
		this.name = name;
	}
	public void setAge(int age) {
		System.out.println("People의 setter: age");
		this.age = age;
	}
	public void setCar(Car car) {
		System.out.println("People의 setter: car");
		this.car = car;
	}
	public void setMajor(List<String> major) {
		this.major = major;
	}
	public void setLicenseList(List<License> licenseList) {
		this.licenseList = licenseList;
	}
	public void setBooks(Map<String, Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "People [name=" + name + ", age=" + age + ", car=" + car + ", major=" + major + ", licenseList="
				+ licenseList + ", books=" + books + "]";
	}

	

}
