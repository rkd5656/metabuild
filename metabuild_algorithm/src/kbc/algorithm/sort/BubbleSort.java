package kbc.algorithm.sort;

import java.util.Arrays;

public class BubbleSort {
	public int[] bubbleSort(int[] numbers) {
		
		int tempNo = 0;
		
		for (int i = 0; i < numbers.length; i++) {
			int idx = 0;
			while (idx<numbers.length-1) {
				if (numbers[idx]>numbers[idx+1]) {
					tempNo = numbers[idx+1];
					numbers[idx+1] = numbers[idx];
					numbers[idx] = tempNo;
				}
				idx++;
			}
		}
		
		return numbers;
	}
	
	public static void main(String[] args) {
		BubbleSort bs = new BubbleSort();
		int[] numbers = {1,4,2,3,5,10,9,6,7,100,76,54,23,200,12,15,37};
		int[] result = bs.bubbleSort(numbers);
		System.out.println(Arrays.toString(result));
	}
}
