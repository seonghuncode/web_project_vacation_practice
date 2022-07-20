package com.ysh.exam.demo.service;

import org.springframework.stereotype.Service;

import com.ysh.exam.demo.repository.MemberRepository;
import com.ysh.exam.demo.util.Ut;
import com.ysh.exam.demo.vo.Member;
import com.ysh.exam.demo.vo.ResultData;

@Service
public class MemberService {

	private MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public  ResultData join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		// 로그인 아이디 증복 체크
		Member oldMember = getMemberByLoginId(loginId);

		if (oldMember != null) { // == 존재한다면
			return ResultData.from("F-7", Ut.f("해당 로그인 아이디 %s는 이미 사용중 입니다", loginId));
		}

		//이름+이메일 중복 체크
		oldMember = getMemberByNameAndEmail(name, email);

		if (oldMember != null) { // == 존재한다면
			return ResultData.from("F-8", Ut.f("해당 이름(%s)과 이메일(%s)은 이미 사용중 입니다.", name, email));
		}

		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		
		int id = memberRepository.getLastInsertId();
		
		return ResultData.from("S-1", "회원가입이 완료 되었습니다.", id);
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
