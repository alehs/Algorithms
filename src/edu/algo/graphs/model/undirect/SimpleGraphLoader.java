package edu.algo.graphs.model.undirect;


import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.algo.Utils;

public class SimpleGraphLoader {

	public static Graph loadAdjMatrixGraph(String path) throws FileNotFoundException {
		Graph graph;
		try (Scanner scanner = Utils.openFile(path)) {
			int vNum = Integer.valueOf(scanner.nextLine());
			int eNum = Integer.valueOf(scanner.nextLine());
			graph = new AdjacencyMatrixGraph(vNum);
			readToGraph(scanner, graph);
		}
		return graph;
	}

	public static Graph loadAdjListGraph(String path) throws FileNotFoundException {
		Graph graph;
		try (Scanner scanner = Utils.openFile(path)) {
			int vNum = Integer.valueOf(scanner.nextLine());
			int eNum = Integer.valueOf(scanner.nextLine());
			graph = new AdjacencyListGraph(vNum);
			readToGraph(scanner, graph);
		}
		return graph;
	}

	public static Graph loadNodeGraph(String path) throws FileNotFoundException {
		Graph graph;
		try (Scanner scanner = Utils.openFile(path)) {
			int vNum = Integer.valueOf(scanner.nextLine());
			int eNum = Integer.valueOf(scanner.nextLine());
			graph = new NodeGraph(vNum);
			readToGraph(scanner, graph);
		}
		return graph;
	}


	private static void readToGraph(Scanner scanner, Graph graph) {
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] parts = line.split("\\s");
			graph.addEdge(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]));
		}
	}
}
