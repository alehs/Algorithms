package edu.algo.datastructures.heap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class HeapSortTest {

	@Test
	public void testHeapSort() {
		String[] arr = {"C", "D", "E", "F", "A", "B"};
		HeapSort hs = new HeapSort(arr);
		var result = hs.sort();
		assertArrayEquals(new String[] {"A", "B", "C", "D", "E", "F"}, result);
	}
}
