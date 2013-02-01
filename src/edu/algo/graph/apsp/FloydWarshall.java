package edu.algo.graph.apsp;

import java.io.FileNotFoundException;

import edu.algo.Utils;
import edu.algo.graph.Graph;
import edu.algo.graph.GraphLoader;

public class FloydWarshall {

	int [][] A;
	//int [][] B;
	int n;

	public static void main(String[] args) throws FileNotFoundException {
		execute(new FloydWarshall(GraphLoader.loadDirectedMatrixGraph("data/floyd-test0.txt")));

//		execute(new FloydWarshall(GraphLoader.loadDirectedMatrixGraph("data/g1.txt")));
//		execute(new FloydWarshall(GraphLoader.loadDirectedMatrixGraph("data/g2.txt")));
//		execute(new FloydWarshall(GraphLoader.loadDirectedMatrixGraph("data/g3.txt")));
	}

	public FloydWarshall(Graph g) {
		this.n = g.size();
		this.A = new int[n+1][n+1];
		init(g);
	}

	public static void execute(FloydWarshall algo) {
		algo.execute();
		algo.print();
		if (!algo.hasNegativeCycle()) {
			Utils.print("shortest path = " + algo.getShortestPath());
		} else {
			Utils.print("Negative cycle detected");
		}
	}

	/**
	 * 1. Base case.
	 */
	void init(Graph g) {
		for (int i = 1; i <= g.size(); i++) {
			for (int j = 1; j <= g.size(); j++) {
				if (i==j) {
					A[i][j] = 0;
				} else if (g.hasEdge(i, j)) {
					A[i][j] = g.getEdgeWeight(i, j);
				} else {
					A[i][j] = Integer.MAX_VALUE;
				}
				//B[i][j] = Integer.MAX_VALUE;
			}
		}
	}

	/**
	 * 2. Recurrence.
	 */
	public void execute() {
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					int len = Integer.MAX_VALUE;
					if (A[i][k] != Integer.MAX_VALUE && A[k][j] != Integer.MAX_VALUE) {
						len = A[i][k] + A[k][j];
					}
					if (len < A[i][j]) {
						A[i][j] = len;
					}
				}
			}
		}
	}

	/**
	 * 3. check for negative loop
	 */
	public boolean hasNegativeCycle() {
		for (int i = 1; i <= n; i++) {
			if (A[i][i] < 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 4. find shortest shortest path.
	 */
	public int getShortestPath() {
		int min = Integer.MAX_VALUE;
		min = Integer.MAX_VALUE;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i != j) {
					if (A[i][j] < min) {
						min = A[i][j];
					}
				}
			}
		}
		return min;
	}

	public void print() {
		print(A, n);
	}
	
	public void print(int[][] arr, int n) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (arr[i][j] ==  Integer.MAX_VALUE) {
					Utils.printf("x ");
				} else {
					Utils.printf("%d ", arr[i][j]);
				}
			}
			Utils.print("");
		}
	}
}
