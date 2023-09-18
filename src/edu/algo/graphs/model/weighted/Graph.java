package edu.algo.graphs.model.weighted;

import java.util.List;

public interface Graph {

    List<Integer> getReachableNodes(int node);

	List<Integer> getIncomingNodes(int node);

    void addEdge(int v1, int v2, int weight);

    int getEdgeWeight(int v1, int v2);

    boolean hasEdge(int v1, int v2);

    Edge getEdge(int v1, int v2);

    int size();
}
