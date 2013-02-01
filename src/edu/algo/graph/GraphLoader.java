package edu.algo.graph;

import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.algo.Utils;
import edu.algo.graph.impl.DirectedMatrixGraph;
import edu.algo.graph.impl.MatrixGraph;

public class GraphLoader {

    public static Graph loadWeightedMatrixGraph(String path) throws FileNotFoundException {
        Graph graph = null;
        try (Scanner scanner = Utils.openFile(path)) {
    		String totalStr = scanner.nextLine();
    		String totals[] = totalStr.split("\\s");
    		int totalNodes = Integer.valueOf(totals[0]);
    		//int totalEdges = Integer.valueOf(totals[1]);

    		graph = new MatrixGraph(totalNodes, 0/*totalEdges*/);
            readToGraph(scanner, graph);
        }
        return graph;
    }

	public static Graph loadDirectedMatrixGraph(String path) throws FileNotFoundException {
        Graph graph = null;
        try (Scanner scanner = Utils.openFile(path)) {
    		String totalStr = scanner.nextLine();
    		String totals[] = totalStr.split("\\s");
    		int totalNodes = Integer.valueOf(totals[0]);
    		//int totalEdges = Integer.valueOf(totals[1]);

    		graph = new DirectedMatrixGraph(totalNodes, 0/*totalEdges*/);
            readToGraph(scanner, graph);
        }
        return graph;
    }

	private static void readToGraph(Scanner scanner, Graph graph) {
		while(scanner.hasNextLine()) {
		    String line = scanner.nextLine();
		    String[] parts = line.split("\\s");
		    graph.addEdge(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]), Integer.valueOf(parts[2]));
		}
	}

}
