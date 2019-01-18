package kbc.week2.spring.util;

import kbc.week2.spring.input.InPutI;

public class WordCount {
	
	private String count;
	
	//글자 수 세고 갯수 리턴
	public WordCount(InPutI inPutI) {
		char[] textList = inPutI.getText().toCharArray();
		int textLength = textList.length;
		
		this.count = "문자의 갯수 : "+textLength;
	}
	
	//글자 수 읽기
	public String getCount() {
		return count;
	}
	
}
