package edu.algo.graphs.travelsailsmanproblem;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TSPTest {

	@Test
	public void testNumOfSetBits() {
		assertEquals(1, TSP.numOfSetBits(4));
		assertEquals(2, TSP.numOfSetBits(5));
		assertEquals(0, TSP.numOfSetBits(0));
		assertEquals(1, TSP.numOfSetBits(1));
		assertEquals(2, TSP.numOfSetBits(3));
	}

	@Test
	public void testGetSetsOfCoordinality() {
		assertEquals(0, TSP.getSetsOfCoordinality(3, 4).size());

		List<Integer> list = TSP.getSetsOfCoordinality(3, 10);
		assertEquals(1, list.size());
		assertEquals(7, list.get(0).intValue());

		list = TSP.getSetsOfCoordinality(2, 10);
		assertEquals(5, list.size());
		assertEquals(3, list.get(0).intValue());
		assertEquals(5, list.get(1).intValue());
		assertEquals(6, list.get(2).intValue());
		assertEquals(9, list.get(3).intValue());
		assertEquals(10, list.get(4).intValue());
	}

	@Test
	public void testIndexesFromSet() {
		// 0101
		List<Integer> list = TSP.indexesFromSet(5);
		assertEquals(2, list.size());
		assertEquals(1, list.get(0).intValue());
		assertEquals(3, list.get(1).intValue());

		// 1010
		list = TSP.indexesFromSet(10);
		assertEquals(2, list.size());
		assertEquals(2, list.get(0).intValue());
		assertEquals(4, list.get(1).intValue());
	}

	@Test
	public void testExecution() throws FileNotFoundException {
		TspMap data = TspMapLoader.loadTSP("data/tsp-test.txt");
		TSP tsp = new TSP(data.size());
		assertEquals(12, tsp.execute(data));
	}
		
}
