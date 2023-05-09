package com.shinhan.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.vo.DeptVO;

@Repository
public class DeptDAOMybatis {
	@Autowired
	SqlSession sqlSession;
	String namespace = "co.kr.firstzone.dept.";
	Logger logger = LoggerFactory.getLogger(DeptDAOMybatis.class);

	public List<DeptVO> deptList() {
		List<DeptVO> deptlist = sqlSession.selectList(namespace + "selectAll");
		logger.info("mybatis: " + deptlist);
		return deptlist;
	}

	public DeptVO selectById(int deptid) {
		logger.info("mybatis -- selectById: " + deptid);
		return sqlSession.selectOne(namespace + "selectById", deptid);
	}

	public int deptUpdate(DeptVO dept) {
		logger.info("mybatis -- deptUpdate: " + dept);
		return sqlSession.update(namespace + "update", dept);
	}

	public int deptDelete(int deptid) {
		logger.info("mybatis -- deptDelete: " + deptid);
		return sqlSession.delete(namespace + "delete", deptid);
	}

	public int deptInsert(DeptVO dept) {
		logger.info("mybatis -- deptInsert: " + dept);
		return sqlSession.insert(namespace + "insert", dept);
	}
}
