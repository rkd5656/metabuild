package kbc.week2.main;

import kbc.week2.oop.ReadFileI;
import kbc.week2.oop.ReadFileImpl;

public class FileToConsole extends ReadFileImpl{

	public static void main(String[] args) {
		ReadFileI ftc = new FileToConsole();
		
		String text = ftc.readFile().getTextNum();
		
		System.out.println(text);
		
	}

}
