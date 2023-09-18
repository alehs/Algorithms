package edu.algo.graphs.minimumspanningtree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.algo.graphs.model.weighted.Edge;
import edu.algo.graphs.model.weighted.Graph;
import edu.algo.graphs.model.weighted.impl.MatrixGraph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrimMstTest {

    @Test
    public void testFindCheapest() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2, 0));
        edges.add(new Edge(1, 3, 10));
        edges.add(new Edge(2, 3, -10));

        Edge check = PrimMst.findCheapest(edges);
        assertEquals(-10, check.weight);
    }

    @Test
    public void testSelectUnexploredEdges() {

        /**
         *    1 2 3__
         * 1 |0 5 3
         * 2 |5 0 4
         * 3 |3 4 0
         */
        Graph graph = new MatrixGraph(new int[][] {{0,5,3},{5,0,4},{3,4,0}});

        List<Edge> edges = PrimMst.selectUnexploredEdges(graph, 1, new ArrayList<Integer>());
        assertEquals(2, edges.size());
        assertEquals(1, edges.get(0).vertex1);
        assertEquals(2, edges.get(0).vertex2);
        assertEquals(5, edges.get(0).weight);

        assertEquals(1, edges.get(1).vertex1);
        assertEquals(3, edges.get(1).vertex2);
        assertEquals(3, edges.get(1).weight);

        edges = PrimMst.selectUnexploredEdges(graph, 1, Arrays.asList(new Integer[] {3}));
        assertEquals(1, edges.size());
        assertEquals(1, edges.get(0).vertex1);
        assertEquals(2, edges.get(0).vertex2);
        assertEquals(5, edges.get(0).weight);

        edges = PrimMst.selectUnexploredEdges(graph, 2, Arrays.asList(new Integer[] {3}));
        assertEquals(1, edges.size());
        assertEquals(2, edges.get(0).vertex1);
        assertEquals(1, edges.get(0).vertex2);
        assertEquals(5, edges.get(0).weight);

        edges = PrimMst.selectUnexploredEdges(graph, 2, Arrays.asList(new Integer[] {2,3}));
        assertEquals(1, edges.size());
        assertEquals(2, edges.get(0).vertex1);
        assertEquals(1, edges.get(0).vertex2);
        assertEquals(5, edges.get(0).weight);

        edges = PrimMst.selectUnexploredEdges(graph, 2, Arrays.asList(new Integer[] {1, 2,3}));
        assertEquals(0, edges.size());
    }

    @Test
    public void testFindCheapestEdge() {
        /**
         *    1 2 3__
         * 1 |0 5 3
         * 2 |5 0 4
         * 3 |3 4 0
         */
        Graph graph = new MatrixGraph(new int[][] {{0,5,3},{5,0,4},{3,4,0}});
        Edge e = PrimMst.findCheapestEdge(graph, Arrays.asList(new Integer[] {2}));

        assertEquals(2, e.vertex1);
        assertEquals(3, e.vertex2);
        assertEquals(4, e.weight);

        e = PrimMst.findCheapestEdge(graph, Arrays.asList(new Integer[] {2,3}));
        assertEquals(3, e.vertex1);
        assertEquals(1, e.vertex2);
        assertEquals(3, e.weight);
        
        e = PrimMst.findCheapestEdge(graph, Arrays.asList(new Integer[] {2,3, 1}));
        assertEquals(null, e);
    }

}
