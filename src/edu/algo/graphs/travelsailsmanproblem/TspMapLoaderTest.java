package edu.algo.graphs.travelsailsmanproblem;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TspMapLoaderTest {

	@Test
	public void testLoad() throws FileNotFoundException {
		TspMap data = TspMapLoader.loadTSP("data/tsp.txt");
		assertEquals(25, data.points.size());

		assertEquals(20833.3333d, data.points.get(0).x);
		assertEquals(17100.0000d, data.points.get(0).y);

		assertEquals(27233.3333d, data.points.get(24).x);
		assertEquals(10450.0000d, data.points.get(24).y);
	}

}
