package edu.algo.graphs.clustering;
import edu.algo.graphs.model.weighted.Edge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ClusterTest {

    @Test
    public void testInit() {
        /**
         *    1 2 3 4__
         * 1 |0 1 0 0
         * 2 |1 0 2 3
         * 3 |0 2 0 0 
         * 4 |0 3 0 0
         */
        Cluster cl = new Cluster(new int[][] {{0,1,0,0},{1,0,2,3},{0,2,0,0},{0,3,0,0}});

        assertEquals(3, cl.edges.size());
        assertEquals(1, cl.edges.get(0).weight);
        assertEquals(2, cl.edges.get(1).weight);
        assertEquals(3, cl.edges.get(2).weight);

        assertEquals(4, cl.leaders.size());
        assertEquals(1, cl.leaders.get(0).getId());
        assertEquals(1, cl.leaders.get(0).getSize());
        assertEquals(Integer.valueOf(1), cl.leaders.get(0).getNodes().get(0));

        assertEquals(4, cl.lmap.size());
        assertEquals(cl.leaders.get(0), cl.lmap.get(Integer.valueOf(1)));
    }

    @Test
    public void testMerge() {
        /**
         *    1 2 3 4__
         * 1 |0 1 0 0
         * 2 |1 0 2 3
         * 3 |0 2 0 0 
         * 4 |0 3 0 0
         */
        Cluster cl = new Cluster(new int[][] {{0,1,0,0},{1,0,2,3},{0,2,0,0},{0,3,0,0}});

        cl.merge(cl.leaders.get(0), cl.leaders.get(1));

        assertEquals(3, cl.leaders.size());
        assertEquals(cl.getLeader(1), cl.getLeader(2));
    }

    @Test
    public void testGetMinSplitEdge() {
        /**
         *    1 2 3 4__
         * 1 |0 1 0 0
         * 2 |1 0 2 3
         * 3 |0 2 0 0 
         * 4 |0 3 0 0
         */
        Cluster cl = new Cluster(new int[][] {{0,1,0,0},{1,0,2,3},{0,2,0,0},{0,3,0,0}});
        Edge edge = cl.nextMinSeparateEdge();
        assertEquals(1, edge.weight);

        edge = cl.nextMinSeparateEdge();
        assertEquals(2, edge.weight);

        edge = cl.nextMinSeparateEdge();
        assertEquals(3, edge.weight);

        edge = cl.nextMinSeparateEdge();
        assertNull(edge);
    }

    @Test
    public void testGetMinSplitEdge_Nulls() {
        /**
         *    1 2 3 4__
         * 1 |0 1 0 0
         * 2 |1 0 2 3
         * 3 |0 2 0 0 
         * 4 |0 3 0 0
         */
        Cluster cl = new Cluster(new int[][] {{0,1,0,0},{1,0,2,3},{0,2,0,0},{0,3,0,0}});

        Edge edge = cl.nextMinSeparateEdge();
        assertEquals(1, edge.weight);

        edge = cl.nextMinSeparateEdge();
        assertEquals(2, edge.weight);

        edge = cl.nextMinSeparateEdge();
        assertEquals(3, edge.weight);

        edge = cl.nextMinSeparateEdge();
        assertNull(edge);
    }

    @Test
    public void testGetMinSplitEdge2() {
        /**
         *    1 2 3 4__
         * 1 |0 1 0 0
         * 2 |1 0 2 3
         * 3 |0 2 0 0 
         * 4 |0 3 0 0
         */
        Cluster cl = new Cluster(new int[][] {{0,1,0,0},{1,0,2,3},{0,2,0,0},{0,3,0,0}});

        cl.merge(cl.getLeader(1), cl.getLeader(2));
        cl.merge(cl.getLeader(3), cl.getLeader(4));

        Edge edge = cl.nextMinSeparateEdge();
        assertEquals(2, edge.weight);

        cl.merge(cl.getLeader(1), cl.getLeader(3));
        edge = cl.nextMinSeparateEdge();
        assertNull(edge);
    }
}
