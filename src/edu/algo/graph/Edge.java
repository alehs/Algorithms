package edu.algo.graph;

public class Edge {
    public int vertex1;
    public int vertex2;
    public int weight;

    public Edge(int v1, int v2, int weight) {
        this.vertex1 = v1;
        this.vertex2 = v2;
        this.weight = weight;
    }
    
    @Override
    public String toString() {
        return String.format("(%d,%d,%d)", vertex1, vertex2, weight);
    }
}
