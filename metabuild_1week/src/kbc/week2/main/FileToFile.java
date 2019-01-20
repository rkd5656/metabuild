package kbc.week2.main;

import kbc.week2.oop.ReadFileI;
import kbc.week2.oop.ReadFileImpl;
import kbc.week2.oop.TextDto;

public class FileToFile extends ReadFileImpl{

	public static void main(String[] args) {
		
		ReadFileI ftc = new FileToConsole();
		
		TextDto tDto = ftc.readFile();
		
		String text = tDto.getTextNum();
		
		tDto.outPut(text);	
	}

}
