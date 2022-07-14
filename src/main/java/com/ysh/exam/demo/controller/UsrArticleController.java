package com.ysh.exam.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysh.exam.demo.vo.Article;

@Controller
public class UsrArticleController {
	
	private int articlesLastId;
	private List<Article> articles; 
	
	public UsrArticleController() {
		articlesLastId = 0;
		articles = new ArrayList<>();
		
		makeTestData();
	}
	
	
	
	private void makeTestData() {
		for(int i = 1; i <= 10; i++) {
			int id = articlesLastId + 1;
			String title = "제목" + i;
			String body = "내용" + i;
			Article article = new Article(id, title, body);
			
			articles.add(article);
			articlesLastId = id;
		}
	}
	
	
	
	
	@RequestMapping("/usr/article/doAdd")  //Home에 만들어도 되지만 실무 에서는 따로 나누어 쓴다
	@ResponseBody
	public Article doAdd(String title, String body) {
		//게시물 id, title, body값을 저장 하는 과정
		
		int id = articlesLastId + 1;
		Article article = new Article(id, title, body);
		
		articles.add(article);
		articlesLastId = id;
		
		return article;
		//실행 하면 title, body에 null값이 들어간다 즉 url창에서 ?값을 넣어 준다
		
	}
	
	
	
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles(){
		//저장된 게시물을 보여 주는 기능
		return articles;
	}
	
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		
		return  id + "번 게시물이 삭제 되었 습니다.";
	}
	
	

}

























