package com.ysh.exam.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ysh.exam.demo.vo.Article;

//패키지명에 있어 항상 demo application이 가지고 있는 부분 까지는 무조건 써주어야 한다

@Service //현재 스프링 프레임 워크를 사용하고 있고 프레임 워크에 service라는 것을 알려주기 위해 사용
//객체 생성에 관련해서 알아서 관리를 해준다
public class ArticleService {
	
	
		//인스턴스 변수 시작
		
		private int articlesLastId;
		private List<Article> articles; 
		//인스턴스 변수 끝
		
		
		//생성자
		public ArticleService() {
			//articleService = new ArticleService();  //@Service라는 어노테이션을 달면 컴포너트로 의존성 주입 처럼 되기 때무네 이것을 적지 않고 @AutoWired를 위에 사용해주면 된다
			
			articlesLastId = 0;
			articles = new ArrayList<>();
			
			makeTestData();
		}
		
		
		//서비스 메서드 시작
		private void makeTestData() {
			for(int i = 1; i <= 10; i++) {
				String title = "제목" + i;
				String body = "내용" + i;
			
				writeArticle(title,body);
			
			}
		}
		
		
		public Article writeArticle(String title, String body ) {
			//중복된는 로직을 따로 만들어 메서드 호출로 간편하게 사용하기 위해 만들 었다
				int id = articlesLastId + 1;
				Article article = new Article(id, title, body);
				
				articles.add(article);
				articlesLastId = id;
				
				return article;
		}
		
		
		
		public Article getArticle(int id) {
			
			for(Article article : articles){  //향상된 for문으로  articles에 들어 있는 것이 끝날때 까지 반복
				if(article.getId() == id) { //private이기 때문에 getter를 이용해서 getID()로 가지고 와야 한다
					return article;
				}
			}
			
			return null;
			
		}
		
		
		public void deleteArticle(int id) {
			Article article = getArticle(id);
			
			articles.remove(article);
			
		}
		
		
		public void modifyArticle(int id, String title, String body) {
			
			//수정을 하려면 일단 수정할 게시물을 찾아와야 한다
			Article article = getArticle(id);
			
			article.setTitle(title);
			article.setBody(body);
			
			
		}


		public List<Article> getArticles() {
			return articles;
		}
		
		
		//서비스 메서드 끝
	
}
