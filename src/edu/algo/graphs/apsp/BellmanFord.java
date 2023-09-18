package edu.algo.graphs.apsp;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;

import edu.algo.Utils;
import edu.algo.graphs.model.weighted.Graph;
import edu.algo.graphs.model.weighted.GraphLoader;

/**
 * All-paris shortest Path problem.
 *
 * Modified Bellman-Ford Algorithm to prove some concept.
 */
public class BellmanFord {

	public static void main(String[] args) throws FileNotFoundException {
		Graph g = GraphLoader.loadDirectedMatrixGraph("data/floyd-test0.txt");
		execute(g);
	}

	public static void execute(Graph g) {
		int n = g.size();
		int [][] A = new int[n+1][n+1];

		A[0][1]=0;
		for (int i = 2; i <= n; i++) {
			A[0][i] = Integer.MAX_VALUE;
		}

		for (int i = 1; i < n; i++) {
			if (isOdd(i)) {
				for (int v = 1; v <= n; v++) {
					recurrence(g, A, i, v);
				}
			} else {
				for (int v = n; v > 0; v--) {
					recurrence(g, A, i, v);
				}
			}
		}

		print(A, n);
	}

	private static void recurrence(Graph g, int[][] a, int i, int v) {
		int calc = calcMin(i, v, g, a);
		if (a[i-1][v] > calc) {
			a[i][v] = calc;
		} else {
			a[i][v] = a[i-1][v];
		}
	}

	private static int calcMin(int i, int v, Graph g, int[][] a) {
		List<Integer> nodes = g.getIncomingNodes(v);
		if (isOdd(i)) {
			for (Iterator<Integer> it = nodes.iterator(); it.hasNext();) {
				if (it.next().intValue() > v) {
					it.remove();
				}
			}
		} else {
			for (Iterator<Integer> it = nodes.iterator(); it.hasNext();) {
				if (it.next().intValue() < v) {
					it.remove();
				}
			}
		}

		int min = Integer.MAX_VALUE;
		for (Integer w : nodes) {
			int val = a[i][w.intValue()] + g.getEdgeWeight(w, v);
			if (val < min) {
				min = val;
			}
		}

		return min;
	}

	public static boolean isOdd(int num) {
		return num%2 == 0 ? false : true;
	}

	public static void print(int[][] arr, int n) {
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
