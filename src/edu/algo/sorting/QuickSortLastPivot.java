package edu.algo.sorting;

import edu.algo.ArrayUtils;
import edu.algo.Loader;

import java.util.Arrays;

/*
 * Optimized version of QuickSort which always takes last element as a pivot
 */
public class QuickSortLastPivot {

	public static void main(String[] args) {
		int[] arr = Loader.loadArr("data/array/QuickSort.txt");
		//int[] arr = new int[] { 7,6,1,4,3,2,5};

		sort(arr, 0, arr.length-1);

		System.out.println("Sorted array: " + Arrays.toString(arr));
	}

	private static void sort(int[] arr, int from, int to) {
		if (from > to) return;
		int pivot = partition(arr, from, to);
		if (pivot < from || pivot > to) return;
		sort(arr, from, pivot - 1);
		sort(arr, pivot + 1, to);
	}

	private static int partition(int[] arr, int from, int to) {
		int piv = arr[to];
		int low = from - 1;
		for (int j = from; j < to; j++) {
			if (arr[j] < piv) {
				low ++;
				ArrayUtils.swap(arr, low, j);
			}
		}
		low++;
		ArrayUtils.swap(arr, low, to);
		return low;
	}
}
