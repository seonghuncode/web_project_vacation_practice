package com.ysh.exam.demo.util;

public class Ut {

	//Ut.empty ==> 비어있는지 확인하는 함수 
	public static boolean empty(Object obj) { //util기능을 범용적으로 만들기 위해 Object로 받는다
		
		if(obj == null) {
			return true;
		}
		
		//obj instanceof String ==> obj의 객체는 String객체이다 라는 의미
		if(obj instanceof String == false) { //String이 아니면 true
			return true;
		}
		
		String str = (String)obj;
		
		return str.trim().length() == 0;
		//좌우 공백을 보고 길이를 재서 0이변 비어 있다고 본다
		
		
		//매개변수로 받은게 null이고, String이 아니며, 문자열이라도 공백과 길이를 0으로 한다
		//null값과 String이 아닌것을 공백과 길이를 0으로 해주는 작업이다.
		
	}

}
