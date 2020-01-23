package com.latte.service;

import java.util.List;

import com.latte.dto.Member;

public interface IMemberService {

	// DB에 저장된 모든 회원정보 반환
	List<Member> getAllMemberList();

	// ID(Email)에 해당되는 회원정보 반환
	Member getMemberById(String email);

	// 모든 회원수 반환
	int getTotalMemberCounts();

	// 회원가입
	int singUpMember(Member member);

	// 회원정보 수정
	int updateMemberInfo(Member member);

	// 회원정보 삭제(mem_active 변경하기), DB에서 정보 삭제 하지 않음
	int deleteMember(String email);

	// 아이디가 존재하는지 체크
	int isValidEmail(String email);

	// 유효한 비밀번호인지 체크
	int isValidPassword(String pwd);

}
