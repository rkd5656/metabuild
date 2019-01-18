package kbc.algorithm.sort;

import java.util.Arrays;

public class InsertionSort {
	
	public int[] insertionSort(int[] numbers) {
		 
		for (int i = 1; i < numbers.length; i++) {
			int	idx = i;
			int insertionNum=numbers[idx];
			
			while(true) {
				if(idx==0) {
					numbers[idx]=insertionNum;
					break;
				}else if(numbers[idx]>=numbers[idx-1]) {
					numbers[idx]=insertionNum;
					break;
				} else if(numbers[idx]<numbers[idx-1]){
					numbers[idx] = numbers[idx-1];
					numbers[idx-1] = insertionNum;
				}
				idx--;
			}
		}
		return numbers;
				
	}
	
	public static void main(String[] args) {
		InsertionSort is = new InsertionSort();
		int[] numbers= {1,5,7,6,1,6,9,5,8,4,1,5,1,3,5,9,8,8,7,5,4,1,5956,123,546,12};
		
		int[] result = is.insertionSort(numbers);
		
		System.out.println(Arrays.toString(result));
	}
	
}
