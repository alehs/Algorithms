package edu.algo.graphs.model.weighted.impl;

import edu.algo.graphs.model.weighted.Graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MatrixGraphTest {

    @Test
    public void testGetReachableNodes() {
        /**
         *    1 2 3 4__
         * 1 |0 1 0 0
         * 2 |1 0 2 3
         * 3 |0 2 0 0 
         * 4 |0 3 0 0
         */
        Graph graph = new MatrixGraph(new int[][] {{0,1,0,0},{1,0,2,3},{0,2,0,0},{0,3,0,0}});
        assertArrayEquals(new Integer[] {2}, graph.getReachableNodes(1).toArray());
        assertArrayEquals(new Integer[] {1,3,4}, graph.getReachableNodes(2).toArray());
        assertArrayEquals(new Integer[] {2}, graph.getReachableNodes(3).toArray());
        assertArrayEquals(new Integer[] {2}, graph.getReachableNodes(4).toArray());
    }
    
}
