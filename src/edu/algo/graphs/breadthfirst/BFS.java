package edu.algo.graphs.breadthfirst;

import edu.algo.graphs.model.undirect.Graph;
import edu.algo.graphs.model.undirect.SimpleGraphLoader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Breadth-first search
 *
 * 	1) Remove vertex v from queue
 * 	2) Add to queue all unmarked adjacent to v and mark them
 *
 * Properties:
 *  - Computes shortest path from s to all other vertices in a graph in time proportional to E + V
 *
 *    Queue always contains of zero or more vertices of distance k from s, followed by zero or more vertices of dist k + 1
 *
 */
public class BFS {
	int marked[];
	int edgeTo[];
	int distTo[];
	Graph graph;

	BFS(Graph graph) {
		this.graph = graph;
		marked = new int[graph.vNum()];
		edgeTo = new int[graph.vNum()];
		distTo = new int[graph.vNum()];
		for (var i = 0; i < graph.vNum(); i++) {
			marked[i] = 0;
			distTo[i] = -1;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Graph graph = SimpleGraphLoader.loadAdjListGraph("data/graph/simpleGraph.txt");
		int from = 0;
		int to = 3;

		BFS bfs = new BFS(graph);
		List<Integer> path = bfs.getPath(from, to);

		System.out.println("DFS search from " + from + " to " + to + " = " + path);
	}

	private void explore(int s) {
		int dist = 0;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(s);
		distTo[s] = dist++;
		marked[s] = 1;
		while(!queue.isEmpty()) {
			int v = queue.poll();
			Iterable<Integer> adj = graph.adj(v);
			for (int w : adj) {
				if (marked[w] != 1) {
					marked[w] = 1;
					queue.add(w);
					edgeTo[w] = v;
					distTo[w] = dist;
				}
			}
			dist ++;
		}
	}

	private List<Integer> getPath(int from, int to) {
		explore(from);
		System.out.println("Distances: " + Arrays.toString(distTo));
		return reconstructPath(to, from);
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
