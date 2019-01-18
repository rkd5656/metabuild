package kbc.algorithm.sort;

public class BinarySearch1 {

	public String binarySearch(int[] numbers, int findNum) {
		// 담는 배열
		int[] temp = numbers;
		// 복사할 배열
		int[] temp2; 
		// 인덱스
		int idx = numbers.length/2-1+(numbers.length%2);
		// 마지막 결과
		int result = 0;
		
		while(true) {
			System.out.println(temp[idx]);
			if(findNum < temp[idx]) {
				temp2 = new int[idx];
				System.arraycopy(temp, 0, temp2, 0, idx);
				temp = temp2;
				idx = temp.length/2-1+(temp.length%2);
			}else if(findNum > temp[idx]) {
				temp2 = new int[temp.length-idx-1];
				System.arraycopy(temp, idx+1, temp2, 0, temp2.length);
				temp = temp2;
				idx = temp.length/2-1+(temp.length%2);
				
			}else if(findNum == temp[idx]){
				result = temp[idx];
				break;
			}
		}
		return "찾은 수 :"+result;
	};
	
	
	public static void main(String[] args) {
		BinarySearch1 bs = new BinarySearch1();
		int[] numbers= {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,15};
		int findNum= 14;
		String result = bs.binarySearch(numbers, findNum);
		
		System.out.println(result);
		
		
	}
	
	
}
