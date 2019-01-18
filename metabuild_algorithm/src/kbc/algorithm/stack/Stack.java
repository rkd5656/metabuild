package kbc.algorithm.stack;

public class Stack {
	
	
	private Object[] dataList = null;
	private int size =0;
	
	//데이터 넣기
	public void push(Object data) {
		Object[] temp = new Object[size+1];
		
		if(size>0) {
			System.arraycopy(dataList,0,temp,0, dataList.length);
		}
		temp[size] = data;
		
		dataList = temp;		
		
		size++;
	}
	
	//마지막으로 들어간 데이터 빼기
	public void pop() {
	
		if(size==0) {
			System.out.println("데이터가 존재하지 않음");
		}else {
			Object[] temp = new Object[size-1];
			
			System.arraycopy(dataList,0,temp,0, temp.length);
			
			dataList = temp;
			
			size--;
		}
		
	}
	
	//마지막 데이터 출력
	public String peek() {
		String result;
		
		if(size==0) {
			result = "데이터가 존재하지 않음";
		}else {
			result = String.valueOf(dataList[size-1]);
		}
		
		return result;
	}
	
	//메인
	public static void main(String[] args) {
		Stack stack = new Stack();
		stack.push("배고프다");
		System.out.println(stack.peek());
		stack.push("와우");
		System.out.println(stack.peek());
		stack.push("호오?");
		System.out.println(stack.peek());
		stack.pop();
		System.out.println(stack.peek());
		stack.pop();
		System.out.println(stack.peek());
		stack.pop();
		System.out.println(stack.peek());
		stack.pop();
		System.out.println(stack.peek());
	}
	
	
}
