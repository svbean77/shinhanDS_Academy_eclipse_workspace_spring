package com.shinhan.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.vo.DeptVO;

@Service
public class DeptService {
	@Autowired
	DeptDAOMybatis dao;
//	DeptDAO dao;
	
	public List<DeptVO> deptList() {
		return dao.deptList();
	}
	
	public DeptVO selectById(int deptid) {
		return dao.selectById(deptid);
	}
	
	public String deptUpdate(DeptVO dept) {
		int result = dao.deptUpdate(dept);
		return result > 0 ? "수정 성공" : "수정 실패";
	}
	
	public String deptDelete(int deptid) {
		int result = dao.deptDelete(deptid);
		return result > 0 ? "삭제 성공" : "삭제 실패";
	}
	
	public String deptInsert(DeptVO dept) {
		int result = dao.deptInsert(dept);
		return result > 0 ? "입력 성공" : "입력 실패";
	}
}
