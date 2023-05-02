package com.shinhan.section07;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DeptController {
	@Autowired
	DeptService service;
	
	public void f1() {
		service.work();
	}
}
