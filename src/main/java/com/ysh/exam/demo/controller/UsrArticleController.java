package com.ysh.exam.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public ResultData<Article> doAdd(HttpSession httpSession, String title, String body) {
		//<Article>은 해도 안해도 큰 의미는 없다 : 사용할꺼면 정상적으로 실행이 될때 담고 있는 것에 맞춘다
		
		boolean isLogined = false;
		int loginedMemberId = 0; //로그인을 하지 않을 것으로 가정
		
		if(httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int)httpSession.getAttribute("loginedMemberId");
		}
		
		if(isLogined == false) {
			return ResultData.from("F-A", "로그인후 이용해 주세요");
		}
		
		
		
		if(Ut.empty(title)) {
			return ResultData.from("F-1", "title(을)를 입력해 주세요.");
		}
		if(Ut.empty(body)) {
			return ResultData.from("F-2", "body(을)를 입력해 주세요.");
		}
		
		//게시물 id, title, body값을 저장 하는 과정
//		ResultData writeArticleRd = articleService.writeArticle(title, body);  //원래는 1만 주었다면 이제는 resultCode, msg도 준다.
//		int id = (int)writeArticleRd.getData1();
		
		//(int를 사용하지 않기 위해서 수정한 코드)
		//ResultData가 품고 있는 보고서의 메인 데이터는 int이다. -> 형변환을 해주지 않아도 된다
		ResultData<Integer> writeArticleRd = articleService.writeArticle(loginedMemberId, title, body);  //원래는 1만 주었다면 이제는 resultCode, msg도 준다.
		int id = writeArticleRd.getData1();     //누가 작성 했는지 알기 위해 Session을 넣어준다
		//현재 받은 데이터
		//S-1
		//4번 게시뭏 입니다.
		//4  
		// ==> 고객에게 보여 줄때는 
		
		Article article = articleService.getArticle(id);
		
		return ResultData.newData(writeArticleRd, "article", article);
		
		
	}
	
	
	
	@RequestMapping("/usr/article/list")
	public String showList(Model model){
		//자바에서는 이러한 형태를 자세히 알려 주면 좋기 때문에 <List<Article>>사용
		//안해주어도 상관은 없다
		
		List<Article> articles = articleService.getArticles();
		
		//jsp파일로 값을 보내주는 방법
		model.addAttribute("articles", articles);
		
		
		return "usr/article/list";
	}
	
	
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData<Article> getArticle(int id){  //Object는 article, String 모두의 상위 객체로 모든것을 의미 한다(String, article)을 return해 주어야 하기 때문에 object를 사용했다.
		//메서드명은 getArticle이 이미있기 때문에 Action을 붙였다 중요X, 가장 상위 타입이 object이다
		
		Article article = articleService.getArticle(id);
		
		if(article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물이 존재하지 않습니다.", id)); //F-1은 개발자가 그냥 정해주면 된다
		}
		
		return ResultData.from("S-1", Ut.f("%d번 게시물 입니다.", id), "article", article);
		
	}
	
	
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData<Integer> doDelete(HttpSession httpSession, int id) {
		
		boolean isLogined = false;
		int loginedMemberId = 0;
		
		if(httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int)httpSession.getAttribute("loginedMemberId");
		}
		
		if(isLogined == false) {
			return ResultData.from("F-A", "로그인 후 이용해 주세요.");
		}
		
		
		Article article = articleService.getArticle(id); //getArticle에게 게시물을 찾아 오게 시킨다
		
		if(article.getMemberId() != loginedMemberId) {
			return ResultData.from("F-2", "삭제할 권한이 없습니다.");
		}
		
		
		
		if(article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		articleService.deleteArticle(id);
		
		return  ResultData.from("S-1", Ut.f("%d번 게시물을 삭제 했습니다.", id), "id",  id);
		//마지막 id는 어떤 게시뭏을 삭제 하였는지
	}


	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData<Article> doModify(HttpSession httpSession, int id, String title, String body) {
	//ResultData<Article> : ResultData인데 내용물은 Article이다.	
		
		
		//1. 로그인 여부를 체크 한다
		boolean isLogined = false;
		int loginedMemberId = 0;
		
		if(httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}
		
		if(isLogined == false) {
			return ResultData.from("F-A", "로그인 후 이용해 주세요.");
		}
		//-----------------------------------------------------------
		
		
		//2. 게시물을 구해서 게시뭏이 없으면 오류 메시지 보여주기
		Article article = articleService.getArticle(id); 
		//getArticle()의 경우 리포지터리 에서 가지고 있지만 컨트롤러 에서 바로 접근을 하면 안되는 구조이기 때문에 인접한 Service에게 요청을 해서 service를 통해 정보를 가지고  온다
		
		if(article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시물은 존재 하지 않습니다.", id));
		}
		//-------------------------------------------------------------
		
		
		//수정 권한은 서비스 한테 시켜도 된다.(컨트롤러는 인포데스크 직원으로 복잡한 것을 시키면 안되지만 이러한 간단한 것은 시켜도 된다)
//		if(article.getMemberId() != loginedMemberId){
//			return ResultData.from("F-2", "해당 게시물 수정 권한이 없습니다");
//		}
		
		//위의 방법 대신 서비스에게 시키는 방법(logineMemberId가 article을 수정할 수 있는가에 대한 기능을 수행)
		//3. 수정 요청을 넘기기 전에 logineMemberId가 article을 수정 가능한지 확인하는 기능 수행
		ResultData actorCanModifyRd =  articleService.actorCanModify(loginedMemberId, article);
		
		
		if(actorCanModifyRd.isFail()) { //실패하면 actorCanModifyRd보고서를 리턴해라
			return actorCanModifyRd;
		}
		//----------------------------------------------------------------
		
		
		//4. 위의 모든 조건을 만족하고 여기 까지 왔다면 수정이 가능하다.
		return articleService.modifyArticle(id, title, body);
		
	}



	

	
	
	




	
	

}

























