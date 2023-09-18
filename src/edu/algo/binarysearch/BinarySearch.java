package edu.algo.binarysearch;

public class BinarySearch {

	public static void main(String[] args) {
		int[] input = new int[] {1, 2,3, 4, 5, 6, 7, 8, 10,11,12,13,14,15,16,17,21};
		int target = 21;
		int position = binarySearch_recursive(input, target, 0, input.length);
		System.out.println("Position of " + target + " is :" + position);
	}

	private static int binarySearch_inplace(int[] input, int target, int unused1, int unused2) {
		int start = 0, end = input.length - 1;
		while (start <= end) {
			int middle = start + (end - start)/2;
			if (input[middle] == target) {
				return middle;
			} else if (target < input[middle]) {
				end = middle - 1;
			} else {
				start = middle + 1;
			}
		}
		return -1;
	}

	private static int binarySearch_recursive(int[] input, int target, int start, int end) {
		if (start == end || start > end) {
			return -1;
		}
		int middle = start + (end-start)/2;
		if (input[middle] == target) {
			return middle;
		} else if (target < input[middle]) {
			return binarySearch_recursive(input, target, start, middle - 1);
		} else {
			return binarySearch_recursive(input, target, middle, end + 1);
		}
	}


}
