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
		// 로그인 아이디 증복 체크
		Member oldMember = getMemberByLoginId(loginId);

		if (oldMember != null) { // == 존재한다면
			return -1; // 리턴하는 것은 번호 해당 번호는 인덱스(가입된 회원의 번호)
		}

		//이름+이메일 중복 체크
		oldMember = getMemberByNameAndEmail(name, email);

		if (oldMember != null) { // == 존재한다면
			return -2; // 오류에 따라 다른 번호를 리턴해서 오류 원인을 쉽게 파악할 수 있도록 한다
		}

		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		return memberRepository.getLastInsertId();
	}

	private Member getMemberByNameAndEmail(String name, String email) {
		return memberRepository.getMemberByNameAndEmail(name, email);
	}

	private Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}

}
