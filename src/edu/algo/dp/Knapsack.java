package edu.algo.dp;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import edu.algo.Utils;

public class Knapsack {

	private int a[][];
	private int m[];

	public static void main(String[] args) throws FileNotFoundException {
		KnapsackLoader loader = new KnapsackLoader("data/knapsack1.txt");
		Knapsack kp = new Knapsack();
		kp.execute1(loader.load(), loader.getCapacity());
		Utils.print("optimal solution = " + kp.getMax1());
	}

	public void execute1(List<Item> items, int capacity) {
		m = new int[capacity + 1];

		for (int i = 0; i < items.size(); i++) {
			for (int j = capacity; j >=0; j--) {
				int wi = items.get(i).size;
				if ( j >= wi) {
					int c2 = m[j-wi] + items.get(i).value;
					if (m[j] < c2) {
						m[j] = c2;
					}
				}
			}
		}
	}
	
	public void execute(List<Item> items, int capacity) {
		a = new int[items.size()+1][capacity+1];
		for (int i = 0; i < capacity; i++) {
			a[0][i] = 0;
		}

		for (int i = 1; i <= items.size(); i++) {
			for (int j = 0; j <= capacity; j++) {
				int c1 = a[i-1][j];
				int wi = items.get(i-1).size;
				if ( j >= wi) {
					int c2 = a[i-1][j-wi] + items.get(i-1).value;
					if (c1 > c2) {
						a[i][j] = c1;
					} else {
						a[i][j] = c2;
					}
				} else {
					a[i][j] = c1;
				}
				
				print(i, j);
			}
		}
	}

	public int getMax() {
		int lastx = a.length-1;
		int lasty = a[lastx].length-1;
		return a[lastx][lasty];
	}
	
	public int getMax1() {
		return m[m.length-1];
	}
	
	private void print(int i, int j) {
		Utils.print("step " + i + " " + j);
		for (int k = 0; k < a.length; k++) {
			Utils.print(Arrays.toString(a[k]));
		}
	}

}
