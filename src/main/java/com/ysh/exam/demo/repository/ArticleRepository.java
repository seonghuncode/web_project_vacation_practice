package com.ysh.exam.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ysh.exam.demo.vo.Article;

@Mapper
public interface ArticleRepository {
	
	
	//데이터 유지, 관리는 DB가 하는게 맞다 
	//하지만 Service는 바로 DB에 접근 할 수 없는 구조이기 때문에 Repository(DAO)애게 요청 한다
	//단순한 로직은 리포지 터리가 할 수 있게 서비스 에서 토스를 한다
	
	
	//이렇게 많은 값들을 지우고 간단하게 할 수 있는 이유 :
	//마이버타스를 사용하고 @Mapper를 사용하면 알아서 되기 때문이다
	//(마이바티스를 가지고 오는 방법은 의존성을 추가해주는 것이다.)
	//mybatis, mysql driver 의존성 주입 + interface + @Mapper + DB접속정보를 추가
	
	//interface안에는 추상 메서드만 넣을 수 있다
	//추상메서드 = 실제 구현부가 없는 것을 의미
	//아래의 코드를 하면 빠진 기능들을 마이바티스가 알아서 추가를 해주어 구현해 준다
	//(하지만 마이바티스가 알아서 할 수 있도록 힌트를 주어야 한다) -> @Select, @Parm 을 사용하여

	
	//INSERT INTO article SET regDate = NOW(), updateDate = NOW(), title = >, body = ?
	public Article writeArticle(String title, String body );

	//(SELECT * FROM article WHERE id = ?) 이러한 쿼리가 실행이 되야 기능이 동작한다
	@Select("select * from article where id = #{id}") // == 아래의 메서드가 실행 되면 이와 같은 쿼리문이 실행 된다 id는 아래의 id를 받는다. 실행하고 Article에 담아서 리턴을 한다
	public Article getArticle(@Param("id") int id);
	
	//(DELETE * FROM article WHERE id = ?)
	public void deleteArticle(int id);
	
	//(UPDATE article SET title = ?, body = ?, updateDate = NOW() WHERE id = ?)
	public void modifyArticle(int id, String title, String body);

	//(SELECT * FROM article ORDER BY id DESC)
	public List<Article> getArticles();
	
	
	//서비스 메서드 끝
	
		
	
}
