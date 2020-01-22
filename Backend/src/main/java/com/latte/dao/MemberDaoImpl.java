package com.latte.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.latte.dto.Member;

@Repository
public class MemberDaoImpl {

	private final String ns = "mapper.member."; // ns : namespace

	@Autowired
	private SqlSession sqlSession;

	public List<Member> getAllMemberList() {
		return sqlSession.selectList(ns + "getAllMemberList");
	}

	public Member getMemberById(String email) {
		return sqlSession.selectOne(ns + "getMemberById", email);
	}

	public int getTotalMemberCounts() {
		return sqlSession.selectOne(ns + "getTotalMemberCounts");
	}

	public int singUpMember(Member member) {
		return sqlSession.insert(ns + "singUpMember", member);
	}

	public int updateMemberInfo(Member member) {
		return sqlSession.update(ns + "updateMemberInfo", member);
	}

	public int deleteMember(String email) {
		return sqlSession.update(ns + "deleteMember", email);
	}

	public int isValidEmail(String email) {
		return sqlSession.selectOne(ns + "isValidEmail", email);
	}

	public int isValidPassword(String pwd) {
		return sqlSession.selectOne(ns + "isValidPassword", pwd);
	}

}
