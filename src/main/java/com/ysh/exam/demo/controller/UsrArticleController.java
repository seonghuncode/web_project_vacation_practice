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
	public ResultData<Article> doAdd(String title, String body) {
		//<Article>은 해도 안해도 큰 의미는 없다
		
		if(Ut.empty(title)) {
			return ResultData.from("F-1", "title(을)를 입력해 주세요.");
		}
		if(Ut.empty(body)) {
			return ResultData.from("F-1", "body(을)를 입력해 주세요.");
		}
		
		//게시물 id, title, body값을 저장 하는 과정
//		ResultData writeArticleRd = articleService.writeArticle(title, body);  //원래는 1만 주었다면 이제는 resultCode, msg도 준다.
//		int id = (int)writeArticleRd.getData1();
		
		//(int를 사용하지 않기 위해서 수정한 코드)
		//ResultData가 품고 있는 보고서의 메인 데이터는 int이다. -> 형변환을 해주지 않아도 된다
		ResultData<Integer> writeArticleRd = articleService.writeArticle(title, body);  //원래는 1만 주었다면 이제는 resultCode, msg도 준다.
		int id = writeArticleRd.getData1();
		//현재 받은 데이터
		//S-1
		//4번 게시뭏 입니다.
		//4  
		// ==> 고객에게 보여 줄때는 
		
		Article article = articleService.getArticle(id);
		
		return ResultData.newData(writeArticleRd, article);
		
		
	}
	
	
	
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public ResultData<List<Article>> getArticles(){
		//자바에서는 이러한 형태를 자세히 알려 주면 좋기 때문에 <List<Article>>사용
		//안해주어도 상관은 없다
		
		List<Article> articles = articleService.getArticles();
		
		//저장된 게시물을 보여 주는 기능
		return ResultData.from("S-1", "게시물 리스트 입니다.", articles);
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
	public ResultData<Integer> doDelete(int id) {
		
		Article article = articleService.getArticle(id); //getArticle에게 게시물을 찾아 오게 시킨다
		
		if(article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		articleService.deleteArticle(id);
		
		return  ResultData.from("S-1", Ut.f("%d번 게시물을 삭제 했습니다.", id), id);
		//마지막 id는 어떤 게시뭏을 삭제 하였는지
	}


	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData<Integer> doModify(int id, String title, String body) {
		Article article = articleService.getArticle(id); 
		//getArticle()의 경우 리포지터리 에서 가지고 있지만 컨트롤러 에서 바로 접근을 하면 안되는 구조이기 때문에 인접한 Service에게 요청을 해서 service를 통해 정보를 가지고  온다
		
		if(article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물은 존재 하지 않습니다.", id));
		}
		
		articleService.modifyArticle(id, title, body);
		

		return ResultData.from("S-1", Ut.f("%d번 게시물을 수정하였습니다.", id), id);
	}



	

	
	
	




	
	

}

























