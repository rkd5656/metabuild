package kbc.week2.oop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextDto {
	
	//문자 갯수
	private String textNum;
	
	//객체 생성과 동시에 문자열 갯수 정의
	public TextDto(String text) {
		char[] textList = text.toCharArray();
		int textLength = textList.length;
		
		this.textNum = "문자의 갯수 : " +textLength;
	}

	public String getTextNum() {
		return textNum;
	}
	
	//파일 출력
	public void outPut(String text) {
		FileOutputStream fileOutputStream = null;
		BufferedWriter bufferedWriter = null;
		OutputStreamWriter outputStreamWriter = null;
		//파일 중복 방지
		Date date2 = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");	
		String filePath = "D:\\Develope\\file\\week_"+date.format(date2)+".txt";
		
		try {
			
			File file = new File(filePath);
			
			fileOutputStream = new FileOutputStream(filePath);
			outputStreamWriter = new OutputStreamWriter(fileOutputStream, "MS949");
			bufferedWriter = new BufferedWriter(outputStreamWriter);
            
			if(file.isFile() && file.canWrite()) {
				bufferedWriter.write(text);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedWriter.close();
				outputStreamWriter.close();
				fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
