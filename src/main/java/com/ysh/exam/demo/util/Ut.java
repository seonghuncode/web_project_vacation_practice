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

	
	//f는 format의 약자로 사용
	public static String f(String format, Object... args) { 
		//loginId하나 혹은 name, email두개가 들어 올 수 있기 때문에 Object... args를 사용하면 ==> 뒤에 있는것이 n이든 간에 배열에 들어 간다. 
		return String.format(format, args);
	}//이 구조는 나중에 복사 붙여 넣기 해서 사용할 수 았으면 된다

}


 










