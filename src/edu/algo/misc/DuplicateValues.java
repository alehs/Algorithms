package edu.algo.misc;

import java.util.HashMap;

public class DuplicateValues {

	public static void main(String[] args) {
		int[] arr = {9, 2, 3, 1, 5, 6, 7, 8, 4, 1};
		//int[] arr = {1,2,2,1};
		System.out.println(findDuplicateHashMap(arr));
		System.out.println(findDuplicateReversedBinarySearch(arr));
		System.out.println(findDuplicateInPlaceHash(arr));
	}

	private static int findDuplicateReversedBinarySearch(int[] arr) {
		int low = 1;
		int high = arr.length;
		int duplicate = -1;

		while (low <= high) {
			int middle = low + (high - low)/2;
			int count = countNumbers(arr, middle);
			if (count > middle) {
				duplicate = middle;
				high = middle - 1;
			} else {
				low = middle + 1;
			}
		}
		return duplicate;
	}

	private static int countNumbers(int[] arr, int middle) {
		int count = 0;
		for(int i : arr) {
			if (i <= middle) {
				count ++;
			}
		}
		return count;
	}

	private static int findDuplicateInPlaceHash(int[] arr) {
		for (var i = 0; i<arr.length; i++) {
			int index = Math.abs(arr[i]);
			if (arr[index] < 0) {
				return index;
			} else {
				arr[index] = -arr[index];
			}
		}
		return -1;
	}

	private static int findDuplicateHashMap(int[] arr) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i : arr) {
			if (map.containsKey(i)) {
				return i;
			} else {
				map.put(i, 1);
			}
		}
		return -1;
	}
}
