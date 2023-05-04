package com.shinhan.controller;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/emp")
public class EmpController {
	Logger logger = LoggerFactory.getLogger(EmpController.class);
	
	@RequestMapping(value = "/insert.do", method = RequestMethod.GET)
	public String registerGet() {
		// 그냥 페이지를 보여줌
		return "emp/empInsert";
	}
	
	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	public String registerPost(int employee_id, String first_name, String last_name, String email, String phone_number, 
			int salary, int department_id, int manager_id, String commission_pct, Date hire_date, String job_id) {
		// 등록하면 보임
		logger.info("employee_id: " + employee_id);
		logger.info("first_name: " + first_name);
		logger.info("last_name: " + last_name);
		logger.info("email: " + email);
		logger.info("phone_number: " + phone_number);
		logger.info("salary: " + salary);
		logger.info("department_id: " + department_id);
		logger.info("manager_id: " + manager_id);
		logger.info("commission_pct: " + commission_pct);
		logger.info("hire_date: " + hire_date);
		logger.info("job_id: " + job_id);
		
		return "emp/empInsertResult";
	}
}
