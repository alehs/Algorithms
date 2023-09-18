package edu.algo.graphs.clustering;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class HammingClustLoaderTest {

    @Test
    @Disabled
    public void loadWeightedMatrixGraph() throws FileNotFoundException {
        Cluster graph = HammingClustLoader.load("data/clustering-test-haffman.txt");
        assertEquals(7, graph.size());
        assertEquals(5, graph.getEdge(1, 3).weight);
    }

    @Test
    @Disabled
    public void testCodesToDist() {
        /**
         * Expected:
         *   1 2 3
         * 1 0 4 3
         * 2 4 0 1
         * 3 3 1 0
         */
//        MatrixGraph graph = HammingClustLoader.codesToDist(Arrays.asList(new String[] {"1001", "0110", "1110"}));
//        assertEquals(4, graph.getEdge(1, 2).weight);
//        assertEquals(1, graph.getEdge(3, 2).weight);
    }
    
    @Test
    public void testCalcDist() {
        assertEquals(4, HammingClustLoader.calcDist("1001", "0110"));
        assertEquals(3, HammingClustLoader.calcDist("1001", "1110"));
        assertEquals(2, HammingClustLoader.calcDist("1001", "1111"));
        assertEquals(1, HammingClustLoader.calcDist("1001", "1011"));
    }

    @Test
    public void testCalcBitsNum() {
        assertEquals(2, HammingClustLoader.calcBitsNum("1001"));
        assertEquals(3, HammingClustLoader.calcBitsNum("1101"));
        assertEquals(1, HammingClustLoader.calcBitsNum("1000"));
        assertEquals(0, HammingClustLoader.calcBitsNum("0000"));
    }
    
    @Test
    public void testSortByDist() {
        List<HammingClustLoader.HammDist> distances = new ArrayList<>();
        distances.add(new HammingClustLoader.HammDist("0001", 1));
        distances.add(new HammingClustLoader.HammDist("1111", 2));
        distances.add(new HammingClustLoader.HammDist("1001", 3));
        distances.add(new HammingClustLoader.HammDist("0000", 4));
        HammingClustLoader.sortByDist(distances);

        assertEquals("0000", distances.get(0).code);
        assertEquals("0001", distances.get(1).code);
        assertEquals("1001", distances.get(2).code);
    }
}
