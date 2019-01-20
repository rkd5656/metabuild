package kbc.week2.main;

import kbc.week2.oop.ReadConsoleI;
import kbc.week2.oop.ReadConsoleImpl;

public class ConsoleToConsole extends ReadConsoleImpl{
	
	public static void main(String[] args) {
		
		ReadConsoleI ftc = new ConsoleToConsole();
		
		String text = ftc.readCondole().getTextNum();
		
		System.out.println(text);
	}
	
}
