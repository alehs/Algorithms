package edu.algo.graphs.minimumspanningtree;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import edu.algo.Utils;
import edu.algo.graphs.model.weighted.Edge;
import edu.algo.graphs.model.weighted.Graph;
import edu.algo.graphs.model.weighted.GraphLoader;

/**
 * Minimum spanning tree (MST) algorithm.
 *
 * spanning tree - subset of the edges of connected edge-weighted graph
 * that connects all the vertices
 *
 * minimum spanning tree - spanning tree with minimum weight
 *
 *
 * Cut - partition of vertices in two sets
 * Crossing edge - connects a vertex in one set with vertex in the other
 *
 * Given any cut, the crossing edge with min weight is the MST.
 *
 * Greedy MST algorithm:
 *  - start with all edges colored grey
 *  - find cut with no black crossing edges, color its min-weight edge black
 *  - repeat until V-1 edges are colored black
 *
 *
 */
public class PrimMst {

    public static void main(String[] args) throws FileNotFoundException {
        Graph graph = GraphLoader.loadWeightedMatrixGraph("data/prim-mst-edges.txt");
        //MatrixGraph graph = GraphLoader.loadWeightedMatrixGraph("data/prim-test2.txt");

        List<Edge> spannedTree = execute(graph, 1);
        long cost = calcStCost(spannedTree);

        Utils.print("Total cost is " + cost);
    }

    /**
     * Main algorithm's method.
     * @param graph
     * @param startVertex
     * @return
     */
    private static List<Edge> execute(Graph graph, int startVertex) {
        List<Edge> spanned = new ArrayList<>(); // spanned tree.
        List<Integer> x = new ArrayList<>(); // vertices explored so-far.

        int v = startVertex;
        x.add(v);

        while (hasMoreNodes(graph, x)) {
            Edge e = findCheapestEdge(graph, x);
            x.add(e.vertex2);
            spanned.add(e);
        }

        return spanned;
    }

    static List<Edge> selectUnexploredEdges(Graph graph, int v, List<Integer> x) {
        List<Edge> result = new ArrayList<>();
        List<Integer> nodes = graph.getReachableNodes(v);
        for (int i = 0; i < nodes.size(); i++) {
            int node = nodes.get(i);
            if (!x.contains(node)) {
                result.add(new Edge(v, node, graph.getEdgeWeight(v, node)));
            }
        }
        return result;
    }

    static Edge findCheapestEdge(Graph graph, List<Integer> x) {
        int minV1 = 0, minV2 = 0;
        int minCost = Integer.MAX_VALUE;
        for (Integer from : x) {
            List<Integer> nodes = graph.getReachableNodes(from);
            for (Integer node : nodes) {
                // only nodes that are unexplored
                if (!x.contains(node)) {
                    int cost = graph.getEdgeWeight(from, node);
                    if (cost != 0 && cost < minCost) {
                        minV1 = from;
                        minV2 = node;
                        minCost = cost;
                    }
                }
            }
        }
        return graph.getEdge(minV1, minV2);
    }

    @Deprecated
    static Edge findCheapest(List<Edge> edges) {
        Edge cheapest = edges.get(0);
        for (Edge edge : edges) {
            if (edge.weight < cheapest.weight) {
                cheapest = edge;
            }
        }
        return cheapest;
    }

    static boolean hasMoreNodes(Graph graph, List<Integer> x) {
        return x.size() < graph.size();
    }

    private static long calcStCost(List<Edge> spannedTree) {
        long totalCost = 0;
        for (Edge edge : spannedTree) {
            if (totalCost > Integer.MAX_VALUE || totalCost < Integer.MIN_VALUE) {
                throw new RuntimeException("possible overflow...");
            }
            totalCost += edge.weight;
        }
        return totalCost;
    }

}
