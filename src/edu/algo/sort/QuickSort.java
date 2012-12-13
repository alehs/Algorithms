package edu.algo.sort;
import java.util.Arrays;

import edu.algo.Loader;

public class QuickSort {

	public static void main(String[] args) {
		int[] arr = Loader.loadArr("QuickSort.txt");
		System.out.println(arr.length);

		long num = sort(arr.clone(), 0, arr.length - 1, PivotChooser.FIRST);
		System.out.println("using first element as pivot: " + num);
		
		num = sort(arr.clone(), 0, arr.length - 1, PivotChooser.LAST);
		System.out.println("using last element as pivot: " + num);

		num = sort(arr.clone(), 0, arr.length - 1, PivotChooser.MEDIAN);
		System.out.println("using median as pivot: " + num);

		num = sort(arr, 0, arr.length - 1, PivotChooser.RANDOM);
		System.out.println("using random pivot: " + num);

		System.out.println(Arrays.toString(arr));
	}

	/**
	 * Recursively sort the given sub-array using the PivotChooser
	 * @return number of comparisons for the routine. 
	 */
	public static long sort(int[] arr, int left, int right, PivotChooser pivChooser) {
		if (left >= right) return 0L;

		int p = pivChooser.choose(arr, left, right);
		long total = right - left;

		p = partitionAroundPivot(arr, left, right, p);
		if (p > 0) 
			total += sort(arr, left, p - 1, pivChooser);
		if (p < arr.length - 1) 
			total += sort(arr, p + 1, right, pivChooser);

		return total;
	}

	/**
	 * Partition the array around the given pivot.
	 * @return pivot new position 
	 */
	public static int partitionAroundPivot(int[] arr, int left, int right, int p) {
		int piv = arr[p];
		swap(arr, left, p);

		int i = left + 1;

		for (int j = left + 1; j <= right; j++) {
			if (arr[j] < piv) {
				swap(arr, j, i);
				i++;
			}
		}

		swap(arr, left, i-1);
		return i-1;
	}

	private static void swap(int[] arr, int first, int second) {
		int tmp = arr[first];
		arr[first] = arr[second];
		arr[second] = tmp;
	}

}
