package kbc.week2.spring.input;

import java.util.Scanner;

public class InPutConsole implements InPutI {

	private String text;
	
	//생성자
	public InPutConsole() {
		this.text = inPutText();
	}
	
	//텍스트 읽기
	public String getText() {
		return text;
	}
	
	//텍스트 콘솔에서 가져오기
	public String inPutText() {
		System.out.println("문자열을 입력 해주세요");
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		return scan.nextLine();
	}

}
