package com.ysh.exam.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ysh.exam.demo.vo.Article;

@Component //@Service랑 @Component랑은 같은 것이다.(깉은 것이라고 생각을 하면 된다)
public class ArticleRepository {
	
	
	//데이터 유지, 관리는 DB가 하는게 맞다 
	//하지만 Service는 바로 DB에 접근 할 수 없는 구조이기 때문에 Repository(DAO)애게 요청 한다
	//단순한 로직은 리포지 터리가 할 수 있게 서비스 에서 토스를 한다
	
	
	

	private int articlesLastId;
	private List<Article> articles; 
	
	
	
	public ArticleRepository() {
		//articleService = new ArticleService();  //@Service라는 어노테이션을 달면 컴포너트로 의존성 주입 처럼 되기 때무네 이것을 적지 않고 @AutoWired를 위에 사용해주면 된다
		
		articlesLastId = 0;
		articles = new ArrayList<>();
		
	}
	
	
	//테스트 데이터의 경우 리포지 터리에 있으면 안된다. 이유는 
	//리포지터리가 하는 일은 어떠한 데이터를 저장, 찾아서 주기, 지우기..... => 단순한 일(창고직 같은것), 복잡한 로직은 주면 안된다
	public void makeTestData() {
		for(int i = 1; i <= 10; i++) {
			String title = "제목" + i;
			String body = "내용" + i;
		
			writeArticle(title,body);
		
		}
	}
	
	
	//서비스 메서드 시작

	
	
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
