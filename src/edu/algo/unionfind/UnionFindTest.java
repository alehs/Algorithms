package edu.algo.unionfind;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnionFindTest {

	/**
	 * Given
	 * 4 3
	 * 3 8
	 * 6 5
	 * 9 4
	 * 2 1
	 * 8 9
	 * 5 0
	 * 7 2
	 * 6 1
	 * 1 0
	 * 6 7
	 *
	 * {3,4,8,9} {0,1,2,5,6,7}
	 */
	@Test
	public void testQuickFind() {
		Union quickFind = UnionFindLoader.loadUnionFind("data/unionfind/tinyUF.txt", (N) -> QuickFind.init(N));
		assertFalse(quickFind.connection(0,9));
		assertFalse(quickFind.connection(7,8));
		assertTrue(quickFind.connection(0,7));
		assertTrue(quickFind.connection(3,9));
	}

	/**
	 * Given -- the same --
	 *
	 * {3,4,8,9} {0,1,2,5,6,7}
	 *
	 *   8        1
	 *  3 9     0 2 7
	 *  4       5
	 *          6
	 */
	@Test
	public void testQuickUnion() {
		Union quickUnion = UnionFindLoader.loadUnionFind("data/unionfind/tinyUF.txt", (N) -> QuickUnion.init(N));
		System.out.println(quickUnion.toString());
		assertFalse(quickUnion.connection(0,9));
		assertFalse(quickUnion.connection(7,8));
		assertTrue(quickUnion.connection(0,7));
		assertTrue(quickUnion.connection(3,9));
	}

	@Test
	public void testWeightedQuickUnion() {
		Union quickUnion = UnionFindLoader.loadUnionFind("data/unionfind/tinyUF.txt", (N) -> WeightedQuickUnion.init(N));
		System.out.println(quickUnion.toString());
		assertFalse(quickUnion.connection(0,9));
		assertFalse(quickUnion.connection(7,8));
		assertTrue(quickUnion.connection(0,7));
		assertTrue(quickUnion.connection(3,9));
	}
}
