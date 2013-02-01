package edu.algo.clust;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import edu.algo.Utils;
import edu.algo.graph.Edge;
import edu.algo.graph.impl.MatrixGraph;

public class HammingClustLoader {

    public static Cluster load(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        try {
            String totalStr = scanner.nextLine();
            String totals[] = totalStr.split("\\s");
            int totalNodes = Integer.valueOf(totals[0]);
            // int codeLength = Integer.valueOf(totals[1]);

            List<HammDist> codes = new ArrayList<>(totalNodes);
            int count = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                codes.add(new HammDist(line.replaceAll("\\s", ""), count ++));
            }

            Utils.print("File scanning completed, # of nodes = " + codes.size());
            return createCluster(codes);
        } finally {
            scanner.close();
        }
    }

    static Cluster createCluster(List<HammDist> distances) {
        MatrixGraph graph = new MatrixGraph(distances.size(), 0/*totalEdges*/);
        Cluster cl = new Cluster(graph.graph);

        Utils.print("Init graph default edges");
        for (int i = 0; i < graph.size(); i++) {
            for (int j = 0; j < graph.size(); j++) {
                graph.graph[i][j] = 100000;
            }
        }

        sortByDist(distances);

        Utils.print("Building graph...");
        for (int i = 0; i < distances.size(); i++) {
            int len0 = distances.get(i).len;
            inner: for (int j = i + 1; j < distances.size(); j++) {
                if ((distances.get(j).len - len0) <= 3) {
                    int cost = calcDist(distances.get(i).code, distances.get(j).code);

                    int from = distances.get(i).index;
                    int to = distances.get(j).index;

                    if (cost <= 5) {
                        //Utils.printf("nodes (%s-%d-%d,%s-%d-%d)  cost=%d \n", distances.get(i).code, from,len0, distances.get(j).code, to,distances.get(j).len, cost);
                        graph.addEdge(from, to, cost);
                        cl.edges.add(new Edge(from, to, cost));
                    }

                } else {
                    break inner;
                }
            }
        }

        Collections.sort(cl.edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });

        Utils.print("done building " + cl.toString());
        return cl;
    }

    static void sortByDist(List<HammDist> distances) {
        Collections.sort(distances, new Comparator<HammDist>() {
            @Override
            public int compare(HammDist o1, HammDist o2) {
                return o1.len - o2.len;
            }
        });
    }

    public static class HammDist {
        String code;
        int index;
        int len;
        public HammDist(String code, int index) {
            this.index = index;
            this.code = code;
            this.len = calcBitsNum(code);
        }
        @Override
        public String toString() {
            return code + " " + len;
        }
    }

    static int calcBitsNum(String code) {
        int bitNum = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '1') {
                bitNum ++;
            }
        }
        return bitNum;
    }

    static MatrixGraph codesToDist0(List<String> codes) {
        MatrixGraph graph = new MatrixGraph(codes.size(), 0/*totalEdges*/);
        for (int i = 0; i < codes.size(); i++) {
            for (int j = i + 1; j < codes.size(); j++) {
                int cost = calcDist(codes.get(i), codes.get(j));
                Utils.printf("nodes # %d,%d, cost=%d \n", i, j, cost);
                //if (cost != 0) {
                graph.addEdge(i+1, j+1, cost);
                //}
            }
        }
        return graph;
    }

    static int calcDist(String from, String to) {
        int dist = 0;
        for (int i = 0; i < from.length(); i++) {
            if (from.charAt(i) != to.charAt(i)) {
                dist++;
            }
        }
        return dist;
    }

}
