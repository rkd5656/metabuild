package kbc.algorithm.sort;

import java.util.Arrays;

public class SelectionSort {
	
	public int[] selectionSort(int[] numbers) {
			int idx = 0; 
			int tempNum = 0;

			for (int i = 1; i < numbers.length; i++) {
				idx = i;
				tempNum =0;

				for (int j = i; j < numbers.length; j++) {
					if (numbers[j]<numbers[idx]) {
						idx = j;
					}
				}

				if(numbers[i-1]>numbers[idx]) {
					tempNum = numbers[idx];
					numbers[idx] = numbers[i-1];
					numbers[i-1]=tempNum;
				}
			}

		return numbers;
	}
	
	
	public static void main(String[] args) {
		SelectionSort ss = new SelectionSort();
		int[] numbers = {9,7,5,3,2,3,6,12,34,67,54,4,3,2,1};
		int[] result = ss.selectionSort(numbers);
		System.out.println(Arrays.toString(result));
		
	}
}
