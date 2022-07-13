package com.ysh.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrHomeController {
	
	private int count; //UserHomeController가 살아있는 동안 값을 저장 하여 변화를 주고 싶다면 전역 변수로 지정
	//별다른 이유가 없으면 무조건 private를 붙여야 한다.
	
	//생성자를 만들어 초기화 해주는 것이 좋은 방법 (바로 초기화 해도 되지만)
	public UsrHomeController() {
		count = -1;
	}
	
	
	
	
	
	@RequestMapping("/usr/home/getCount")
	@ResponseBody
	public int getCount() {
		//int count = 0;  지역 변수의 값은 저장이 되지 않기 때문에 전역 변수로 만들어 주어야 한다.
		return count;
	}
	
	
	
	@RequestMapping("/usr/home/doSetCount")
	@ResponseBody
	public String doSetCount(int count) {
		this.count = count;
		return "count의 값이" + this.count + "초기화 되었습니다.";
	}
	//위의 코드는 new UsrHomeController().doSetCount(10)와 유사한 개념
	//부라우저 에서 url뒤에 ? count의 값을 적어 주어야 한다
	//스프링은 매개변수에 적은 int count를 판단하지 못 한다
	//즉 스프링에는 쿼리 스트링 이라는 방식이 있는데 이것이 url?변수명=변수값을 적어 준다(여러개 가능 사이에는 &사용)
	
	
	
	
	
	

}
