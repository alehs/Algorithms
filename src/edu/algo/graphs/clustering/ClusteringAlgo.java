package edu.algo.graphs.clustering;

import java.io.FileNotFoundException;

import edu.algo.Utils;
import edu.algo.graphs.model.weighted.Edge;
import edu.algo.graphs.model.weighted.GraphLoader;
import edu.algo.graphs.model.weighted.impl.MatrixGraph;

public class ClusteringAlgo {

    public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException {
        hammingClustering1("data/clustering2.txt");
        //hammingClustering1("data/clustering-test-hamming.txt");
        //simpleClustering("data/clustering1.txt");
    }

    public static void simpleClustering(String path) throws FileNotFoundException {
        MatrixGraph graph = (MatrixGraph) GraphLoader.loadWeightedMatrixGraph(path);
        Cluster cl = new Cluster(graph.graph);
        int spacing = execute(cl, 4);
        Utils.print(spacing);
    }

    public static void hammingClustering2(String path) throws FileNotFoundException, CloneNotSupportedException {
        Cluster cluster = HammingClustLoader.load(path);
        Utils.print("Start alogrithm..");
        Cluster cl1 = cluster.clone();
        Edge e = cl1.nextMinSeparateEdge();
        int edgesNumber = 0;
        while (e.weight < 3) {
            edgesNumber ++;
            e = cl1.nextMinSeparateEdge();
            if (e == null) break;
        }
        Utils.print("# of edges with spacing < 3 is " + edgesNumber);
    }

    public static void hammingClustering1(String path) throws FileNotFoundException, CloneNotSupportedException {
        Cluster cluster = HammingClustLoader.load(path);
        Utils.print("Start alogrithm..");

        int k = 16506, maxK = 1;
        int spacing = 0;
        do {
            Cluster cl = cluster.clone();
            spacing = execute(cl, k);
            Utils.printf("K = %d, spacing = %d \n", k, spacing);
            if (spacing >= 3 ) {
                maxK = k;
            }
            k = k += 10;
        } while (spacing >= 3);

        Utils.print("overflow at k = " + k + ", maxK = " + maxK + " spacing " + spacing);
        k--;

        do {
            Cluster cl = cluster.clone();
            spacing = execute(cl, k);
            Utils.printf("k = %d, spacing = %d \n", k, spacing);
            if (spacing < 3 ) {
                k--;
            } else {
                maxK = k;
            }

        } while (spacing < 3 && k > 0);

        Utils.print("max K = " + maxK);
    }

    
    public static void hammingClustering(String path) throws FileNotFoundException, CloneNotSupportedException {
        Cluster cluster = HammingClustLoader.load(path);
        Utils.print("Start alogrithm..");

        int k = 16504, maxK = 1;
        //int k = 2, maxK = 1;
        int spacing = 0;
        do {
            Cluster cl = cluster.clone();
            spacing = execute(cl, k);
            Utils.printf("K = %d, spacing = %d \n", k, spacing);
            if (spacing >= 3 ) {
                maxK = k;
            }
            k++;
        } while (spacing >= 3);

        Utils.print("max K = " + maxK);
    }

    /**
     * Returns cluster spacing
     * @param cl
     * @param k
     * @return
     */
    public static int execute(Cluster cl, int k) {
        while (cl.getK() > k) {
            Edge e = cl.nextMinSeparateEdge();
            if (e == null) break;
            cl.merge(cl.getLeader(e.vertex1), cl.getLeader(e.vertex2));
        }

        Edge next = cl.nextMinSeparateEdge();
        if (next == null) {
            return Integer.MAX_VALUE;
        }
        return next.weight;
    }
}
