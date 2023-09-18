package edu.algo.dynamicprogramming;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnapsackTest {

	@Test
	public void test1 () throws FileNotFoundException {
		Knapsack kp = new Knapsack();
		KnapsackLoader loader = new KnapsackLoader("data/knapsack_test1.txt");
		kp.execute1(loader.load(), loader.getCapacity());

		assertEquals(51, kp.getMax1());
	}

	@Test
	public void test2 () throws FileNotFoundException {
		Knapsack kp = new Knapsack();
		KnapsackLoader loader = new KnapsackLoader("data/knapsack_test2.txt");
		kp.execute1(loader.load(), loader.getCapacity());

		assertEquals(1686, kp.getMax1());
	}

}
