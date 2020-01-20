package com.latte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.latte.dao.MemberDaoImpl;
import com.latte.dto.Member;
import com.latte.dto.TestUser;

@Service
public class MemberServiceImpl implements IMemberService {

	@Autowired
	private MemberDaoImpl memberdao;

	@Override
	@Transactional(readOnly = true)
	public List<TestUser> getAllMemberList() {
		return memberdao.getAllMemberList();
	}

	@Override
	@Transactional(readOnly = true)
	public Member getMember(String id) {
		return memberdao.getMember(id);
	}

	@Override
	@Transactional(readOnly = true)
	public int getTotalMembers() {
		return memberdao.getTotalMembers();
	}

	@Override
	@Transactional
	public int addMember(Member member) {
		return memberdao.addMember(member);
	}

	@Override
	@Transactional
	public void updateMember(Member member) {
		memberdao.updateMember(member);
	}

	@Override
	@Transactional
	public boolean deleteMember(String id) {
		return memberdao.deleteMember(id);
	}

	@Override
	@Transactional(readOnly = true)
	public int checkId(String uid) {
		return memberdao.checkId(uid);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkPwd(String password) {
		return memberdao.checkPwd(password);
	}

}
