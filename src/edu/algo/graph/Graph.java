package edu.algo.graph;

import java.util.List;

public interface Graph {

    public abstract List<Integer> getReachableNodes(int node);

    public abstract int getEdgeWeight(int v1, int v2);

    public abstract Edge getEdge(int v1, int v2);

    public abstract int size();
}
