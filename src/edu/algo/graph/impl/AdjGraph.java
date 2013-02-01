package edu.algo.graph.impl;

import java.util.ArrayList;
import java.util.List;

import edu.algo.graph.Edge;
import edu.algo.graph.Graph;
import edu.algo.graph.Node;

public class AdjGraph implements Graph {

    /** Adjacency list. */
    //public List<Integer>[] adjList;
    //public [][] adjList;
    public List<List<Node>> adjList;

    public AdjGraph(int vNum, int eNum) {
        adjList = new ArrayList<List<Node>>(vNum);
    }

    public int size() {
        return adjList.size();
    }

    /**
     * Returns labels of vertices that are reachable from the given vertex.
     */
    public List<Integer> getDistVertices(int vertice) {
        //return adjList.get(vertice);
        //return new ArrayList<Integer>(nodes[node]);
        return null;
    }

    @Override
    public List<Integer> getReachableNodes(int node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getEdgeWeight(int v1, int v2) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Edge getEdge(int v1, int v2) {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public boolean hasEdge(int v1, int v2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addEdge(int v1, int v2, int weight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Integer> getIncomingNodes(int node) {
		// TODO Auto-generated method stub
		return null;
	}

//    public Integer getEdgeCost(Integer v1, Integer v2) {
//         
//    }

}
