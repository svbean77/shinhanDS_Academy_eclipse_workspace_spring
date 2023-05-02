package com.shinhan.section04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("empSer")
// Autowired이기 때문에 dao가 생기면 타입이 같을 때 자동으로 주입됨 -> service에는 기능만 작성하면 돼!
public class EmpService {
	@Autowired
	EmpDAO dao;

	public void empAll() {
		dao.selectAll();
	}
}
