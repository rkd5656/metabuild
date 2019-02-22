package com.meta.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;


public class LogAnalysis {
		
	/**
	 * 추출 키워드의 재사용을 위한 field객체 
	 */
	private String[] keyWord = { /*[0]*/"##galileo_bean start.",/*[1]*/"ESB_TRAN_ID :",/*[2]*/"Content-Length:",
			/*[3]*/"#galileo call time:", /*[4]*/"1. Before Marshalling", /*[5]*/"2. Marshalling",
			/*[6]*/"3. Invoking galileo", /*[7]*/"4. Unmarshalling and Send to CmmMod Server",
			/*[8]*/"##galileo_bean end.", /*[9]*/"eclipse.galileo-bean-thread-" };
	
	/**
	 * filePath에 맞는 log파일을 불러온 후 스캐너에 담아 줄 단위로 원하는 키워드에 맞게
	 * 추출한 뒤 Pattern에 맞춰 split을 해준 후 그 배열을 return
	 * 
	 * 1. file을 가져온 후 스캐너에 담아 줌.
	 * 2. scanner에 있는 String중 원하는 키워드가 있는 줄만 가져와 StringBuffer에 담아 줌.
	 * 3. Pattern과 정규식을 이용해 시간별로 짜르기 위해 compile
	 * 4. split으로 짜른 후 return
	 * 
	 * @return : 불러온 로그를 split으로 구분 해 배열을 return
	 */
	private String[] inPutLog() {
		//가져올 log파일 경로
		String filePath = "D:\\WorkingSpace\\log\\galileo.log";
		//값을 담아 줌 StringBuffer
		StringBuffer text = new StringBuffer();

		Pattern pattern = null;
		
		Scanner scan =null;
		
		try {
			
			File file = new File(filePath);
			//1
			scan = new Scanner(file);
			//2
			while (scan.hasNextLine()) {
				String str = scan.nextLine();
				if (str.contains(keyWord[0]) || str.contains(keyWord[1]) || str.contains(keyWord[2])
						|| str.contains(keyWord[3]) || str.contains(keyWord[4]) || str.contains(keyWord[5])
						|| str.contains(keyWord[6]) || str.contains(keyWord[7]) || str.contains(keyWord[8])
						|| str.contains(keyWord[9])) {
					text.append(str);
					text.append(" ");
				}
			}
			//3
			pattern = Pattern.compile("(?=\\[([0-9][0-9].[0-1][0-9].[0-3][0-9]\\s[0-2][0-9]:[0-5][0-9]))");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			//스캐너를 닫아 줌
			scan.close();
		}
		//4
		return pattern.split(text);
	}
	
