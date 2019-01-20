package kbc.week2.oop;

import java.util.Scanner;

//콘솔 입력 후 물자열 갯 수 가져오기
public class ReadConsoleImpl implements ReadConsoleI {

	@Override
	public TextDto readCondole() {
		
		System.out.println("문자열을 입력 해주세요");
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		String text = scan.nextLine();
		
		System.out.println();
		
		TextDto tDto = new TextDto(text);
		
		return tDto;
	}

}
