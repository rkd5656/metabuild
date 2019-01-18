package kbc.algorithm.queue;


public class KangMap{
	
	
	private int size = 0; 
	private Node[] obj = {}; 
	
	private class Node{
		int idx;
		Object data;
		
		public Node(int idx, Object data) {
			this.idx = idx;
			this.data = data;
		}

		@Override
		public String toString() {
			return String.valueOf(data);
		}
		
		
	}
	
	public void put(int idx, Object data) {
		size++;
		Node[] temp = new Node[size];
		
		Node put = new Node(idx, data);
		
		if (obj==null) {
			temp[0] = put;
			obj = temp;
		}else {
			System.arraycopy(obj, 0, temp, 0, obj.length);
			temp[size-1] = put;
			obj = temp;
		}
	}
	
	public Node findNode(int idx) {
		
		Node n= new Node(0,null);
		
		if (obj!=null){
			for (int i = 0; i < obj.length; i++) {
				if(obj[i].idx == idx) {
					n=obj[i];
				}
			}
		}
		
		return n;
	}
	
	public String get(int idx) {
		String node = findNode(idx).toString();
		if (node.equals("null")){
			return null;
		}else {
			String result = findNode(idx).toString();
			return result;
		}
		
	}
	
	public void remove(int idx) {
		for (int i = 0; i < obj.length; i++) {
			if(i==size-1) {
				obj[i] = null;
			}else if(obj[i].idx == idx) {
				obj[i] = obj[size-1];
			}
		}
		size--; 
		Node[] temp = new Node[size];
		System.arraycopy(obj, 0, temp, 0, temp.length);
		obj = temp;
	}
	
	public int size() {
		return size;
	}
	
}