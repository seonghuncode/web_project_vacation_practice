package com.ysh.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysh.exam.demo.service.MemberService;
import com.ysh.exam.demo.util.Ut;
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
	public Object doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		
		//trim() : 띄어쓰기 length = 0 : 공백
		if(Ut.empty(loginId)) {
			return "loginId(을)를 입력해 주세요.";
		}
		
		if(Ut.empty(loginPw)) {
			return "loginPw(을)를 입력해 주세요.";
		}
		
		if(Ut.empty(name)) {
			return "name(을)를 입력해 주세요.";
		}
		
		if(Ut.empty(nickname)) {
			return "nickname(을)를 입력해 주세요.";
		}
		
		if(Ut.empty(cellphoneNo)) {
			return "cellphoneNo(을)를 입력해 주세요.";
		}
		
		if(Ut.empty(email)) {
			return "email(을)를 입력해 주세요.";
		}
		
		//성공하면 id에 1이상의 숫자가 들어오고 실패하면 -1이들어 온다
		int id = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		
		if(id == -1) {
			return Ut.f("해당 로그인 아이디(%s)는 이미 사용중 입니다.", loginId);
		}
		
		if(id == -2) {
			return Ut.f("해당 이름(%s)과 이메일(%s)은 이미 사용중 입니다.", name, email);
		}
		
		Member member = memberService.getMemberById(id);
		
		return member;
	}
	
	
	
	
	
	

}



























