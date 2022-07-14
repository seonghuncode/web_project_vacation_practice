package com.ysh.exam.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysh.exam.demo.vo.Article;

@Controller
public class UsrArticleController {
	
	//인스턴스 변수 시작
	private int articlesLastId;
	private List<Article> articles; 
	//인스턴스 변수 끝
	
	
	//생성자
	public UsrArticleController() {
		articlesLastId = 0;
		articles = new ArrayList<>();
		
		makeTestData();
	}
	
	
	//서비스 메서드 시작
	private void makeTestData() {
		for(int i = 1; i <= 10; i++) {
			String title = "제목" + i;
			String body = "내용" + i;
		
			wirteArticle(title,body);
		
		}
	}
	
	
	public Article wirteArticle(String title, String body ) {
		//중복된는 로직을 따로 만들어 메서드 호출로 간편하게 사용하기 위해 만들 었다
			int id = articlesLastId + 1;
			Article article = new Article(id, title, body);
			
			articles.add(article);
			articlesLastId = id;
			
			return article;
	}
	
	
	
	private Article getArticle(int id) {
		
		for(Article article : articles){  //향상된 for문으로  articles에 들어 있는 것이 끝날때 까지 반복
			if(article.getId() == id) { //private이기 때문에 getter를 이용해서 getID()로 가지고 와야 한다
				return article;
			}
		}
		
		return null;
		
	}
	
	
	private void deleteArticle(int id) {
		Article article = getArticle(id);
		
		articles.remove(article);
		
	}
	
	//서비스 메서드 끝
	
	
	
	
	//액션 메서드 시작
	
	
	
	@RequestMapping("/usr/article/doAdd")  //Home에 만들어도 되지만 실무 에서는 따로 나누어 쓴다
	@ResponseBody
	public Article doAdd(String title, String body) {
		//게시물 id, title, body값을 저장 하는 과정
		

		Article article = wirteArticle(title, body);
		
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
		
		Article article = getArticle(id); //getArticle에게 게시물을 찾아 오게 시킨다
		
		if(article == null) {
			return  id + "번 게시물이 존재 하지 않습니다..";
		}
		
		deleteArticle(id);
		
		return  id + "번 게시물이 삭제 되었 습니다.";
	}


	

	
	
	
	
	//액션 메서드 끝



	
	

}

























