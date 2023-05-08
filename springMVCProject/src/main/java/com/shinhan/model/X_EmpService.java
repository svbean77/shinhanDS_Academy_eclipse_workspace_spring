package com.shinhan.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.vo.EmpVO;

@Service // @Service: @Component + service (business 로직이 있다)
public class X_EmpService {
	@Autowired
	X_EmpDAO empDAO;
	
	public List<EmpVO> selectAll() {
		return empDAO.selectAll();
	}
}
