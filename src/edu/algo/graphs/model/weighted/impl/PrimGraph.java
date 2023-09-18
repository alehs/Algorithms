package edu.algo.graphs.model.weighted.impl;

import java.util.ArrayList;
import java.util.List;

import edu.algo.ArrayUtils;

/**
 * Simple Matrix-based graph representation built on primitives.
 */
public class PrimGraph {

	/** Adjacency list. */
	public int[][] graph;

	public PrimGraph(int [][] graph) {
		this.graph = graph;
	}

	public PrimGraph merge(Edge e) {

		checkEdge(e);

		List<List<Integer>> list = new ArrayList<List<Integer>>();

		int i1 = toIndex(e.v1);
		int i2 = toIndex(e.v2);

		final int mergedIndex = i1 < i2 ? i1 : i2;
		final int maxIndex = i1 > i2 ? i1: i2;
		int mi = -1; 

		for (int i = 0; i < graph.length; i++) {

			if (i == i1 || i == i2) {

				int[] sub = graph[i];
				List<Integer> msub = new ArrayList<Integer>();

				for (int k : sub) {
					int ik = toIndex(k);
					if (ik != i1 && ik != i2) {
						if (ik > maxIndex) {
							msub.add(k-1);
						} else {
							msub.add(k);
						}
					}
				}

				if (mi>-1) {
					list.get(mi).addAll(msub);
				} else {
					list.add(msub);
					mi = i;
				}

			} else {

				int[] edges = graph[i];
				List<Integer> edgesList = new ArrayList<Integer>();

				for (int k : edges) {
					int ik = toIndex(k);
					if (ik == i1 || ik == i2) {
						edgesList.add(fromIndex(mergedIndex));
					} else {
						if (ik > maxIndex) {
							edgesList.add(k-1);
						} else {
							edgesList.add(k);
						}
					}
				}

				list.add(edgesList);
			}
		}

		return new PrimGraph(ArrayUtils.fromList(list));
	}

	public void checkEdge(Edge e) {
		int[] sub = this.graph[toIndex(e.v1)];

		if (!ArrayUtils.in(sub, e.v2)) {
			throw new IllegalStateException("Edge is not in graph " + e);
		}
	}

	static int toIndex(int i) {
		return i - 1;
	}
	static int fromIndex(int i) {
		return i + 1;
	}

	/**
	 * Returns number of nodes in graph.
	 */
	public int size() {
		return graph.length;
	}

	/**
	 * Returns number of edges in graph. 
	 */
	public int edgNum() {
		return 0;
	}

	/**
	 * Returns number of edges between two vertices.
	 */
	public int edgNum(int v1, int v2) {
		int i1 = toIndex(v1);
		int[] e1 = graph[i1];

		int num = 0;
		for (int i : e1) {
			if (i == v2) {
				num ++;
			}
		}

		return num;
	}

	public static class Edge {
		public int v1;
		public int v2;

		public Edge(int v1, int v2) {
			this.v1 = v1;
			this.v2 = v2;
		}

		@Override
		public String toString() {
			return v1 + "," + v2;
		}
	}

}
