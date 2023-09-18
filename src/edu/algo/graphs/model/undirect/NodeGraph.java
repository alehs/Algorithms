package edu.algo.graphs.model.undirect;

import java.util.ArrayList;
import java.util.List;

public class NodeGraph implements Graph {
	public List<Node> nodes;

	public NodeGraph(int vNum) {
		nodes = new ArrayList<>(vNum);
		for(int i=0; i<vNum; i++) {
			nodes.add(new Node(i));
		}
	}

	@Override
	public void addEdge(int fromV, int toV) {
		Node fromNode = nodes.get(fromV);
		Node toNode = nodes.get(toV);
		fromNode.neighbors.add(toNode);
		toNode.neighbors.add(fromNode);
	}

	@Override
	public Iterable<Integer> adj(int v) {
		List<Integer> adjList = new ArrayList<>();
		Node vNode = nodes.get(v);
		for (Node n : vNode.neighbors) {
			adjList.add(n.val);
		}
		return adjList;
	}

//	public boolean hasPath(int fromVertex, int toVertex) {
//
//	}
//
//	public boolean getPath(int fromVertex, int toVertex) {
//
//	}

	@Override
	public int vNum() {
		return nodes.size();
	}

	@Override
	public int gNum() {
		return vNum();
	}
}
