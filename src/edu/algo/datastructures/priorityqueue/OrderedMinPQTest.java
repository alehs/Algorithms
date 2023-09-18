package edu.algo.datastructures.priorityqueue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderedMinPQTest {

	@Test
	public void testSimplePQueue() {
		PQ pq = new OrderedMinPQ(10);
		pq.insert("C");
		pq.insert("D");
		pq.insert("E");
		assertEquals("C", pq.pop());
		pq.insert("F");
		assertEquals("D", pq.pop());
	}


}
