package com.latte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.latte.dao.MemberDaoImpl;
import com.latte.dto.Member;

@Service
public class MemberServiceImpl implements IMemberService {

	@Autowired
	private MemberDaoImpl memberdao;

	@Override
	public List<Member> getAllMemberList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member getMemberById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalMemberCounts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addMember(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateMemberInfo(Member member) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean deleteMember(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int checkId(String uid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean checkPwd(String password) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
//	@Transactional(readOnly = true)
//	public List<TestUser> getAllMemberList() {
//		return memberdao.getAllMemberList();
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public Member getMember(String id) {
//		return memberdao.getMember(id);
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public int getTotalMembers() {
//		return memberdao.getTotalMembers();
//	}
//
//	@Override
//	@Transactional
//	public int addMember(Member member) {
//		return memberdao.addMember(member);
//	}
//
//	@Override
//	@Transactional
//	public void updateMember(Member member) {
//		memberdao.updateMember(member);
//	}
//
//	@Override
//	@Transactional
//	public boolean deleteMember(String id) {
//		return memberdao.deleteMember(id);
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public int checkId(String uid) {
//		return memberdao.checkId(uid);
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public boolean checkPwd(String password) {
//		return memberdao.checkPwd(password);
//	}

}
