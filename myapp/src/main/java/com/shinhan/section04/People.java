package com.shinhan.section04;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.shinhan.section06.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shinhan.section05.Car;

import lombok.ToString;

@Component

@ToString
public class People {
	private String name;
	private int age;
	
	// type이 같으면 자동으로 주입
	@Autowired
	private Car car;
	private List<String> major;
	
	private List<License> licenseList;
	private Map<String, Book> books;
	
	Set<String> friend;
	Properties myprofile;
}
