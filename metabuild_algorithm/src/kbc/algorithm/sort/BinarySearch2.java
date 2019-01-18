package kbc.algorithm.sort;

public class BinarySearch2 {
	public String binarySearch(int[] numbers, int findNum) {
		// 최소 인덱스
		int min = 0;
		// 최대 인덱스
		int max = numbers.length-1; 
		// 중간 인덱스
		int mid = numbers.length/2-1+(numbers.length%2);
		// 마지막 결과
		int result;
		int resultIdx;
		
		while(true) {
			System.out.println("중간 값 : "+numbers[mid]);
			if(numbers[mid]>findNum) {
				max = mid;
				mid = max/2-1+(max%2);
			}else if(numbers[mid]<findNum) {
				min = mid;
				mid = (min+max)/2+(min+max)%2;
			}else if(numbers[mid]==findNum){
				result = numbers[mid];
				resultIdx = mid;
				break;
			}
		}
		
		return "인덱스 : "+resultIdx+" / 값 : "+result;
	}
	
	public static void main(String[] args) {
		BinarySearch2 bs = new BinarySearch2();
		int[] numbers= {2,4,6,8,10,26,28,30,32,35,38,40,47};
		int findNum= 40;
		String result = bs.binarySearch(numbers, findNum);
		
		System.out.println(result);
	}
}
