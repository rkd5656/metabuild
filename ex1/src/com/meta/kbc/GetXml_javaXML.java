package com.meta.kbc;

import java.io.File;
import java.io.FileOutputStream;
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

public class GetXml_javaXML {
	
	//XML파일 불러와서 document에 넣어주는 객체
	private DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	private DocumentBuilder dBuilder = null;
	private Document doc = null;
	
	//base파일에서 fileID 가져옴
	private String[] getFileId() {
		//fileId 배열
		String[] fileId = {};
		//base파일 url
		String url ="D:\\WorkingSpace\\xml\\xmlBase\\T_BASEFILE_TB.xml";
		
		try {
			
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(url);
			
			doc.getDocumentElement().normalize();
			//fileID nodeList
			NodeList fileidList = doc.getElementsByTagName("FILE_ID");
			//파일아이디 가져오는 함수
			fileId = getFileIdList(fileidList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return fileId;
	}
	
	//fileID 가져오기
	private String[] getFileIdList(NodeList fileidList) {
		
		String[] a = new String[fileidList.getLength()];
		
		for (int i = 0; i < a.length; i++) {
			a[i] = fileidList.item(i).getTextContent();
		}
		
		return a;
	}
	
	//xmlF에서 row를 가져오고를 통해 조건이 맞다면 list를 가져오고 파일 생성
	public void getXmlF() {
		
		String[] fileId = getFileId();
		
		for (int idx = 0; idx < fileId.length; idx++) {
			String url ="D:\\WorkingSpace\\xml\\xmlF\\F_"+fileId[idx]+"_TB.xml";
			try {
				dBuilder = dbFactory.newDocumentBuilder();
				
				doc = dBuilder.parse(url);
				
				doc.getDocumentElement().normalize();
				//F파일 ROW
				NodeList rowList = doc.getElementsByTagName("ROW");
				//F파일 COL
				NodeList meta = doc.getElementsByTagName("COL"); 
							
				if (rowList!= null) {
					//F파일 ROW 및 comment list
					List<Object[]> list = getSim(rowList, Integer.parseInt(fileId[idx]));

					setXmlT(list, fileId[idx], meta);
					list = null;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}				
	}
	
	//sim값 가져온 후 if문에서 조건 판단, 조건이 맞다면 값 넣어주고 배열에 담기
	private List<Object[]> getSim(NodeList rowList, int idx) {
		
		List<Object[]> rowL = new ArrayList<Object[]>();
		//p파일 경로
		String url ="D:\\WorkingSpace\\xml\\xmlP\\P_"+idx+"_TB.xml";

		Map<String, String> map = new HashMap<String, String>();
		NodeList rowList1 =null;
		try {
			
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(url);
			
			doc.getDocumentElement().normalize();
			
			rowList1 = doc.getElementsByTagName("ROW");
			//P [i] 파일의 P_ID(key)의 LICENSE_ID를 Map에 담아줌(VALUE)
			for (int i = 0; i < rowList1.getLength(); i++) {
				map.put(getTagValue("P_ID", (Element)rowList1.item(i)),
						getTagValue("LICENSE_ID", (Element)rowList1.item(i))) ;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//ROW의 SIMILAR_RATE 조건 연산 후 P_ID와 동일한 F파일의 LICENSE_ID를 가져온 후
		//배열로 return
		for (int i = 0; i < rowList.getLength(); i++) {
			
			Element row= (Element) rowList.item(i);
			
			String sim = getTagValue("SIMILAR_RATE", row);
			
			if(Integer.parseInt(sim)/100>15) {
				if (!getTagValue("P_ID", row).equals("")) {
					rowL.add(new Object[] { row, map.get(getTagValue("P_ID", row)) });
				} else {
					rowL.add(new Object[] { row });
				}
			}else {
				rowL.add(new Object[] { row });
			}
		}
		return rowL;
	}
	
	//ROW와 META정보를 받은 후 XML 생성
	private void setXmlT(List<Object[]> list,String idx, NodeList meta) {
		//outPut경로
		String url ="D:\\WorkingSpace\\xml\\xmlT2\\T_"+idx+"_TB.xml";
				
		//meta정보
		String[] colArr = new String[meta.getLength()];
		
		for (int i = 0; i < colArr.length; i++) {
			colArr[i] = meta.item(i).getTextContent();
		}
		
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.newDocument();
			
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

	


	

	//태그 value
	private String getTagValue(String sTag, Element element) {
		
	    try{
	        String result = element.getElementsByTagName(sTag).item(0).getTextContent();
	        
	        return result;
	        
	    } catch(NullPointerException e){
	    	e.printStackTrace();
	        return "";
	    } catch(Exception e){
	    	e.printStackTrace();
	        return "";
	    }
	}

	//메인
	public static void main(String[] args) {
		long startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory(), endMemory = 0;
		      
		
		long start = System.currentTimeMillis();
		
		GetXml_javaXML dd = new GetXml_javaXML();
		dd.getXmlF();

		long end = System.currentTimeMillis();
		
		
	    endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	    
	    long useMemory = (endMemory-startMemory);

		//실행시간 측정
		System.out.println( "실행 시간 : " + ( end - start )/1000.0 + "초" );
		System.out.println("Used Memory:"+useMemory+"bytes");
		
	}
}
