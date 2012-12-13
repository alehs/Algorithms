package edu.algo.sort;
import static junit.framework.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class QuickSortTest {

	@Test
	public void testChooseFirst() {
		int[] arr = {1,2,3,4,5};
		assertEquals(0, PivotChooser.FIRST.choose(arr, 0, arr.length));
		assertEquals(1, PivotChooser.FIRST.choose(arr, 1, arr.length));
		assertEquals(2, PivotChooser.FIRST.choose(arr, 2, arr.length-1));
	}

	@Test
	public void testChooseLast() {
		int[] arr = {1,2,3,4,5};
		assertEquals(4, PivotChooser.LAST.choose(arr, 0, arr.length-1));
		assertEquals(3, PivotChooser.LAST.choose(arr, 1, arr.length-2));
		assertEquals(2, PivotChooser.LAST.choose(arr, 0, arr.length-3));
	}

	@Test
	public void testChooseMiddle() {
		int[] arr = {1,2,3,4,5};
		assertEquals(2, PivotChooser.MEDIAN.choose(arr, 0, arr.length-1));
		assertEquals(2, PivotChooser.MEDIAN.choose(arr, 1, arr.length-1));
		assertEquals(1, PivotChooser.MEDIAN.choose(arr, 0, arr.length-3));

		arr = new int[] {5,2,1,0};
		assertEquals(1, PivotChooser.MEDIAN.choose(arr, 0, arr.length-1));
		assertEquals(2, PivotChooser.MEDIAN.choose(arr, 1, arr.length-1));
		assertEquals(0, PivotChooser.MEDIAN.choose(arr, 0, arr.length-3));
	}

	@Test
	public void testPartionAroundPivot1() {
		int[] arr = {7,6,5,4,3,2,1};
		// 4 6 5 7 3 2 1
		// 4 3 5 7 6 2 1
		// 4 3 2 7 6 5 1
		// 4 3 2 1 6 5 7
		// 1 3 2 4 6 5 7

		int lt = 0;
		int rt = arr.length - 1;
		int p = 3; // arr[3] = 4

		int np = QuickSort.partitionAroundPivot(arr, lt, rt, p);

		assertEquals(p, np);
		assertArrayEquals(new int[] {1,3,2,4,6,5,7}, arr);
	}

	@Test
	public void testPartionAroundPivot2() {
		int[] arr = {7,6,5,4,3,2,1};
		// 2 6 5 4 3 7 1
		// 2 1 5 4 3 7 6
		// 1 2 5 4 3 7 6 

		int lt = 0;
		int rt = arr.length - 1;
		int p = 5; // arr[5] = 2

		int np = QuickSort.partitionAroundPivot(arr, lt, rt, p);

		assertEquals(1, np);
		assertArrayEquals(new int[] {1,2,5,4,3,7,6}, arr);
	}

	@Test
	public void testPartionAroundPivot3() {
		int[] arr = {7,6,5,4,3,2,1};
		// 7 6 5 4 3 2 1
		//     5 4 3 2
		//     4 5 3 2
		//     4 3 5 2
		//     4 3 2 5
		//	   2 3 4 5

		int lt = 2;
		int rt = arr.length - 2;
		int p = 3; // arr[3] = 4

		int np = QuickSort.partitionAroundPivot(arr, lt, rt, p);

		assertEquals(4, np);
		assertArrayEquals(new int[] {7,6,2,3,4,5,1}, arr);
	}

	@Test
	public void testPartionAroundPivot4() {
		int[] arr = {7,6,5,4,3,2,1};
		// 7 6 5 4 3 2 1
		// 1 6 5 4 3 2 7

		int lt = 0;
		int rt = arr.length - 1;
		int p = 0; // arr[1] = 7

		int np = QuickSort.partitionAroundPivot(arr, lt, rt, p);

		assertEquals(6, np);
		assertArrayEquals(new int[] {1,6,5,4,3,2,7}, arr);
	}

	@Test
	public void testSortFirst() {
		int[] arr = {7,6,5,4,3,2,1};

		long num = QuickSort.sort(arr, 0, arr.length - 1, PivotChooser.MEDIAN);

		System.out.println(num);
		System.out.println(Arrays.toString(arr));

		assertArrayEquals(new int[] {1,2,3,4,5,6,7}, arr);
	}

	@Test
	public void testSortLast() {
		int[] arr = {7,6,5,4,3,2,1};

		long num = QuickSort.sort(arr, 0, arr.length - 1, PivotChooser.LAST);

		//assertEqual()
		System.out.println(num);
		System.out.println(Arrays.toString(arr));

		assertArrayEquals(new int[] {1,2,3,4,5,6,7}, arr);
	}

	private static void assertArrayEquals(int[] expected, int[] actual) {
		assertEquals(expected.length, actual.length);
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}
}
