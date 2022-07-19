package com.ysh.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysh.exam.demo.service.MemberService;
import com.ysh.exam.demo.vo.Member;

@Controller
public class UsrMemberController {
	
//	방법1
//	@Autowired
//	private MemberService memberService;
	
	//방법2
	private MemberService memberService;
	
	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	

	//컨트롤러는 인포데스크 직원으로(점원) 직접 요리를 하지 않기 때문에 service를 만들어 주어야 한다
	//==> 컨트롤러는 손님의 요구사항을 service에 전달을 해주어야 한다
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Member doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		int id = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		
		Member member = memberService.getMemberById(id);
		
		return member;
	}
	
	
	
	
	
	

}



























