package edu.algo.graphs.travelsailsmanproblem;

import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.algo.Utils;

public class TspMapLoader {

    public static TspMap loadTSP(String path) throws FileNotFoundException {
    	TspMap tspmap = null;
        try (Scanner scanner = Utils.openFile(path)) {
    		int totalNodes = Integer.valueOf(scanner.nextLine().trim());
    		tspmap = new TspMap(totalNodes);

    		while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\s");
                tspmap.addPoint(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
            }
        }
        return tspmap;
    }

}
