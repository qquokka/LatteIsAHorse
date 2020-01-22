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
	@Transactional(readOnly = true)
	// 모든 회원 정보 가져오기
	public List<Member> getAllMemberList() {
		return memberdao.getAllMemberList();
	}

	@Override
	@Transactional(readOnly = true)
	// 회원 아이디(Email)로 특정 회원 정보 가져오기
	public Member getMemberById(String email) {
		return memberdao.getMemberById(email);
	}

	@Override
	@Transactional(readOnly = true)
	// 모든 회원 수 가져오기(탈퇴 여부 고려하기)
	public int getTotalMemberCounts() {
		return memberdao.getTotalMemberCounts();
	}

	@Override
	@Transactional
	//회원 가입
	public int singUpMember(Member member) {
		return memberdao.singUpMember(member);
	}

	@Override
	@Transactional
	//회원 정보 수정
	public int updateMemberInfo(Member member) {
		return memberdao.updateMemberInfo(member);
	}

	@Override
	@Transactional
	//회원 탈퇴 처리
	public int deleteMember(String email) {
		return memberdao.deleteMember(email);
	}

	@Override
	//아이디 유효성 체크
	public int isValidEmail(String email) {
		return memberdao.isValidEmail(email);
	}

	@Override
	//패스워드 유효성 체크
	public int isValidPassword(String pwd) {
		return memberdao.isValidPassword(pwd);
	}

}
