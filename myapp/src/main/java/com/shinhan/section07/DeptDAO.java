package com.shinhan.section07;

import org.springframework.stereotype.Repository;

// bean으로 만들기
// bean: spring이 생성, 소멸을 관리하는 객체
// 방법 1) XML 파일에 등록: <bean id="아이디" class="패키지.클래스"/>
// 방법 2) Annotation 사용: meta data를 사용 -> 가독성이 좋음 @Component, @Repository, @Service, @Controller
// 		context:component-scan으로 패키지 설정
@Repository("deptDAO")
public class DeptDAO implements DeptDAOInterface{
	@Override
	public void deptAll() {
		System.out.println("1. 모든 부서의 정보 return");
	}
}
