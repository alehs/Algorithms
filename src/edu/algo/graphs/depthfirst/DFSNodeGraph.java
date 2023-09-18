package edu.algo.graphs.depthfirst;

import edu.algo.graphs.model.undirect.Node;
import edu.algo.graphs.model.undirect.NodeGraph;
import edu.algo.graphs.model.undirect.SimpleGraphLoader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Depth-First search implemented base on the NodeGraph data structure.
 */
public class DFSNodeGraph {

	public static void main(String[] args) throws FileNotFoundException {
		NodeGraph graph = (NodeGraph) SimpleGraphLoader.loadNodeGraph("data/graph/simpleGraph.txt");
		int from = 0;
		int to = 5;
		List<Integer> path = searchPath(graph, from, to);
		System.out.println("DFS search from " + from + " to " + to + " = " + path);
	}

	public static List<Integer> searchPath(NodeGraph graph, int fromV, int toV) {
		Node startNode = graph.nodes.get(fromV);
		List<Integer> itinerary = new ArrayList<>();
		search(itinerary, startNode, toV);
		Collections.reverse(itinerary);
		return itinerary;
	}

	private static boolean search(List<Integer> path, Node node, int toV) {

		if (node.visited) {
			return false;
		}

		if (node.val == toV) {
			path.add(node.val);
			return true;
		}

		node.visited = true;

		for (Node n : node.neighbors) {
			if (search(path, n, toV)) {
				path.add(node.val);
				return true;
			}
		}
		return false;
	}


}
