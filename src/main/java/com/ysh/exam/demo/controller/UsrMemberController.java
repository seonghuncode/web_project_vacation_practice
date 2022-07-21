package com.ysh.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysh.exam.demo.service.MemberService;
import com.ysh.exam.demo.util.Ut;
import com.ysh.exam.demo.vo.Member;
import com.ysh.exam.demo.vo.ResultData;

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
	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		
		//trim() : 띄어쓰기 length = 0 : 공백
		if(Ut.empty(loginId)) {
			return ResultData.from("F-1", "loginId(을)를 입력해 주세요");
		}
		
		if(Ut.empty(loginPw)) {
			return ResultData.from("F-2", "loginPw(을)를 입력해 주세요.");
		}
		
		if(Ut.empty(name)) {
			return ResultData.from("F-3", "name(을)를 입력해 주세요.");
		}
		
		if(Ut.empty(nickname)) {
			return ResultData.from("F-4", "nickname(을)를 입력해 주세요.");
		}
		
		if(Ut.empty(cellphoneNo)) {
			return ResultData.from("F-5", "cellphoneNo(을)를 입력해 주세요.");
		}
		
		if(Ut.empty(email)) {
			return ResultData.from("F-6", "email(을)를 입력해 주세요.");
		}
		
		
		//joinRd에 들어 있는 데이터
		//S-1
		//회원가입이 완료 되었습니다.
		//7
		ResultData<Integer> joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		//Interger을 적는 이유는 아래서 joinRd앞에 형변환 (int)를 사용하지 않기 위해서
		
		if(joinRd.isFail()) {
			return (ResultData)joinRd;
		}
		
		
		//위의 joinRd에서 가지고 있는 데이터를 브라우저에게 보여 줄때 성공 여부랑, 메세지는 동일하게 하고 회원번호를 바꾸고 싶어 만든 코드 이
		Member member = memberService.getMemberById(joinRd.getData1());
		return ResultData.newData(joinRd, member);
		
		
//		return joinRd; 
//		만약 이렇게 하면 회원가입이 완료 되었다는 메세지와 함께  데이터에는 getLasdtInsertId가들어가게 된다
//		따라서 위처럼 데이터를 모든 회원 정보를 출력하기 위해 위처럼 바꾸었다.
		
	}
	
	
	
	
	
	

}



























