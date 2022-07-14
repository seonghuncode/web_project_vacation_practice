package com.ysh.exam.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysh.exam.demo.vo.Article;

@Controller
public class UsrHomeController {
	
	
	@RequestMapping("/usr/home/getString")
	@ResponseBody
	public String getString() {
		return "Hi";
	}
	
	
	@RequestMapping("/usr/home/getInt")
	@ResponseBody
	public int getInt() {
		return 10;
	}
	
	
	@RequestMapping("/usr/home/getFloat")
	@ResponseBody
	public float getFloat() {
		return 10.5f;
	}
	
	
	@RequestMapping("/usr/home/getDouble")
	@ResponseBody
	public double getDouble() {
		return 10.5;
	}
	
	
	
	@RequestMapping("/usr/home/getBoolean")
	@ResponseBody
	public boolean getBoolean() {
		return true;  //브라우저 에서 보여지는 true이는 불린 값이 아니다. 브라우저는 자바 에서 사용하는 것을 인식X
	}
	
	
	
	@RequestMapping("/usr/home/getCharacter")
	@ResponseBody
	public char getCharacter() {
		return 'a';
	}
	
	
	
	//자바의 경우 위의 상황 처럼 다양한 데이터 처리를 할 수 있지만 브라우저는 오직 문장만 인식 가능
	
	@RequestMapping("/usr/home/getMap")
	@ResponseBody
	public Map<String, Object> getMap() {
		Map<String, Object> map = new HashMap<>();
		map.put("철수 나이", 22);
		map.put("영희 나이", 22);
		
		return map;
		
		//여기서 Map는 자바만 이해할 수 있는 개념
		//ResposneBody는 하래의 함수가 리턴한 값을 브라우저에 보여줘라
		//브라우저는 문장 밖에 알지 못 한다
	}
	
	
	
	
	@RequestMapping("/usr/home/getList")
	@ResponseBody
	public List<String> getList(){
		List<String> list = new ArrayList<>();
		list.add("철수");
		list.add("영희");
		
		return list;
		//자바스크립트 배열 형태로 브라우저에 나온다
		//이것은 문장이다(자바스크립트가 아니다)
	}	
	
	
	@RequestMapping("/usr/home/getArticle")
	@ResponseBody
	public Article getArticle() {
		Article article = new Article(1, "제목1", "내용2");
		
		return article;
	}
	
	
	@RequestMapping("/usr/home/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		Article article1 = new Article(1, "제목1", "내용2");
		Article article2 = new Article(2, "제목2", "내용2");
		
		List<Article> list = new ArrayList<>();
		list.add(article1);
		list.add(article2);
		
		return list;
	}
	//자바에서 객체 또는 map의 경우는 웹브라우저에 {}안에 표시가 되어 출력 된다
	//자바 세상에서 array or list 는 브라우저에 []형식으로 번역이 된다
	
	
	

	
	
	
	
	

}



























