package edu.algo.graph.tsp;

import java.io.FileNotFoundException;

import junit.framework.Assert;

import org.junit.Test;

public class TspMapLoaderTest {

	@Test
	public void testLoad() throws FileNotFoundException {
		TspMap data = TspMapLoader.loadTSP("data/tsp.txt");
		Assert.assertEquals(25, data.points.size());

		Assert.assertEquals(20833.3333d, data.points.get(0).x);
		Assert.assertEquals(17100.0000d, data.points.get(0).y);

		Assert.assertEquals(27233.3333d, data.points.get(24).x);
		Assert.assertEquals(10450.0000d, data.points.get(24).y);
	}

}