	/**
	 * inPutLog()에서 log파일의 배열을 가져오고 thread번호를 이용해 필터링을 거쳐
	 * keyWord를 주출하여 값들을 List에 담아주고 return해주는 기능
	 * 
	 * 1. inPutLog()를 사용해 log를 가져옴
	 * 2. 계속적으로 실행 될 thread의 번호를 key값으로 하고 해당 thread들의 keyWord들 값을 저장해줄
	 * 	  LogDto를 value로 하는 Map<String, LogDto>을 선언
	 * 3. log파일 배열을 for문을 돌림
	 *  3-1. thread 번호만 추출 하여 담아줌
	 *  3-2. 번호별로 map을 만들어 주며 이미 있는 쓰레드 번호는 담아주지 않음. value는 null.
	 *  3-3. keyWord별로 추출 -> 해당 if문에 직접 주석
	 *   - 키워드별로 추출하되 해당 쓰레드 번호가 모든 keyWord를 dto에 담지 못할 경우
	 *     무시하고 null로 만들어줌. 그리고 start에서 다시 dto생성
	 *  3-4. dto에 값이 가득 찼을 시 List에 담아주고 dto를 null로 만들어 줌
	 *  
	 * 4. for문이 다 돌았을 시 List를 start시간 순으로 sort
	 * 5. List를 return
	 * 
	 * @return List<LogDto> : start부터 end까지 값이 가득 찬 dto들을 return
	 */
	private List<LogDto> dataExtract() {
		//1
		String[] texts = inPutLog();
		//2
		Map<String, LogDto> tempMap = new HashMap<String, LogDto>();

		List<LogDto> dtoList = new ArrayList<LogDto>();
		//3
		for (String s : texts) {
			//3-1
			int idx = s.indexOf(keyWord[9]) + keyWord[9].length();
			
			String key = s.substring(idx, idx + 8);
			//3-2
			if (!tempMap.containsKey(key)) {
				
				tempMap.put(key, null);
				
			}
			
			/* [0] ##galileo_bean start.
			 * [1] ESB_TRAN_ID :
			 * [2] Content-Length:
			 * [3] #galileo call time:
			 * [4] 1. Before Marshalling
			 * [5] 2. Marshalling
			 * [6] 3. Invoking galileo
			 * [7] 4. Unmarshalling and Send to CmmMod Server
			 * [8] ##galileo_bean end.*/
			
			//3-3
			//##galileo_bean start.를 추출하며 start이기에 logDto객체 생성
			if (s.contains(keyWord[0])) {
				
				tempMap.put(key, new LogDto());
				//start를 담아줌
				tempMap.get(key).setStart(
						s.substring(s.indexOf("[") + 1, s.indexOf("]")));
			//ESB_TRAN_ID :를 추출하며, start를 거치지 못 할경우 무시.
			} else if (tempMap.get(key) != null && s.contains(keyWord[1])) { 
				//DTO가 null이 아니고 esbId 가 null일 경우 esbId를 넣어 줌.
				if (tempMap.get(key).getEsbId() == null) {
					
					tempMap.get(key).setEsbId(
							s.substring(s.indexOf(keyWord[1])+1 +
									keyWord[1].length(), s.lastIndexOf(" ")));
					
					//하지만 esbId의 값이 존재한다면 start와 end를 거치지 않은
					//잘못된 data이기 때문에 dto를 null로 만들어줌. esbId는 항상 존재 하고 고유하기 때문에 가능
				} else {
					tempMap.put(key, null);
				}
			//Content-Length:를 추출하고 조건 판단 후 data를 넣어줌 
			} else if (tempMap.get(key) != null && tempMap.get(key).getConLen()== null
					&& s.contains(keyWord[2])) {// content
				
				tempMap.get(key).setConLen(
						s.substring(s.indexOf(keyWord[2]) +
								keyWord[2].length(), s.lastIndexOf(" ")));
			//#galileo call time:를 추출하고 조건 판단 후 data를 넣어줌
			} else if (tempMap.get(key) != null && tempMap.get(key).getCallTime() == null
					&& s.contains(keyWord[3])) {

				tempMap.get(key).setCallTime(
						s.substring(s.indexOf(keyWord[3]) + keyWord[3].length(), s.lastIndexOf(" ")-3));
			//1. Before Marshalling, 2. Marshalling, 3. Invoking galileo, 4. Unmarshalling and Send to CmmMod Server
			//네가지 모두 존재할 시 추출 하고 조건 판단 후 data를 각각 넣어줌.
			} else if (tempMap.get(key) != null && tempMap.get(key).getBefM() == null
					&& s.contains(keyWord[4])&& s.contains(keyWord[5])
					&& s.contains(keyWord[6])&& s.contains(keyWord[7])) {

				tempMap.get(key).setBefM(
						s.substring(s.indexOf(keyWord[4]) - 13, s.indexOf(keyWord[4]) - 8));
				
				tempMap.get(key).setNowM(
						s.substring(s.indexOf(keyWord[4]) + keyWord[4].length()+1,
								s.indexOf(keyWord[4])+keyWord[4].length()+6));

				tempMap.get(key).setInvoGal(
						s.substring(s.indexOf(keyWord[5])+keyWord[5].length()+1,
								s.indexOf(keyWord[5])+keyWord[5].length()+6));
				
				tempMap.get(key).setUnM(
						s.substring(s.indexOf(keyWord[6])+keyWord[6].length()+1,
								s.indexOf(keyWord[6])+keyWord[6].length()+6));
			//##galileo_bean end.를 추출하고, 조건 판단 후 data를 집어 넣음
			} else if (tempMap.get(key) != null &&
					tempMap.get(key).getEnd() == null && s.contains(keyWord[8])) {
				
				tempMap.get(key).setEnd(s.substring(s.indexOf("[") + 1, s.indexOf("]")));
				//DTO안에 모든 data가 들어 있는지 판단 후 하나라도 data가 없다면
				//해당 key의 dto를 null로 만듬 
				if (tempMap.get(key).getStart() == null || tempMap.get(key).getEsbId() == null
						|| tempMap.get(key).getConLen() == null || tempMap.get(key).getCallTime() == null
						|| tempMap.get(key).getBefM() == null || tempMap.get(key).getEnd() == null) {
					tempMap.put(key, null);
					
				//3-4
				//DTO안에 모든 data가 존재 한다면, List에 dto를 추가하고 해당 key의 dto를 null로 만듬. 
				} else {
					
					dtoList.add(tempMap.get(key));
					
					tempMap.put(key, null);
				}
			}
		}
		
		//4
		//시작 시간 순으로 정렬
		Collections.sort(dtoList, new Comparator<LogDto>() {
			public int compare(LogDto o1, LogDto o2) {
			        return o1.getStart().compareTo(o2.getStart());
			    }
			});
		
		//5
		return dtoList;
	}

	
	/**
	 * dataExtract()에서 data를 받아와 최종적 결과파일 2개를 만들고 출력해주는 기능
	 * 
	 * 1. dataExtract()를 통해 시작시간 순의 data가 담겨있는 List<LogDto> 객체를 가져옴
	 * 2. outLogData(data);를 통해 파일 1번과 2번의 내용이 담겨 있는 StringBuffer객체 두개를 받아옴.
	 * 3. outFile(filePath, String)를 이용해 두개의 파일을 출력
	 */
	private void outLog() {
		//1
		List<LogDto> data = dataExtract();
		//파일 1번의 경로	
		String filePath = "D:\\WorkingSpace\\logOut\\logData.log";
		//파일 2번의 경로
		String filePath2 = "D:\\WorkingSpace\\logOut\\logTimeSize.log";
		//2
		StringBuffer[] sb = outLogData(data);
		//3
		outFile(filePath2, sb[0].toString());
		//3
		outFile(filePath, sb[1].toString());
	}
	
