package edu.algo.dp;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;

public class KnapsackLoaderTest {

	KnapsackLoader loader = new KnapsackLoader("data/knapsack1.txt");

	@Test
	public void testLoad() throws FileNotFoundException {
		List<Item> items = loader.load();
		assertEquals(100, items.size());
		assertEquals(16808, items.get(0).value);
		assertEquals(250, items.get(0).size);
		assertEquals(54680, items.get(items.size()-1).value);
		assertEquals(153, items.get(items.size()-1).size);
		
		assertEquals(10000, loader.getCapacity());
	}

}
