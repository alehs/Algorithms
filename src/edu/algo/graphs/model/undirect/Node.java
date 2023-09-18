package edu.algo.graphs.model.undirect;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Node {

	public int val;
	Node next;
	public Set<Node> neighbors = new HashSet<>();
	public boolean visited;

	public Node(int v) {
		val = v;
	}

	Node (int v, Node[] n) {
		val = v;
		neighbors.addAll(List.of(n));
	}

	@Override
	public String toString() {
		return "val:" + val;
	}
}
