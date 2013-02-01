package edu.algo.clust;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import edu.algo.graph.Edge;
import edu.algo.graph.impl.MatrixGraph;

public class Cluster extends MatrixGraph implements Cloneable {

    //private MatrixGraph graph;
    List<Leader> leaders;
    Map<Integer, Leader> lmap;
    List<Edge> edges;

    public Cluster(int[][] graph) {
        super(graph);
        this.leaders = new ArrayList<>(graph.length);
        this.edges = new LinkedList<>();
        this.lmap = new HashMap<Integer, Leader>();

        for (int i = 1; i <= graph.length; i++) {
            Leader lead = new Leader(i);
            leaders.add(lead);
            lmap.put(i, lead);
        }
    }

    public void initEdges() {
        for (int i = 1; i <= graph.length; i++) {
            for (int j = i + 1; j <= graph.length; j++) {
                Edge e = getEdge(i, j);
                if (e != null) {
                    edges.add(e);
                }
            }
        }
        Collections.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });
    }
    
    /**
     * Merges two clusters in single one.
     */
    public void merge(Leader lead1, Leader lead2) {
        if (lead1.getSize() > lead2.getSize()) {
            doMerge(lead2, lead1);
        } else {
            doMerge(lead1, lead2);
        }
    }

    public Leader getLeader(int node) {
        return lmap.get(node);
    }

    protected void doMerge(Leader from, Leader to) {
        for (Integer node : from.getNodes()) {
            to.add(node);
            lmap.put(node, to);
        }
        leaders.remove(from);
    }

    protected boolean isSameCluster(int node1, int node2) {
        return getLeader(node1).equals(getLeader(node2));
    }

    public int getK() {
        return leaders.size();
    }

    public Edge nextMinSeparateEdge() {
        if (edges.size() == 0) {
            return null;
        }
        Edge e = edges.remove(0);
        if (isSameCluster(e.vertex1, e.vertex2)) {
            return nextMinSeparateEdge();
        }
        return e;
    }

    @Override
    protected Cluster clone() throws CloneNotSupportedException {
        Cluster copy = new Cluster(this.graph.clone());
        copy.edges = new ArrayList<>(this.edges);
        return copy;
    }
    
    @Override
    public String toString() {
        return String.format("edges=%d, leaders=%d", this.edges.size(), this.leaders.size()); 
    }
}
