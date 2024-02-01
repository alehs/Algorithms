package edu.algo.datastructures.heap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryHeapTest {

	@Test
	public void testBinaryHeap() {
		BinaryHeap pq = new BinaryHeap(10);
		pq.insert("D");
		pq.insert("E");
		pq.insert("C");
		assertEquals("E", pq.pop());
		pq.insert("A");
		assertEquals("D", pq.pop());
	}

}
