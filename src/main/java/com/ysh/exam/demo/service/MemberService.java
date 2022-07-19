package com.ysh.exam.demo.service;

import org.springframework.stereotype.Service;

import com.ysh.exam.demo.repository.MemberRepository;
import com.ysh.exam.demo.vo.Member;

@Service
public class MemberService {

	private MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}


	public int join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		
		Member oldMember = getMemberByLoginId(loginId);
		
		if(oldMember != null) { // == 존재한다면
			return -1; // 리턴하는 것은 번호 해당 번호는 인덱스(가입된 회원의 번호)
		}
		
		
		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		return memberRepository.getLastInsertId();
	}


	private Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}


	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}

}
