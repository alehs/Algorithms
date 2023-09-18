package edu.algo.graphs.model.undirect;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyListGraph implements Graph {

	List<List<Integer>> graph;

	public AdjacencyListGraph(int vNum) {
		graph = new ArrayList<>(vNum);
		for (int i = 0; i < vNum; i++) {
			graph.add(new ArrayList<>());
		}
	}

	@Override
	public void addEdge(int fromV, int toV) {
		graph.get(fromV).add(toV);
		graph.get(toV).add(fromV);
	}

	@Override
	public Iterable<Integer> adj(int v) {
		return graph.get(v);
	}

	@Override
	public int vNum() {
		return graph.size();
	}

	@Override
	public int gNum() {
		return -1;
	}
}

