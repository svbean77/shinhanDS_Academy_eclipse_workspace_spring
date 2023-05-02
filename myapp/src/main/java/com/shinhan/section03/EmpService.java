package com.shinhan.section03;

// Service가 DAO를 이용한다 = Service가 DAO에 의존한다 (new DAO()해서 사용해야 하니까!)
public class EmpService {
	// new 하지 않고 이용하는 방법
	EmpDAO dao;
	
	// 1. 생성자를 통해 Injection
	public EmpService(EmpDAO dao) {
		this.dao = dao;
	}
	
	// 2. setter를 통해 Injection
	public EmpService() {
		System.out.println("EmpService의 default 생성자");
	}
	
	public void setDao(EmpDAO dao) {
		this.dao = dao;
	}
	
	// 기능
	public void empAll() {
		dao.selectAll();
	}
}
