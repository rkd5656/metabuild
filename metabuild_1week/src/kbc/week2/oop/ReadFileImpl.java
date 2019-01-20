package kbc.week2.oop;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFileImpl implements ReadFileI{

	@Override
	public TextDto readFile() {
		
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		String text = "";
		String filePath = "D:\\Develope\\file\\week_1.txt";
		
		try {			
			fileInputStream = new FileInputStream(filePath);
			inputStreamReader = new InputStreamReader(fileInputStream, "MS949");
			bufferedReader = new BufferedReader(inputStreamReader);
			
			String line = "";
            while((line = bufferedReader.readLine()) != null){
            	text += line;
            }
            
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
				inputStreamReader.close();
				fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		TextDto tDto = new TextDto(text);
		
		return tDto;
		
	}

}
