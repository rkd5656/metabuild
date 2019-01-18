package kbc.week2.spring.output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import kbc.week2.spring.util.FilePath;
import kbc.week2.spring.util.WordCount;

public class OutPutFile implements OutPutI {
	
	private String filePath;
	
	//생성자 (파일 패스와 글자 수 내보내기 바로 실행)
	public OutPutFile(WordCount wordCount, FilePath filePath) {
		this.filePath = filePath.filePath();
		outPutText(wordCount.getCount());
	}

	//글자 수 내보내기
	public void outPutText(String count) {
		
		FileOutputStream fileOutputStream = null;
		BufferedWriter bufferedWriter = null;
		OutputStreamWriter outputStreamWriter = null;
		
		try {
			
			File file = new File(filePath);
			
			fileOutputStream = new FileOutputStream(filePath);
			outputStreamWriter = new OutputStreamWriter(fileOutputStream, "MS949");
			bufferedWriter = new BufferedWriter(outputStreamWriter);
            
			if(file.isFile() && file.canWrite()) {
				bufferedWriter.write(count);
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
