package edu.algo.graphs.model.undirect;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class AdjacencyMatrixGraph implements Graph {

	int[][] vertices;
	int size;

	public AdjacencyMatrixGraph(int vNum) {
		this.size = vNum;
		this.vertices = new int[vNum][vNum];
		for (int i = 0; i < size; i ++) {
			for (int j = 0; j < size; j ++) {
				vertices[i][j] = 0;
			}
		}
	}

	@Override
	public void addEdge(int fromV, int toV) {
		vertices[fromV][toV] = 1;
		vertices[toV][fromV] = 1;
	}

	@Override
	public Iterable<Integer> adj(int v) {
		List<Integer> adjList = new ArrayList<>();
		for (int i=0; i<size; i++) {
			if (vertices[v][i] == 1) {
				adjList.add(i);
			}
		}
		return adjList;
	}

	@Override
	public int vNum() {
		return size;
	}

	@Override
	public int gNum() {
		return size;
	}
}
