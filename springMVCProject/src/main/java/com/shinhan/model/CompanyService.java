package com.shinhan.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.vo.DeptVO;
import com.shinhan.vo.EmpVO;
import com.shinhan.vo.JobVO;

@Service
public class CompanyService {
	@Autowired
	CompanyDAO dao;
	
	public List<DeptVO> deptSelectAll() {
		return dao.deptSelectAll();
	}
	
	public List<JobVO> jobSelectAll() {
		return dao.jobSelectAll();
	}
	
	public List<EmpVO> managerSelectAll() {
		return dao.managerSelectAll();
	}
}
