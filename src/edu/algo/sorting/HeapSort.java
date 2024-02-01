package edu.algo.sorting;

import java.util.Arrays;

import static edu.algo.ArrayUtils.swap;

/**
 * Binary Heap sort algorithm
 *
 * 1. Build the Heap using bottom-up method
 * 2. Remove all maximums from heap
 */
public class HeapSort {

	public static void main(String[] args) {
		String[] arr = {"C", "D", "E", "F", "A", "B"};
		HeapSort.sort(arr);
		System.out.println("sorted array: " + Arrays.toString(arr));
	}

	public static <Key extends Comparable> void sort(Key[] arr) {
		int N = arr.length;
		for (int i = N/2; i >= 0; i--) {
			sink(arr, i, N);
		}
		int last = N;
		while (last > 0) {
			swap(arr, 0, --last);
			sink(arr, 0, last);
		}
	}

	private static void sink(Comparable[] arr, int k, int size) {
		while((k * 2 + 1) <= size - 1) {
			int ch = biggestChild(arr, k, size);
			if (less(arr, k, ch)) swap(arr, ch, k);
			k = ch;
		}
	}

	private static int biggestChild(Comparable[] arr, int k, int size) {
		// 0, 1, 2, 3, 4, 5
		// 1
		//    2  3
		//          4  5  6
		int ch = k * 2 + 1;
		if (ch < size - 1 && less(arr, ch, ch+1)) ch++;
		return ch;
	}

	private static boolean less(Comparable[] arr, int a, int b) {
		return arr[a].compareTo(arr[b]) < 0;
	}

}
