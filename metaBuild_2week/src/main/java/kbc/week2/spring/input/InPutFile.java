package kbc.week2.spring.input;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class InPutFile implements InPutI {

	private String text ="";
	private String filePath = "";
	
	//생성자
	public InPutFile(String filePath) {
		this.filePath = filePath;
		this.text = inPutText();
	}

	//파일 글 가져오기
	public String getText() {
		return text;
	}
	
	//파일 글 읽은 후 내뱉기
	public String inPutText() {
		
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		
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
		return text;
	}

}
