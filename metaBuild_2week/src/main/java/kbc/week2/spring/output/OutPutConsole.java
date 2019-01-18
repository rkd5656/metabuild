package kbc.week2.spring.output;

import kbc.week2.spring.util.WordCount;

public class OutPutConsole implements OutPutI {
	
	//생성자(글자수 콘솔로 내보내기)
	public OutPutConsole(WordCount wordCount) {
		outPutText(wordCount.getCount());
	}

	public void outPutText(String count) {
		System.out.println(count);
	}

}
