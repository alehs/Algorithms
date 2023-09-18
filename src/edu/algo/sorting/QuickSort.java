package edu.algo.sorting;
import java.util.Arrays;

import edu.algo.Loader;

import static edu.algo.ArrayUtils.swap;

public class QuickSort {

	public static void main(String[] args) {
		int[] arr = Loader.loadArr("data/array/QuickSort.txt");
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
	public static long sort(int[] arr, int from, int to, PivotChooser pivChooser) {
		if (from >= to) return 0L;

		int p = pivChooser.choose(arr, from, to);
		long total = to - from;

		p = partitionAroundPivot(arr, from, to, p);
		if (p > 0) 
			total += sort(arr, from, p - 1, pivChooser);
		if (p < arr.length - 1) 
			total += sort(arr, p + 1, to, pivChooser);

		return total;
	}

	/**
	 * Partition the array around the given pivot.
	 *
	 * 	- move pivot to the beginning of the array
	 * 	- move all elements smaller to the pivot to the left
	 * 	- put pivot to its correct position
	 * 	- return new pivot position
	 * @return pivot new position 
	 */
	public static int partitionAroundPivot(int[] arr, int from, int to, int p) {
		int piv = arr[p];
		swap(arr, from, p);

		int curr = from + 1;

		for (int i = curr; i <= to; i++) {
			if (arr[i] < piv) {
				swap(arr, i, curr);
				curr++;
			}
		}

		// return pivot to its correct position
		swap(arr, from, curr-1);
		return curr-1;
	}

}
