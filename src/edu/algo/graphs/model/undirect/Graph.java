package edu.algo.graphs.model.undirect;

public interface Graph {

	void addEdge(int fromV, int toV);

	// adjacent vertices to v
	Iterable<Integer> adj(int v);

	/**
	 * Number of vertices in graph
	 */
	int vNum();

	/**
	 * Number of edges in graph
	 */
	int gNum();

	String toString();
}
