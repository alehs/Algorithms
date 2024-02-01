package edu.algo.misc;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
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
 * This solution is using DFS graph search algorithm
 */
public class NumberOfIslandsDFS {

	List<List<Integer>> adjList;

	int [] visited;
	int size = 0;

	public static void main(String[] args) {
		char[][] grid = new char[][]{
				{'1', '1', '0', '1', '0'},
				{'1', '1', '0', '1', '0'},
				{'0', '0', '0', '0', '0'},
				{'0', '0', '0', '1', '1'}
		};

		NumberOfIslandsDFS dfs = new NumberOfIslandsDFS(grid);
		System.out.println("Number of islands = "+ dfs.getNumberOfIslands());
	}

	NumberOfIslandsDFS(char[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		this.size = n*m;
		this.adjList = new ArrayList<>(size);

		for (int i=0; i<n; i++) {
			for (int j = 0; j<m; j++) {

				List<Integer> neighbors = new ArrayList<>();
				adjList.add(neighbors);

				if (grid[i][j] == '1') {
					neighbors.add(toNodeIndex(i,j,m));

					if (i > 0 && grid[i-1][j] == '1') {
						neighbors.add(toNodeIndex(i-1,j,m));
					}
					if (i < n - 1 && grid[i+1][j] == '1') {
						neighbors.add(toNodeIndex(i+1,j,m));
					}
					if (j > 0 && grid[i][j-1] == '1') {
						neighbors.add(toNodeIndex(i,j-1,m));
					}
					if (j < m - 1 && grid[i][j+1] == '1') {
						neighbors.add(toNodeIndex(i,j+1,m));
					}
				}
			}
		}
		this.visited = new int[size];
	}

	private int toNodeIndex(int i, int j, int m) {
		return i*m + j;
	}

	private int getNumberOfIslands() {
		int nextNode = getNextUnvisitedIslandNode(0);
		int count = 0;
		while (nextNode != -1) {
			count++;
			dfs(nextNode);
			nextNode = getNextUnvisitedIslandNode(nextNode+1);
		}
		return count;
	}

	private void dfs(int nextNode) {
		Deque<Integer> queue = new LinkedList<>();
		queue.add(nextNode);

		while (queue.size() > 0) {
			int node = queue.pollLast();
			visited[node] = 1;

			List<Integer> neighbors = getNeighbors(node);
			for (Integer n : neighbors) {
				if (visited[n] == 0 && n != node) {
					queue.add(n);
				}
			}
		}
	}

	private List<Integer> getNeighbors(int node) {
		return adjList.get(node);
	}

	private int getNextUnvisitedIslandNode(int i) {
		int curr = i;
		while (curr < size) {
			if (visited[curr] == 0 && !adjList.get(curr).isEmpty()) {
				return curr;
			} else {
				curr++;
			}
		}
		return -1;
	}


}
