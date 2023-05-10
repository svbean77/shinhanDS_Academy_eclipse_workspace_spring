package com.shinhan.model;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.vo.EmpVO;

@Service // @Component + Service
public class EmpService {
	@Autowired // EmpDAO 타입이 같으면 bean을 생성해 자동 주입 해준다
//	EmpDAO empDao;
	EmpDAOMybatis empDao;


//	public EmpVO getSalary(int empid) {
//		return empDao.getSalary(empid);
//	}


	public List<EmpVO> selectAll() {
		return empDao.selectAll();
	}

	public EmpVO selectById(int empid) {
		return empDao.selectById(empid);
	}

	public List<EmpVO> selectByDept(int deptid) {
		return empDao.selectByDept(deptid);
	}

	public List<EmpVO> selectByCondition(Integer deptid, String jobid, Double salary) {
		return empDao.selectByCondition(deptid, jobid, salary);
	}
	public List<EmpVO> selectByCondition2(Integer[] deptid, String jobid, Double salary, Date hiredate) {
		return empDao.selectByCondition2(deptid, jobid, salary, hiredate);
	}
	public List<EmpVO> selectByCondition3(Integer[] deptid, String jobid, Double salary, Date hiredate) {
		return empDao.selectByCondition3(deptid, jobid, salary, hiredate);
	}

//	public List<EmpVO> selectLAB() {
//		List<EmpVO> emplist = empDao.selectLAB();
//		System.out.println("[EmpService] 실행 건수: " + emplist.size());
//		return emplist;
//	}

	public String empInsert(EmpVO emp) {
		int result = empDao.empInsert(emp);
		return result > 0 ? "입력 성공" : "입력 실패";
	}

	public String empUpdate(EmpVO emp) {
		int result = empDao.empUpdate(emp);
		return result > 0 ? "수정 성공" : "수정 실패";
	}

	public String empDelete(int empid) {
		int result = empDao.empDelete(empid);
		return result > 0 ? "삭제 성공" : "삭제 실패";
	}
}
