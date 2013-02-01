package edu.algo.graph;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class GraphLoaderTest {

    @Test
    public void loadWeightedMatrixGraph() throws FileNotFoundException {
        Graph graph = GraphLoader.loadWeightedMatrixGraph("data/prim-mst-edges.txt");

        Assert.assertEquals(500, graph.size());
        Assert.assertEquals(6807, graph.getEdgeWeight(1, 2));
        Assert.assertEquals(-8874, graph.getEdgeWeight(2, 3));

        List<Integer> expected = Arrays.asList(new Integer[] {1,3,25,39,104,157,173,309});
        List<Integer> actual = graph.getReachableNodes(2);

        for (int i = 0; i < expected.size(); i++) {
            Assert.assertEquals(expected.get(i), actual.get(i));
        }
    }
}
