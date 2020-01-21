package com.latte.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.latte.dto.Member;

@Repository
public class MemberDaoImpl {

	String ns = "latte.member."; // ns : namespace

	@Autowired
	private SqlSession sqlSession;

	public List<Member> getAllMemberList() {
		return sqlSession.selectList(ns + "getAllMemberList");
	}

	public Member getMember(String id) {
		return sqlSession.selectOne(ns + "getMember", id);
	}

	public int getTotalMembers() {
		return sqlSession.selectOne(ns + "getTotalMembers");
	}

	public int addMember(Member member) {
		return sqlSession.insert(ns + "addMember", member);
	}

	public void updateMember(Member member) {
		sqlSession.update(ns + "updateMember", member);
	}

	public boolean deleteMember(String id) {
	
		return sqlSession.update(ns + "deleteMember", id) > 0 ? true : false;
	}

	public int checkId(String uid) {
		return sqlSession.selectOne(ns + "checkId", uid);
	}

	public boolean checkPwd(String password) {
		return sqlSession.selectOne(ns + "checkPwd", password);
	}

}
