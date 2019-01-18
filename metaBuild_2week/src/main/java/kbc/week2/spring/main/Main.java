package kbc.week2.spring.main;


import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;



public class Main {

	public static void main(String[] args) {
		//Xml 읽고 컨테이너 생성 후 빈 가져오기 : 생성
		GenericApplicationContext context = new GenericApplicationContext();
		
		// 설정
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
		reader.loadBeanDefinitions("kbc/week2/spring/xml/applictionContext.xml");
		
		//초기화
		context.refresh();		
		
		//종료 : destroy
		context.close();
	}

}
