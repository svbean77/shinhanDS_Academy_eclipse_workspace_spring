package com.shinhan.section04;

import org.springframework.stereotype.Repository;

// Component 이면서 data access하는(DB) object = Repository
@Repository
public class EmpDAO {
	public EmpDAO() {
		System.out.println("EmpDAO의 dafult 생성자");
	}
	
	public void selectAll() {
		System.out.println("모든 직원을 조회한다!");
	}
}