	/**
	 * 경로와 원하는 파일의 내용을 받아 파일을 UTF-8로 내보내주는 기능
	 * 
	 * 1. 파일을 추출 File,FileOutputStream,BufferedWriter,OutputStreamWriter
	 * 2. UTF-8로 설정
	 * 3. 작성가능 판단
	 * 4. stream을 finally로 close
	 * @param filePath : 파일경로
	 * @param sb : 해당 파일의 내용
	 */
	private void outFile(String filePath, String sb) {
		//1
		FileOutputStream fileOutputStream = null;
		
		BufferedWriter bufferedWriter = null;
		
		OutputStreamWriter outputStreamWriter = null;
		
		try {
			
			File file = new File(filePath);
			
			fileOutputStream = new FileOutputStream(filePath);
			//2
			outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
			
			bufferedWriter = new BufferedWriter(outputStreamWriter);
			
			//3
			if(file.isFile() && file.canWrite()) {
				bufferedWriter.write(sb);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				//4
				bufferedWriter.close();
				outputStreamWriter.close();
				fileOutputStream.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * log파일 dto의 data를 이용해 결과 형식에 맞게
	 * 파일1번과 파일2번의 내용을 담은 StringBuffer[] 객체를 return하는 기능
	 * 
	 * 1. 받은 data를 for문을 돌림
	 *  1-1 해당 data의 처리시간을 계산
	 *  1-2 처리건수 계산
	 *  1-3 평균시간계산을 위해 time을 더해줌
	 *  1-4 평균사이즈 계산을 위해 현재 data의 conLen을 더해줌
	 *  1-5 첫번째 파일일 땐 최소 시간과 최소 사이즈를 정해줌.
	 *  1-6 최소, 최대 시간 및 사이즈 설정
	 *  1-7 다음에 체크할 dto가 시간이 바뀔 시 실행
	 *   1-7-1 형식대로 버퍼에 append해줌
	 *   1-7-2 현재 파일이 마지막 데이터가 아니면 계행을 해주면, 마지막일 시 해당 if문을 나감
	 *   1-7-3 startDate등 초기화
	 *  1-8 파일 1번의 내용을 buffer에 append
	 * 2. for문이 다 돌고 StringBuffer 두개를 return
	 * 
	 * @param data : log파일 dto가 담기 List를 넣어줌
	 * @return StringBuffer[] : 파일1, 파일2의 내용이 담겨있는 StringBuffer두개를 return
	 */
	private StringBuffer[] outLogData(List<LogDto> data) {
		//           파일 1번                              파일 2번
		StringBuffer dataSb = new StringBuffer(), averageSb = new StringBuffer();
		//시간게산을 위해 선언
		SimpleDateFormat sdf = new SimpleDateFormat("YY.MM.DD hh:mm:ss"), sdf2 = new SimpleDateFormat("YY.MM.DD hh:mm");
		//파일 2번의 내용을 만들기 위해 선언
		//기준 시간
		String startDate =data.get(0).getStart().substring(0, data.get(0).getStart().length()-3);
		//처리건, 평균시간, 평균사이즈, 최소사이즈, 최대 사이즈
		int totalProcess = 0, averageTime = 0, averageSize = 0, minSize = 0, maxSize = 0;
		//최소 시간, 최대 시간
		long minTime = 0, maxTime = 0;
		//1
		for (int i = 0; i < data.size(); i++) {
			
			LogDto ld = null;
			
			try {

				ld = data.get(i);
				//1-1
				long time = sdf.parse(ld.getEnd()).getTime()-sdf.parse(ld.getStart()).getTime();
				//1-2
				totalProcess++;
				//1-3
				averageTime += time;
				//1-4
				averageSize += Integer.parseInt(ld.getConLen());
				//1-5
				if (i==0) {
					minTime = time;
					minSize = Integer.parseInt(ld.getConLen());
				}
				//1-6
				//최소시간
				if (minTime>time) {
					minTime = time;
				}
				//최대시간
				if (maxTime<time) {
					maxTime = time;
				}
				//최소사이즈
				if (minSize>Integer.parseInt(ld.getConLen())) {
					minSize = Integer.parseInt(ld.getConLen());
				}
				//최대사이즈
				if (maxSize<Integer.parseInt(ld.getConLen())) {
					maxSize = Integer.parseInt(ld.getConLen());
				}
				//1-7
				//마지막 데이터 이거나 현재 data의 시간과 다음 data의 시간이 다를 시 실행
				a :if (i==data.size()-1||!sdf2.parse(ld.getStart()).equals(sdf2.parse(data.get(i+1).getStart()))) {
					//1-7-1
					dataSb.append(startDate+", ");
					dataSb.append(totalProcess+", ");
					dataSb.append(averageTime/totalProcess+", ");
					dataSb.append(minTime+", ");
					dataSb.append(maxTime+", ");
					dataSb.append(averageSize/totalProcess+", ");
					dataSb.append(minSize+", ");
					dataSb.append(maxSize);
					//1-7-2
					if (i!=data.size()-1) {
						dataSb.append("\r\n");
					}else {
						break a;
					}
					//1-7-3
					//시간이 변하니 다음데이터의 시간으로 바꿔준 뒤 나머지 초기화
					startDate = data.get(i+1).getStart().substring(0, data.get(i+1).getStart().length()-3);
					averageTime = 0;
					minTime=sdf.parse(data.get(i+1).getEnd()).getTime()-sdf.parse(data.get(i+1).getStart()).getTime();
					maxTime=0;
					averageSize=0;
					minSize=Integer.parseInt(data.get(i+1).getConLen());
					maxSize=0;
					totalProcess = 0;
					
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			//1-8
			averageSb.append(ld.getStart()+", ");	
			averageSb.append(ld.getEnd()+", ");		
			averageSb.append(ld.getEsbId()+", ");	
			averageSb.append(ld.getConLen()+", ");	
			averageSb.append(ld.getCallTime()+", ");
			averageSb.append(ld.getBefM()+", ");	
			averageSb.append(ld.getNowM()+", ");	
			averageSb.append(ld.getInvoGal()+", ");
			averageSb.append(ld.getUnM());
			
			if (i!=data.size()-1) {
				averageSb.append("\r\n");
			}
		}
		//1-9	
		return new StringBuffer[]{dataSb, averageSb};
	}

	/**
	 * 메인 : 실행 후 시간 및 메모리 체크
	 * @param args
	 */
	public static void main(String[] args) {
		System.gc();
	   
	   	long startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory(), endMemory = 0;
	      
		long start = System.currentTimeMillis();
		
		LogAnalysis la = new LogAnalysis();
		
	    la.outLog();

		long end = System.currentTimeMillis();
		
		System.gc();
		
	    endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	    
	    long useMemory = (startMemory-endMemory);

		//실행시간 측정
		System.out.println( "실행 시간 : " + ( end - start ) + "ms" );
		
		System.out.println("Used Memory:"+useMemory/1024+"Kbytes");
     
   }

}