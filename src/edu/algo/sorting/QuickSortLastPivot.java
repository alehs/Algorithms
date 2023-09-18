package edu.algo.sorting;

import edu.algo.Loader;

/*
 * Optimized version of QuickSort which always takes last element as a pivot
 */
public class QuickSortLastPivot {

	public static void main(String[] args) {
		int[] arr = Loader.loadArr("data/array/QuickSort.txt");
		System.out.println(arr.length);

		sort(arr, 0, arr.length - 1);

		System.out.println("Sorted array: " + arr);
	}

	private static void sort(int[] arr, int from, int to) {
		int piv = arr[to];
		int i = from - 1;
		//for (int j = i; i<= to)
	}
}
