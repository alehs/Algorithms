package edu.algo.sorting;

import java.util.Arrays;

public class InsertionSort {

	public static void main(String[] args) {
		int [] arr = new int[] { 7,6,5,4,3,2,1};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static void sort(int[] arr) {
		int low = 0;
		while (low < arr.length - 1) {
			int curr = low + 1;
			while (curr > 0 && arr[curr] < arr[curr - 1]) {
				swap(arr, curr, curr - 1);
				curr--;
			}
			low++;
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
