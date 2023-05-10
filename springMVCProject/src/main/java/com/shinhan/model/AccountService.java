package com.shinhan.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)  //tracsaction 전파규칙 설정
public class AccountService {
	@Autowired
	AccountDAOMybatis dao;

	// 이체
	public void transfer() {
		dao.update1(); // 입금 (맞은 문장)
		dao.update2(); // 출금 (틀린 문장)
	}
}
