package edu.algo.sorting;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MergeSortTest {

	@Test
	public void testMergeShort() {
		int arr[] = {2,3,1};
		int aux[] = Arrays.copyOf(arr, arr.length);
		MergeSort.merge(aux, arr, 0,2, 1);
		Assertions.assertEquals(1, arr[0]);
		Assertions.assertEquals(2, arr[1]);
		Assertions.assertEquals(3, arr[2]);
	}
}
