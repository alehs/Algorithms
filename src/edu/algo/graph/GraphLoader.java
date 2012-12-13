package edu.algo.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.algo.graph.impl.MatrixGraph;

public class GraphLoader {

    public static Graph loadWeightedMatrixGraph(String path) throws FileNotFoundException {
        MatrixGraph graph = null;

        File file = new File(path);
        Scanner scanner = new Scanner(file);
        try {
            String totalStr = scanner.nextLine();
            String totals[] = totalStr.split("\\s");
            int totalNodes = Integer.valueOf(totals[0]);
            int totalEdges = Integer.valueOf(totals[1]);

            graph = new MatrixGraph(totalNodes, totalEdges);

            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\s");
                graph.addEdge(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]), Integer.valueOf(parts[2]));
            }

        } finally {
           scanner.close();
        }

        return graph;
    }
    
}
