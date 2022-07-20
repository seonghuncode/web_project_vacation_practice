package com.ysh.exam.demo.vo;

import lombok.Getter;

public class ResultData {
	//(만드는 형식이 따로 있는것이 아니라 우선 이러한 형식을 어떻게 사용하면 되는지 알고 있으면 된다.)
	//S-1 : success에 대힌 내용이 담긴다
	//F-1 : 실패에 대한 내용이 담긴다.
	@Getter
	private String resultCode;	//보고서에 성공 유무
	@Getter
	private String msg; //보고서에 메세지
	@Getter
	private Object data1;
	
	//private는 외부에서 접근할 수 없다
	private ResultData() {
		
	}
	
	
	public static ResultData from(String resultCode, String msg) {
		return from(resultCode, msg, null);
	} //메서드 오버로딩 법칙을 위해 두개만 받을 수 있는 메서드도 만들어 준다
	
	
	
	public static ResultData from(String resultCode, String msg, Object data1) {
		ResultData rd = new ResultData();
		rd.resultCode = resultCode;
		rd.msg = msg;
		rd.data1 = data1;
		
		return rd;
	}
	
	//성공을 했는가?
	public boolean isSuccess() {
		return resultCode.startsWith("S-"); //성공의 유형에 따라 S-1, S-2이든 S-로 시작하면 성공이다.
	}
	
	public boolean isFail() {
		return isSuccess() == false;
	}


	public static ResultData newData(ResultData joinRd, Object newData) {

		return from(joinRd.getResultCode(), joinRd.msg, newData);
	}
	
	
	
}
