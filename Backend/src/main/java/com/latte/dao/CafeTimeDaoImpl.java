package com.latte.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.latte.dto.CafeTimeDto;

@Repository
public class CafeTimeDaoImpl {

	private final String ns = "mapper.cafetime.";

	@Autowired
	private SqlSession sqlSession;

	int addCafeTime(CafeTimeDto CafeTime) {
		return sqlSession.insert(ns + "addCafeTime", CafeTime);
	}
	//카페 하나에 대한 시간 출력
	CafeTimeDto getCafeTimeByctId(int ctid) {
		return sqlSession.selectOne(ns + "getCafeTimeByctId", ctid);
	}

	int updateCafeTimeInfo(CafeTimeDto CafeTime) {
		return sqlSession.update(ns + "updateCafeTimeInfo", CafeTime);
	}

}
