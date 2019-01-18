package kbc.week2.spring.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FilePath {
	
	
	//파일 중복 방지
	public String filePath(){ 
		
		Date date2 = new Date();
		
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
		
		String filePath = "D:\\Develope\\file\\week_"+date.format(date2)+".txt";
		
		return filePath;
	}
}
