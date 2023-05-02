package com.shinhan.section07;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service // @Component + Service 역할
public class DeptService {
	@Autowired // DeptDAO deptDAO = new DeptDAO()와 같음! (직접 new 하는 방법은 의존 관계가 강함 -> 수정 발생 시 직접 수정해야 함)
	// Autowired 작성 위치: field, constructor, setter -> 만들기 귀찮으니 field에 작성하는 것이 일반적
	// type이 같으면 자동 주입, 같은 type이 여러 개이면 같은 이름 찾음
	// 같은 type이 여러 개 있으면 Qualifier로 이름을 설정 (ex. interface로 구현한 경우)
	// 이름이 같은 애를 찾아서 자동 주입을 해주지만 명확하지 않기 때문에 Qualifier로 명시해주는 것이 좋은 습관!
	@Qualifier("deptDAO")
	DeptDAOInterface dao;
	
	public void work() {
		dao.deptAll();
	}
}
