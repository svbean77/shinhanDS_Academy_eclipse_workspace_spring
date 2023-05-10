package com.shinhan.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOMybatis {
	@Autowired
	SqlSession sqlSession;
	final static Logger LOG = LoggerFactory.getLogger(AccountDAOMybatis.class);
	
	public void update1() {
		Map<String, Object> param = new HashMap<>();
		param.put("amount", 1);
		param.put("accountNo", "112");
		int result = sqlSession.update("co.kr.firstzone.account.update1", param);

		LOG.info("update1 실행");
		LOG.info(result + "건 업데이트 (입금)");
	}
	
	public void update2() {
		Map<String, Object> param = new HashMap<>();
		
		param.put("amount", 1);
		param.put("accountNo", "113");
		int result = sqlSession.update("co.kr.firstzone.account.update2", param);
		
		LOG.info("update2 실행");
		LOG.info(result + "건 업데이트 (출금)");
	}
}
