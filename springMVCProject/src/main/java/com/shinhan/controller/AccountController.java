package com.shinhan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shinhan.model.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {
	@Autowired
	AccountService service;
	
	@GetMapping(value = "/transfer.do", produces = "text/html;charset=utf-8")
	@ResponseBody // 응답문서의 문자로 가라는 의미 (response.getWriter().append("메시지");
	public String transactionTest () {
		// 트랜잭션을 사용하지 않은 경우: 입금은 성공했는데 출금은 실패함!!! 
		service.transfer();
		return "<h1>transfer transaction test 한글한글</h1>";
	}
}
