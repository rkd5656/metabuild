package kbc.algorithm.queue;

import java.util.Scanner;


public class Queue {
	int front = 0;
	int rear = 0;
	KangMap map = new KangMap();
	
	//데이터 넣기
	public void enqueue(String text, int length) {
		if(front == 0 || length == 0) {
			front++;
		}
		rear++;
		map.put(rear, text);
	}
	
	//첫번째 인덱스 데이터 빼기
	public void dequeue() {
		map.remove(front);
		front++;
		if(front > rear) {
			front--;
		}
	}
	
	//첫번째 데이터 확인 및 빼기
	public void peek(int num) {
		String peek = map.get(front);
		if(peek==null) {
			System.out.println("대기자 없음");
			System.out.println("--------------------------------------------");
		}else {
			System.out.println(num+"번 창구 : "+front+"번 손님 "+peek);
			System.out.println("--------------------------------------------");
			dequeue();			
		}
	}
	
	//처리 (은행으로 구현)
	public void bank() {
		a : while(true) {
			int length = map.size();
			System.out.println("대기 손님: " +length);
			
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			
			System.out.println("고르세요 1.번호뽑기 / 2.처리 / 3.종료");
			String choice = scan.nextLine();
			System.out.println("--------------------------------------------");
			
			switch (choice) {
			case "1" :
				System.out.println("원하는 서비스를 입력하세요 ex)출금,입금,대출");
				String text = scan.nextLine();
				System.out.println("--------------------------------------------");
				enqueue(text, length);
				break;

			case "2" :
				System.out.println("창구번호를 입력");
				int num=scan.nextInt();
				System.out.println("--------------------------------------------");
				peek(num);
				
				break;
				
			case "3" :
				break a;
			}
		}
	}
	
	public static void main(String[] args) {
		Queue q = new Queue();
		q.bank();
	
		
		
	}
}
