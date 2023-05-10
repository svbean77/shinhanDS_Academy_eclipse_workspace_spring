package com.shinhan.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)  //tracsaction ���ı�Ģ ����
public class AccountService {
	@Autowired
	AccountDAOMybatis dao;

	// ��ü
	public void transfer() {
		dao.update1(); // �Ա� (���� ����)
		dao.update2(); // ��� (Ʋ�� ����)
	}
}
