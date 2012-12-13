package edu.algo.graph.mst;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import edu.algo.Utils;
import edu.algo.graph.Edge;
import edu.algo.graph.Graph;
import edu.algo.graph.GraphLoader;

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
