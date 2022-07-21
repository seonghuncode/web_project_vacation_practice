package com.ysh.exam.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


//vo의 경우 간단하게 value object로 간단한 article들을 모아두는 용도



////@Getter를 일일이 붙이기 그러면, @Data를 붙여도 된다
//@AllArgsConstructor // 객체를 생성할때 매개변수로 받을 수 있다. 즉 생성자를 3개 받아야 한다
//@NoArgsConstructor //인자 없는 생성자를 만들 수 있다.
//public class Article{    //파일명에 해당 하는 class는 무조건 public이 있어야 한다.
//	@Getter //private이기 때문에 public 으로 바꾸는 대신
//	private int id;
//	@Getter
//	private String title;
//	@Getter
//	private String body;
//	
////	public Article() {
////		id = 1;
////		title = "제목1";
////	}  @AllArgsConstructor 사용하면 생성자를 만들어 줄 필요가 없다
//}



//위의 코드와 동일한 코드 getter setter를 사용하기 위해서는 @Data 어노테이션이 필요하다


@Data
@AllArgsConstructor // 객체를 생성할때 매개변수로 받을 수 있다. 즉 생성자를 3개 받아야 한다
public class Article{    //파일명에 해당 하는 class는 무조건 public이 있어야 한다.
	
	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private String title;
	private String body;
	
}


