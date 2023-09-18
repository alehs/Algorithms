package edu.algo.graphs.depthfirst;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.algo.graphs.model.undirect.Graph;
import edu.algo.graphs.model.undirect.SimpleGraphLoader;

/**
 *  Depth-First search implemented base on arrays of primitives
 *
 * Properties:
 *  1. If vertex was visited - it means that exists path to this vertex
 *  2. Can reconstruct path in time proportional to its length
 */
public class DFS {

	int[] visited;
	int[] edgeTo;
	Graph graph;

	DFS(Graph graph) {
		this.graph = graph;
		visited = new int[graph.vNum()];
		edgeTo = new int[graph.vNum()];
		for (var i = 0; i < graph.vNum(); i++) {
			visited[i] = 0;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Graph graph = SimpleGraphLoader.loadAdjListGraph("data/graph/simpleGraph.txt");
		int from = 0;
		int to = 3;

		DFS dfs = new DFS(graph);
		List<Integer> path = dfs.getPath(from, to);

		System.out.println("DFS search from " + from + " to " + to + " = " + path);
	}

	private List<Integer> getPath(int start, int target) {
		explorePath(start, target);
		return reconstructPath(target, start);
	}

	/**
	 * - Mark vertex as visited,
	 * - recursively visit all unmarked adjacent vertices
	 */
	private void explorePath(int v, int target) {
		visited[v] = 1;
		Iterable<Integer> neighbors = this.graph.adj(v);
		for (int n : neighbors) {
			if (visited[n] != 1) {
				this.edgeTo[n] = v;
				// exit as soon as the path was found
				if (n == target) break;
				explorePath(n, target);
			}
		}
	}

	private List<Integer> reconstructPath(int target, int start) {
		List<Integer> path = new ArrayList<>();
		int curr = target;
		while (curr != start) {
			path.add(curr);
			curr = edgeTo[curr];
		}
		path.add(start);
		Collections.reverse(path);
		return path;
	}

}
