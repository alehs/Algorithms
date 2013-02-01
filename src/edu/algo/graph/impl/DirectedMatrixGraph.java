package edu.algo.graph.impl;

import java.util.ArrayList;
import java.util.List;


public class DirectedMatrixGraph extends MatrixGraph {

    public DirectedMatrixGraph(int [][] graph) {
        super(graph);
    }

    public DirectedMatrixGraph(int vNum, int eNum) {
    	super(vNum, eNum);
        for (int i = 0; i < vNum; i++) {
        	for (int j = 0; j < vNum; j++) {
				this.graph[i][j] = Integer.MAX_VALUE;
			}
		}
    }

    public void addEdge(int v1, int v2, int weight) {
        checkRange(v1);
        checkRange(v2);
        int i1 = toIndex(v1);
        int i2 = toIndex(v2);
        graph[i1][i2] = weight;
    }

    public boolean hasEdge(int v1, int v2) {
    	return getEdgeWeight(v1, v2) != Integer.MAX_VALUE;
    }

    @Override
    public List<Integer> getIncomingNodes(int node) {
        List<Integer> nodes = new ArrayList<>();
        int nindex = toIndex(node);
        for (int i = 0; i < graph.length; i++) {
			if (graph[i][nindex] != Integer.MAX_VALUE) {
				nodes.add(fromIndex(i));
			}
		}
        return nodes;
    }

    @Override
    public List<Integer> getReachableNodes(int node) {
        List<Integer> nodes = new ArrayList<>();
        int[] line = graph[toIndex(node)];
        for (int i = 0; i < line.length; i++) {
            if (line[i] != Integer.MAX_VALUE) {
                nodes.add(fromIndex(i));
            }
        }
        return nodes;
    }
}
