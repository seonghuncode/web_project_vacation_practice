package com.ysh.exam.demo.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.ysh.exam.demo.repository.ArticleRepository;
import com.ysh.exam.demo.util.Ut;
import com.ysh.exam.demo.vo.Article;
import com.ysh.exam.demo.vo.ResultData;

//패키지명에 있어 항상 demo application이 가지고 있는 부분 까지는 무조건 써주어야 한다

@Service //현재 스프링 프레임 워크를 사용하고 있고 프레임 워크에 service라는 것을 알려주기 위해 사용
//객체 생성에 관련해서 알아서 관리를 해준다
//서비스는 넘겨주기만 하는 구조 (코드가 복잡해 지고 많아지면 진가가 나온다)
public class ArticleService {
	
//		@Autowired // 는 객체를 만드는데(객체생성)
//		private ArticleRepository articleRepository; 여기에 연결되 었다가 진행되기를 생성자가 기다려야 하는데 그렇지 않기 때문에 테스트 데이터를 생성하는데 있어 문제가 된다
//
//		public ArticleService() {
//			makeTestData();  //@Autowired가 객체를 생성하고 생성자는 바로실행 되기 때문에 약간 기다려야 되는데 바로 실행이 되기 때문에 testDate가 제대로 실행이 되지 않는다
//		}
		
	//해결 방안  생성자 주입 방법을 사용 한다
	private ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}
	



		public ResultData<Integer> writeArticle(int memberId, String title, String body) {
			articleRepository.writeArticle(memberId, title, body); //write의 경우 들어가는 글이기 때문애 return값이 없다 ==> 어떤 값이 들어왔는지 알기 위해서는 lastInsertId를 만들어 값을 받는다
			int id = articleRepository.getLastInsertId();
			
			return ResultData.from("S-1", Ut.f("%d번 게시물이 생성 되었습니다.", id), id);
		}


		//서비스는 데이터 로직만 관리하고 실제 데이터는 리포지터리가 가지고 있기 때문에 리포지터리 에게 토스
		public List<Article> getArticles() {
			return articleRepository.getArticles();
		}



		public Article getArticle(int id) {
			return articleRepository.getArticle(id);
		}



		public void deleteArticle(int id) {
			articleRepository.deleteArticle(id);
			
		}



		public ResultData<Article> modifyArticle(int id, String title, String body) {
			
			articleRepository.modifyArticle(id, title, body);
			
			Article article = getArticle(id);
			
			return ResultData.from("S-1", Ut.f("%d번 게시물이 수정 되었습니다.", id), article);
			
		}




		public ResultData actorCanModify(int actorId, Article article) {
			if(article == null) {
				return ResultData.from("F-1", "권한이 없습니다.");
			}
			if(article.getMemberId() != actorId) {
				return ResultData.from("F-2", "수정 권한이 없습니다.");
			}
			
			return ResultData.from("S-1", "수정이 가능합니다.");
			
		}
		
		
		
}
