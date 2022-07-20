package com.ysh.exam.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysh.exam.demo.service.ArticleService;
import com.ysh.exam.demo.util.Ut;
import com.ysh.exam.demo.vo.Article;
import com.ysh.exam.demo.vo.ResultData;

@Controller
public class UsrArticleController {
	
	@Autowired  //알아서 new를 해준다 시용은 @service 혹은 dao같은 어노테이션을 달은 것에 대해서만 사용한다
	//(컴포넌트에 등록된 것들만 사용 한다), 따로 객체를 만들지 않아도 된다
	private ArticleService articleService;  //서비스를 사용하기 위해
	

	
	@RequestMapping("/usr/article/doAdd")  //Home에 만들어도 되지만 실무 에서는 따로 나누어 쓴다
	@ResponseBody
	public Article doAdd(String title, String body) {
		//게시물 id, title, body값을 저장 하는 과정
		

		int id = articleService.writeArticle(title, body);
		
		Article article = articleService.getArticle(id);
		
		return article;
		//실행 하면 title, body에 null값이 들어간다 즉 url창에서 ?값을 넣어 준다
		
	}
	
	
	
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles(){
		//저장된 게시물을 보여 주는 기능
		return articleService.getArticles();
	}
	
	
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(int id){  //Object는 article, String 모두의 상위 객체로 모든것을 의미 한다(String, article)을 return해 주어야 하기 때문에 object를 사용했다.
		//메서드명은 getArticle이 이미있기 때문에 Action을 붙였다 중요X, 가장 상위 타입이 object이다
		
		Article article = articleService.getArticle(id);
		
		if(article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물이 존재하지 않습니다.", id)); //F-1은 개발자가 그냥 정해주면 된다
		}
		
		return ResultData.from("S-1", Ut.f("%d번 게시물 입니다.", id), article);
		
	}
	
	
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		
		Article article = articleService.getArticle(id); //getArticle에게 게시물을 찾아 오게 시킨다
		
		if(article == null) {
			return  id + "번 게시물이 존재 하지 않습니다..";
		}
		
		articleService.deleteArticle(id);
		
		return  id + "번 게시물이 삭제 되었 습니다.";
	}


	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {
		Article article = articleService.getArticle(id); 
		//getArticle()의 경우 리포지터리 에서 가지고 있지만 컨트롤러 에서 바로 접근을 하면 안되는 구조이기 때문에 인접한 Service에게 요청을 해서 service를 통해 정보를 가지고  온다
		
		if(article == null) {
			return id + "번 게시물이 존재 하지 않습니다.";
		}
		
		articleService.modifyArticle(id, title, body);
		
		return id + "게시물을 수정 하였습니다.";
		
	}



	

	
	
	




	
	

}

























