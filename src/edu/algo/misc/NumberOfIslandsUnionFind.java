package edu.algo.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an m x n 2D binary grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 * Input: grid = [
 *   ['1','1','1','1','0'],
 *   ['1','1','0','1','0'],
 *   ['1','1','0','0','0'],
 *   ['0','0','0','0','0']
 * ]
 * Output: 1
 * 
 * Example 2:
 * Input: grid = [
 *   ['1','1','0','0','0'],
 *   ['1','1','0','0','0'],
 *   ['0','0','1','0','0'],
 *   ['0','0','0','1','1']
 * ]
 * Output: 3
 * 
 * This solution is using UnionFind algorithm
 */
public class NumberOfIslandsUnionFind {

	private int[] arr;
	private int[] sz;
	private int[] land;

	private NumberOfIslandsUnionFind(int size) {
		arr = new int[size];
		for (int i=0; i<size; i++) {
			arr[i]= i;
		}
		land = new int[size];
	}

	public static class Point {
		public int i, j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	public static void main(String[] args) {
		char[][] grid = new char[][] {
				{'1','1','0','0','0'},
				{'1','1','0','0','0'},
				{'0','0','1','0','0'},
				{'0','0','0','1','1'}
		};

		int n = grid.length;
		int m = grid[0].length;

		NumberOfIslandsUnionFind algo = new NumberOfIslandsUnionFind(n*m);

		// N * M * log(N+M) -> P^2 log P
		for (int i = 0; i<n;i++) {
			for (int j=0;j<m;j++) {
				if (isIsland(grid, i ,j)) {
					int index = toIndex(i, j, m);
					algo.union(index, index);

					List<Point> neighbors = getNeighbors(grid, i, j);
					for(Point p : neighbors) {
						algo.union(index, toIndex(p.i, p.j, m));
					}
				}
 			}
		}

		System.out.println("Number of islands = " + algo.getNumberOfUnions());
	}

	private static boolean isIsland(char[][] grid, int i, int j) {
		return grid[i][j] == '1';
	}

	private static List<Point> getNeighbors(char[][] grid, int i, int j) {
		List<Point> neighbors = new ArrayList<>();
		if (i > 0 && grid[i - 1][j] == '1') {
			neighbors.add(new Point(i-1, j));
		}
		if (j > 0 && grid[i][j - 1] == '1') {
			neighbors.add(new Point(i, j-1));
		}
		if (i < grid.length - 1 && grid[i+1][j] == '1') {
			neighbors.add(new Point(i+1, j));
		}
		if (j < grid[0].length - 1 && grid[i][j+1] == '1') {
			neighbors.add(new Point(i, j+1));
		}
		return neighbors;
	}

	private static int toIndex(int i, int j, int m) {
		return i*m + j;
	}

	private void union(int i, int j) {
		if (i != j) {
			int iRoot = root(i);
			int jRoot = root(j);
			arr[iRoot] = jRoot;
		}
		this.land[i] = 1;
		this.land[j] = 1;
	}

	private int root(int i) {
		int curr = i;
		int parent = arr[i];
		while (parent != arr[curr]) {
			arr[curr] = arr[arr[curr]];
			curr = arr[parent];
		}
		return parent;
	}

	private int getNumberOfUnions() {
		int counter = 0;
		for (int i=0; i<arr.length; i++) {
			if (land[i] != 0 && root(i) == i) {
				counter ++;
			}
		}
		return counter;
	}

}
