package edu.algo.misc;

import java.util.Arrays;


/**
 * Sort array in the way positive numbers go firs and negative next
 */
public class CustomizedSort {

	public static void main(String[] args) {
		int [] arr = {-1,2,-2,-4,5,3,0,1};
		Integer[] boxed = Arrays.stream(arr).boxed().toArray(Integer[]::new);
		Arrays.sort(boxed, (a, b) -> {
			if (a<0 && b>=0) return 1;
			if (a>=0 && b<0) return -1;
			if (a<0 && b<0) return -(a.compareTo(b));
			return (a).compareTo(b);
		});
		System.out.println(Arrays.toString(boxed));
	}
}
