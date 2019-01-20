package kbc.week2.main;

import kbc.week2.oop.ReadConsoleI;
import kbc.week2.oop.ReadConsoleImpl;
import kbc.week2.oop.TextDto;

public class ConsoleToFile extends ReadConsoleImpl{

	public static void main(String[] args) {
	
		ReadConsoleI ftc = new ConsoleToFile();
		
		TextDto tDto = ftc.readCondole();
		
		String text = tDto.getTextNum();
		
		tDto.outPut(text);
		
	}
	
}
