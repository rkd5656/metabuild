package com.meta.kbc;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLParsing {
	
	
	
	/**
	 * field 객체
	 * XML파일 불러와서 document에 넣어주는 객체
	 * 
	 * DocumentBuilderFactory, DocumentBuilder, Document
	 */
	private DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	private DocumentBuilder dBuilder = null;
	private Document doc = null;
	
	/**
	 * FILE_ID를 불러오고 배열에 담아 준 후 return해 주는 기능
	 * 
	 * 1. T_BASEFILE_TB.xml 파일을 불러옴
	 * 2. Document 객체에 담아줌
	 * 3. Document 객체에서 FILE_ID의 NodeList를 불러옴
	 * 4. getFileIdList 함수를 이용해 fileId[] 배열로 변경.
	 * 5. fileId[]를 다시 return
	 * 
	 * @return String[] fileId
	 */
	private String[] getFileId() {
		//fileId 배열
		String[] fileId = {};
		//base파일 url
		//1
		String url ="D:\\WorkingSpace\\xml\\xmlBase\\T_BASEFILE_TB.xml";
		
		try {
			//2
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(url);
			
			doc.getDocumentElement().normalize();
			
			//3
			//fileID nodeList
			NodeList fileidList = doc.getElementsByTagName("FILE_ID");
			
			//4
			fileId = getFileIdList(fileidList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//5
		return fileId;
	}
	
	
	/**
	 * FILE_ID를 받아 배열로 만들어준 후 return해주는 기능
	 * 
	 * 1. getFileId()에서 T_BASEFILE_TB.xml의 FILE_ID NodeList를 받음.
	 * 2. String 배열에 for문을 통해 넣어줌
	 * 3. 그 배열을 return해주는 기능
	 * 
	 * @param NodeList fileidList : Base파일 FILE_ID들
	 * @return String[] a
	 */
	private String[] getFileIdList(NodeList fileidList) {
		//1
		String[] a = new String[fileidList.getLength()];
		//2
		for (int i = 0; i < a.length; i++) {
			a[i] = fileidList.item(i).getTextContent();
		}
		//3
		return a;
	}
	
	/**
	 * FILE_ID의 번호에 맞춰 F파일을 가져와 조건 판단 후 T파일을 생성해주는 기능
	 * 
	 * 1. getFileId()을 실행해 FILE_ID배열을 가져옴
	 * 2. fileId길이 만큼 for문을 돌림
	 *  2-1. for문은 fileId의 번호를 통해 F파일을 불러 옴
	 *  2-2. F파일의 Row들을 NodeList(rowList)를 담아주고 최초로 한번 만 METADATA의 COL을 NodeList(meta)에 담아줌
	 *  2-3. F파일의 idx번째 NodeList가 null이 아닐 시 getComment(rowList, fileId[idx])을 실행 시켜 List객체를 가져옴
	 *  2-4. 가져온 list 파일을 setXmlT(list, fileId[idx], meta)을 실행시켜 idx번째 fileId의 T XML 파일 생성
	 */
	private void getSetXml() {
		//1.
		String[] fileId = getFileId();
		
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			
			// F파일 METADATA 정보들
			NodeList meta = null;
			
			//2.
			for (int idx = 0; idx < fileId.length; idx++) {
				
				String url = "D:\\WorkingSpace\\xml\\xmlF\\F_" + fileId[idx] + "_TB.xml";
				//2-1
				doc = dBuilder.parse(url);

				doc.getDocumentElement().normalize();
				
				//2-2 F파일 ROW
				NodeList fRowList = doc.getElementsByTagName("ROW");
				//2-2 최초 한번 meta정보를 담아줌
				if (idx==0) {
					meta = doc.getElementsByTagName("COL");
				}
				//2-3
				if (fRowList != null) {
					// F파일 ROW 및 comment list
					List<Object[]> list = getComment(fRowList, Integer.parseInt(fileId[idx]));
					//2-4
					outXmlT(list, fileId[idx], meta);
					list = null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}			
	
	/**
	 * F파일의 idx번째 Row들을 받아 SIMILAR_LATE 연산 조건 판단 후
	 * P파일의 P_ID와 비교해 LICENSE_ID를 가져오고 해당 F파일 Row와 같이 return해주는 기능 
	 * 
	 * 1. comment 및 해당 F파일 Row를 담아줄 List 객체(comList) 선언
	 * 2. F파일 idx에 맞는 P파일의 P_ID를 key로 LICENSE_ID String를 담아줄 Map 객체(map) 선언
	 * 3. P_"+idx+"_TB.xml파일을 가져온 후 Document에 담아줌
	 * 4. 정규화 후 Row들을 NodeList(pXMLRow)에 담아 줌
	 * 5. pXMLRow의 길이 만큼 for문을 통해 getTagValue()를 실행하여 Row의 P_ID를 key로한 LICENSE_ID를 map에 담아줌
	 * 6. fRowList의 길이 만큼 for문을 통해 i번째의 SIMILAR_RATE를 가져옴
	 *  6-1. if문을 통한 SIMILAR_RATE/100>15 조건 판단
	 *  6-2. 5-1번의 조건이 만족할 시
	 *   6-2-1 P파일의 P_ID가 존재 할 경우 map에서 해당 P_ID의 LICENSE_ID와 해당 Row를 comList에 담아주고,
	 *   6-2-2 P파일의 P_ID가 존재 하지 않을 경우 해당 Row만 담아줌.
	 *  6-3. 5-1번의 조건이 만족 못할 시 해당 Row만 담아줌 comList에 담아줌
	 * 7. comList return
	 * 
	 * @param fRowList : F파일 Row들
	 * @param idx : F파일 index
	 * @return comList : comment 및 해당 F파일 Row를 담은 List<Object[]> 객체
	 */
	private List<Object[]> getComment(NodeList fRowList, int idx) {
		//1
		List<Object[]> comList = new ArrayList<Object[]>();
		//p파일 경로
		String url ="D:\\WorkingSpace\\xml\\xmlP\\P_"+idx+"_TB.xml";
		//2
		Map<String, String> map = new HashMap<String, String>();
		
		try {
			//3
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(url);
			
			doc.getDocumentElement().normalize();
			//4
			NodeList pXMLRow = doc.getElementsByTagName("ROW");
			//5
			for (int i = 0; i < pXMLRow.getLength(); i++) {
				map.put(getTagValue("P_ID", (Element)pXMLRow.item(i)),
						getTagValue("LICENSE_ID", (Element)pXMLRow.item(i))) ;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//6
		for (int i = 0; i < fRowList.getLength(); i++) {
			
			Element row= (Element) fRowList.item(i);
			
			String sim = getTagValue("SIMILAR_RATE", row);
			//6-1
			if(Integer.parseInt(sim)/100>15) {
				//6-2
				//6-2-1
				if (!getTagValue("P_ID", row).equals("")) {
					comList.add(new Object[] { row, map.get(getTagValue("P_ID", row)) });
				} else {//6-2-2
					comList.add(new Object[] { row });
				}
			}else {
				//6-3
				comList.add(new Object[] { row });
			}
		}
		//배열로 return
		//7
		return comList;
	}
	
	//ROW와 META정보를 받은 후 XML 생성
	/**
	 * F파일의 Row와 P파일의 LICENSE_ID로 T파일 생성하는 기능
	 * 
	 * 1. meta에 들어있는 METADATA정보들을 추출해 String[] colArr에 담아줌
	 * 2. Document 객체 새로 생성후 XML생성
	 *  2-1 depth를 TABLE -> METADATA	-> NAME,COL
	 *  					 ROWS 		-> ROW -> F파일 한 ROW의 내용
	 * 3. Document객체를 Transformer로 XML파일 형식으로 변환 후 출력 
	 * 
	 * @param list : F파일 Row와 해당 idx의 P파일 Comment가 담겨있는 List
	 * @param idx : 해당 파일의 번호
	 * @param meta : METADATA 정보
	 */
	private void outXmlT(List<Object[]> list,String idx, NodeList meta) {
		//outPut경로
		String url ="D:\\WorkingSpace\\xml\\xmlT2\\T_"+idx+"_TB.xml";
				
		//1
		String[] colArr = new String[meta.getLength()];
		//1
		for (int i = 0; i < colArr.length; i++) {
			colArr[i] = meta.item(i).getTextContent();
		}
		
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.newDocument();
			//2,2-1
			//TABLE
			Element rootElement = doc.createElement("TABLE");
			doc.appendChild(rootElement);
			
			//<METADATA>
			Element metaData = doc.createElement("METADATA");
			rootElement.appendChild(metaData);
			
			//<NAME>F_[idx]_TB</NAME>
			Element name = doc.createElement("NAME");
			name.appendChild(doc.createTextNode("T_"+idx+"_TB"));
			metaData.appendChild(name);
			
			//<COL>만들기
			for (int i = 0; i < colArr.length; i++) {
				Element col = doc.createElement("COL");
				col.appendChild(doc.createTextNode(colArr[i]));
				metaData.appendChild(col);
			}
				
			//<ROWS>
			Element rows = doc.createElement("ROWS");
			rootElement.appendChild(rows);
			
			//<ROW>
			for (int i = 0; i < list.size(); i++) {
				Element row = doc.createElement("ROW");
				
				for (int j = 0; j < colArr.length-1; j++) {
					Element col = doc.createElement(colArr[j]);
					col.appendChild(doc.createTextNode(getTagValue(colArr[j], (Element)list.get(i)[0])));
					row.appendChild(col);
				}
				
				Element comment = doc.createElement(colArr[colArr.length-1]);
				
				//<COMMENT>
				if (list.get(i).length>1) {
					comment.appendChild(doc.createTextNode((String) list.get(i)[1]));
				}
				
				row.appendChild(comment);
				rows.appendChild(row);
			}
			
			//XML파일로 변환해주는 객체
			//3
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new FileOutputStream(new File(url)));
			
			transformer.transform(source, result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	


	

	/**
	 * 원하는 Node또는 Element객체 안의 원하는 태그 값을 String으로 return하는 기능
	 * 값을 추출할 때 언제든 사용 가능
	 * 
	 * 1. 원하는 태그의 Element에서 String을 가져옴
	 * 2. String을 return
	 * @param sTag : 태그 이름
	 * @param element : 원하는 태그 이름의 값을 빼오려는 Element
	 * @return String : 원하는 태그의 Text 값 return
	 */
	private String getTagValue(String sTag, Element element) {
		
	    try{
	    	//1
	        String result = element.getElementsByTagName(sTag).item(0).getTextContent();
	        
	        //2
	        return result;
	        
	    } catch(NullPointerException e){
	    	e.printStackTrace();
	        return "";
	    } catch(Exception e){
	    	e.printStackTrace();
	        return "";
	    }
	}

	/**
	 * main 메소드
	 *  
	 * @param args
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		long startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory(), endMemory = 0;
				
		XMLParsing dd = new XMLParsing();
		dd.getSetXml();

	    endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	    
	    long end = System.currentTimeMillis();
	    
	    long useMemory = (endMemory-startMemory)/1024;
	    
	    DecimalFormat df = new DecimalFormat("#,###");
	    
	    String memory = df.format(useMemory);

		//실행시간 측정
		System.out.println( "실행 시간 : " + ( end - start )/1000.0 + "초" );
		System.out.println("메모리 사용량:"+memory+"kbytes");
		
	}
}
