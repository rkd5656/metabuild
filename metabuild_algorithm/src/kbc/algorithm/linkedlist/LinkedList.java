package kbc.algorithm.linkedlist;

public class LinkedList {
	//첫번째 노드
	private Node head;
	//마지막 노드
	private Node tail;
	//리스트 사이즈
	private int size;
	//노드 클래스 생성
	private class Node{
		
		//노드에 들어갈 데이터
		private Object data;
		
		//노드에 들어갈 다음 노드의 레퍼런스
		private Node next;
		
		//노드 생성시 데이터를 집어 넣고, 다음 노드의 레퍼런스는 Null로 만듬
		public Node(Object data) {
			this.data = data;
			this.next = null;			
		}
		
		//노드 데이터 보기
		@Override
		public String toString() {
			return String.valueOf(data);
		}
	}
	
	//첫번째 노드 추가
	public void firstNode(Object data) {
		Node first = new Node(data);
		
		first.next = head;
		
		head = first;
		
		size++;
		
		if (head.next == null) {
			tail = head;
		}
		
	}
	
	//마지막 노드 추가
	public void lastNode(Object data) {
		Node last = new Node(data);
		
		if(size == 0) {
			firstNode(data);
		}else {
			tail.next = last;
			
			tail = last;
			
			size++;
		}
	}
	
	
	//노드 찾기(노드추가나 삭제 시 사용)
	public Node findNode(int findIdx) {
		Node fn = head;
		
		for (int i = 0; i < findIdx; i++) {
			fn = fn.next;
		}
		
		return fn;
	}
	
	//노드 추가(어느지점이든 가능, 넣으려는 인덱스로 들어가고 원래 있던 노드는 밀려남)
	public void addNode(Object data, int findIdx) {
		
		if(size == 0 || findIdx == 0) {
			firstNode(data);
		}else {
			
			Node add = new Node(data);
			
			Node temp1 = findNode(findIdx-1);
			
			Node temp2 = findNode(findIdx);
			
			temp1.next = add;
			
			add.next = temp2;
			
			size++;
			
			if (add.next == null) {
				tail = add;
			}
		}
		
		
		
	}
	
	//노드 지우기(어느지점이든 가능, 지우려는 인덱스는 지워지고 앞에 노드가 들어옴)
	public void delNode(int findIdx) {
		if (size==0) {
			System.out.println("노드가 존재하지 않음");
		}else if(findIdx == 0){
			firstDelNode();
		}else if(findIdx >= size){
			lastDelNode();
		}else {
			Node temp1 = findNode(findIdx-1);
			Node temp2 = findNode(findIdx+1);
			
			temp1.next = temp2;
			
			size--;
		}
	}
	
	//맨 처음 노드 지우기
	public void firstDelNode(){
		
		if (size==0) {
			System.out.println("노드가 존재하지 않음");
		}else {
			head = head.next;
			size--;
		}
		
	}
	
	//마지막 노드 지우기
	public void lastDelNode() {
		Node temp = findNode(size-2);
		
		if (size==0) {
			System.out.println("노드가 존재하지 않음");
		}else if(size ==1){
			firstDelNode();
		}else {
			temp.next = null;
			
			tail = temp;
			
			size --;
		}
	}
	
	//데이터 값 보기
	public String findData(int findIdx) {
		
		if (findIdx>size) {
			return "인덱스 값이 너무 큽니다.";
		}else {
			Node find = findNode(findIdx);
			return find.toString();
		}
		
	}
	
	//첫번째 노드 데이터 값 보기
	public String head() {
		if (head==null) {
			return "노드가 없음";
		}else {
			return head.toString();
		}
	}
	
	//마지막 노드 데이터 값 보기
	public String tail() {
		if (tail==null) {
			return "노드가 없음";
		}else {
			return tail.toString();
		}
	}
	
	//메인
	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		ll.firstNode("네번째");
		ll.firstNode("세번째");
		ll.firstNode("두번째");
		ll.firstNode("첫번째");
		
	}
	
	
	
	
}
