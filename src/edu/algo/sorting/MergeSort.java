package edu.algo.sorting;

import java.util.Arrays;

/**
 * Recursively apply:
 *    - split array into 2 parts;
 *    - sort both parts;
 *    - merge both parts;
 */
public class MergeSort {

	public static void main(String[] args) {
		int [] arr = new int[] { 7,6,5,4,3,2,1};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	private static void sort(int[] arr) {
		int aux[] = Arrays.copyOf(arr, arr.length);
		sort(aux, arr, 0, arr.length - 1);
	}

	private static void sort(int[] aux, int[] arr, int low, int high) {
		if (high <= low) return;
		int mid = low + (high - low)/2;
		sort(aux, arr, low, mid);
		sort(aux, arr, mid + 1, high);
		merge(aux, arr, low, high, mid);
	}

	static void merge(int[] fromArr, int[] toArr, int low, int high, int mid) {
		int l = low;
		int h = mid + 1;
		int k = low;

		for (int i = low; i <= high; i++) {
			fromArr[i] = toArr[i];
		}

		while (l <= mid || h <= high) {
			if (h > high || fromArr[l] < fromArr[h]) {
				toArr[k++] = fromArr[l++];
			} else if (l > mid || fromArr[l] > fromArr[h]) {
				toArr[k++] = fromArr[h++];
			}
		}
	}

	private static void merge2(int[] fromArr, int[] toArr, int low, int high, int mid) {
		int l = low;
		int h = mid + 1;

		for (int i = low; i <= high; i++) {
			fromArr[i] = toArr[i];
		}

		for (int k = low; k <= high; k++) {
			if (l > mid) 						toArr[k] = fromArr[h++];
			else if (h > high)					toArr[k] = fromArr[l++];
			else if (fromArr[l] < fromArr[h])	toArr[k] = fromArr[l++];
			else 								toArr[k] = fromArr[h++];
		}
	}


}
