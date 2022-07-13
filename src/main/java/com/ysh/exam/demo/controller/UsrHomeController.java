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
		count = 0;
	}
	
	
	@RequestMapping("/usr/home/main")
	@ResponseBody
	public String showMain() {
		return "안녕하세요";
	}
	
	
	@RequestMapping("/usr/home/main2")
	@ResponseBody
	public String showMain2() {
		return "반갑습니다.";
	}
	
	
	@RequestMapping("/usr/home/main3")
	@ResponseBody
	public String showMain3() {
		return "또 만나요";
	}
	
	
	
	@RequestMapping("/usr/home/main4")
	@ResponseBody
	public int showMain4() {
		//int count = 0;  지역 변수의 값은 저장이 되지 않기 때문에 전역 변수로 만들어 주어야 한다.
		count++;
		return count;
	}
	
	
	
	
	
	
	

}
