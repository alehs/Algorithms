package edu.algo.graph.impl;

import java.util.ArrayList;
import java.util.List;

import edu.algo.graph.Edge;
import edu.algo.graph.Graph;

public class MatrixGraph implements Graph {

    /** Adjacency list. */
    public int[][] graph;

    public MatrixGraph(int [][] graph) {
        this.graph = graph;
    }

    public MatrixGraph(int vNum, int eNum) {
        this.graph = new int[vNum][vNum];
    }

    public void addEdge(int v1, int v2, int weight) {
        checkRange(v1);
        checkRange(v2);
        int i1 = toIndex(v1);
        int i2 = toIndex(v2);
        graph[i1][i2] = weight;
        graph[i2][i1] = weight;
    }

    protected final int toIndex(int v) {
        return v-1;
    }

    protected final int fromIndex(int index) {
        return index + 1;
    }

    protected void checkRange(int v) {
        if (v > graph.length) {
            throw new RuntimeException("Vertex number " + v + " exceeds graph size " + graph.length);
        }
    }

    /* (non-Javadoc)
     * @see edu.algo.graph.Il#getReachableNodes(int)
     */
    @Override
    public List<Integer> getReachableNodes(int node) {
        List<Integer> nodes = new ArrayList<>();
        int[] line = graph[toIndex(node)];
        for (int i = 0; i < line.length; i++) {
            if (line[i] != 0) {
                nodes.add(fromIndex(i));
            }
        }
        return nodes; 
    }

    /* (non-Javadoc)
     * @see edu.algo.graph.Il#getEdgeWeight(int, int)
     */
    @Override
    public int getEdgeWeight(int v1, int v2) {
        return graph[toIndex(v1)][toIndex(v2)];
    }

    /* (non-Javadoc)
     * @see edu.algo.graph.Il#getEdge(int, int)
     */
    @Override
    public Edge getEdge(int v1, int v2) {
        Edge e = null;
        if (v1 > 0 && v2 > 0 && v1 != v2) {
            int cost = getEdgeWeight(v1, v2);
            e = new Edge(v1, v2, cost);
//            if (cost != 0) {
//                e = new Edge(v1, v2, cost);
//            }
        }
        return e;
    }

    /* (non-Javadoc)
     * @see edu.algo.graph.Il#getSize()
     */
    @Override
    public int size() {
        return graph.length;
    }
}
