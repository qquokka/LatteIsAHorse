package com.latte.service;

import java.util.List;

import com.latte.dto.Member;
import com.latte.dto.TestUser;


public interface IMemberService {
	
	//DB에 저장된 모든 회원정보 반환
	List<TestUser> getAllMemberList();
	
	//ID에 해당되는 회원정보 반환
	Member getMember(String id);

	//모든 회원수 반환
	int getTotalMembers();

	//회원가입
	int addMember(Member member);

	//회원정보 수정
	void updateMember(Member member);
	
	//회원정보 삭제(delflag 변경하기), DB에서 정보 삭제 하지 않음
	boolean deleteMember(String id);

	//아이디가 존재하는지 체크
	int checkId(String uid);

	//유효한 비밀번호인지 체크
	boolean checkPwd(String password);



	
	

}
