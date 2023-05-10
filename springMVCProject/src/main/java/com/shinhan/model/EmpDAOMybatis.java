package com.shinhan.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.vo.EmpVO;

@Repository
public class EmpDAOMybatis {
	@Autowired // 타입이 같으면 자동으로 injection
	SqlSession sqlSession;
	static final String NAMESPACE = "co.kr.firstzone.emp.";
	static final Logger LOG = LoggerFactory.getLogger(EmpDAOMybatis.class);

	public List<EmpVO> selectAll() {
		List<EmpVO> emplist = sqlSession.selectList(NAMESPACE + "selectAll");
		LOG.info("전 직원: " + emplist);
		return emplist;
	}

	public EmpVO selectById(int empid) {
		EmpVO emp = sqlSession.selectOne(NAMESPACE + "selectById", empid);
		LOG.info(empid + "번 직원: " + emp);
		return emp;
	}

	public List<EmpVO> selectByDept(int deptid) {
		List<EmpVO> emplist = sqlSession.selectList(NAMESPACE + "selectByDept", deptid);
		LOG.info(deptid + "번 부서 직원: " + emplist);
		return emplist;
	}

	public List<EmpVO> selectByCondition(Integer dept_id, String job_id, Double salary) {
		Map<String, Object> mapData = new HashMap<>();
		mapData.put("deptid", dept_id); // key는 xml의 #{deptid}임!
		mapData.put("jobid", job_id);
		mapData.put("salary", salary);

		List<EmpVO> emplist = sqlSession.selectList(NAMESPACE + "selectByCondition", mapData);
		LOG.info("조건에 해당: " + emplist);
		return emplist;
	}
	
	// 방법 1: vo를 이용해 for문 돌기 
	public List<EmpVO> selectByCondition2(Integer[] dept_id, String job_id, Double salary, Date hiredate) {
//		EmpVO emp = new EmpVO();
//		emp.setDepartment_id(dept_id);
//		emp.setJob_id(job_id);
//		emp.setSalary(salary);
//		emp.setHire_date(hiredate);
//		
//		List<EmpVO> emplist = sqlSession.selectList(NAMESPACE + "selectByCondition2", emp);
//		LOG.info("조건에 해당: " + emplist);
//		return emplist;
		
		List<EmpVO> emplistResult = new ArrayList<>();
		List<EmpVO> emplist = null;

		// 내 방법: 전체 검색의 value를 0이 아닌 ""로 주면 이렇게 해야 함 -> 모두 조회해야 하기 때문!
//		if(Arrays.toString(dept_id) == "[]") {
//			dept_id = new Integer[] {0};
//		}
		
		for(Integer dept: dept_id) {
			EmpVO emp = new EmpVO();
			
			emp.setDepartment_id(dept);
			emp.setJob_id(job_id);
			emp.setSalary(salary);
			emp.setHire_date(hiredate);
		
			emplist = sqlSession.selectList(NAMESPACE + "selectByCondition2", emp);

			LOG.info("조건에 해당: " + emplist);
			emplist.forEach(aa -> emplistResult.add(aa));
		}
		
		return emplistResult;
	}
	
	// 방법 2: map을 이용해 넣기
	public List<EmpVO> selectByCondition3(Integer[] dept_id, String job_id, Double salary, Date hiredate) {
		Map<String, Object> mapData = new HashMap<>();
		if(dept_id.length == 0 || dept_id[0] == 0) dept_id = null;
		
		mapData.put("deptid", dept_id); 
		mapData.put("jobid", job_id);
		mapData.put("salary", salary);
		mapData.put("hiredate", hiredate);

		List<EmpVO> emplist = sqlSession.selectList(NAMESPACE + "selectByCondition3", mapData);
		LOG.info("조건에 해당: " + emplist);
		return emplist;
	}

	public int empInsert(EmpVO emp) {
		int resultCount = sqlSession.insert(NAMESPACE + "insert", emp); // insert의 영향을 받은 row 수가 리턴됨
		LOG.info("입력 건수: " + resultCount);
		return resultCount;
	}

	public int empUpdate(EmpVO emp) {
		int resultCount = sqlSession.update(NAMESPACE + "update", emp);
		LOG.info("수정 건수: " + resultCount);
		return resultCount;
	}

	public int empDelete(int empid) {
		int resultCount = sqlSession.delete(NAMESPACE + "delete", empid);
		LOG.info("삭제 건수: " + resultCount);
		return resultCount;
	}

}
