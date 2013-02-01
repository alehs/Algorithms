package edu.algo.dp;

import java.io.FileNotFoundException;

import org.junit.Test;

import junit.framework.Assert;

public class KnapsackTest {

	@Test
	public void test1 () throws FileNotFoundException {
		Knapsack kp = new Knapsack();
		KnapsackLoader loader = new KnapsackLoader("data/knapsack_test1.txt");
		kp.execute1(loader.load(), loader.getCapacity());

		Assert.assertEquals(51, kp.getMax1());
	}

	@Test
	public void test2 () throws FileNotFoundException {
		Knapsack kp = new Knapsack();
		KnapsackLoader loader = new KnapsackLoader("data/knapsack_test2.txt");
		kp.execute1(loader.load(), loader.getCapacity());

		Assert.assertEquals(1686, kp.getMax1());
	}

}
