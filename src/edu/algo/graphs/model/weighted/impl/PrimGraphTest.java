package edu.algo.graphs.model.weighted.impl;

import edu.algo.graphs.model.weighted.impl.PrimGraph.Edge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PrimGraphTest {

	@Test
	public void testMerge1() {
		PrimGraph g = new PrimGraph(new int[][] {{2,3}, {1}, {1}});
		assertEquals(3, g.size());

		PrimGraph r = g.merge(new Edge(1, 2));
		assertEquals(2, r.size());
		assertEquals(1, r.graph[0].length);
		assertEquals(2, r.graph[0][0]);

		assertEquals(1, r.graph[1].length);
		assertEquals(1, r.graph[1][0]);
	}

	@Test
	public void testMerge2() {
		PrimGraph g = new PrimGraph(new int[][] {{2,3,4}, {1,3,4}, {1,2,4}, {1,2,3}});
		assertEquals(4, g.size());

		PrimGraph r = g.merge(new Edge(2, 3));
		assertEquals(3, r.size());
		assertEquals(3, r.graph[0].length);
		assertEquals(2, r.graph[0][0]);
		assertEquals(2, r.graph[0][1]);
		assertEquals(3, r.graph[0][2]);

		assertEquals(4, r.graph[1].length);
		assertEquals(1, r.graph[1][0]);
		assertEquals(3, r.graph[1][1]);
		assertEquals(1, r.graph[1][2]);
		assertEquals(3, r.graph[1][3]);

		assertEquals(3, r.graph[2].length);
		assertEquals(1, r.graph[2][0]);
		assertEquals(2, r.graph[2][1]);
		assertEquals(2, r.graph[2][2]);
	}

	@Test
	public void testMerge3() {
		PrimGraph g = new PrimGraph(new int[][] {{2,3,4}, {1,3,4}, {1,2,4}, {1,2,3}});
		assertEquals(4, g.size());

		PrimGraph r = g.merge(new Edge(1, 2));
		assertEquals(3, r.size());

		assertEquals(4, r.graph[0].length);
		assertEquals(2, r.graph[0][0]);
		assertEquals(3, r.graph[0][1]);
		assertEquals(2, r.graph[0][2]);
		assertEquals(3, r.graph[0][3]);

		assertEquals(3, r.graph[1].length);
		assertEquals(1, r.graph[1][0]);
		assertEquals(1, r.graph[1][1]);
		assertEquals(3, r.graph[1][2]);

		assertEquals(3, r.graph[1].length);
		assertEquals(1, r.graph[2][0]);
		assertEquals(1, r.graph[2][1]);
		assertEquals(2, r.graph[2][2]);
	}

	@Test
	public void testMerge4() {
		PrimGraph g = new PrimGraph(new int[][] {{2,3,4}, {1,3,4}, {1,2,4}, {1,2,3}});
		assertEquals(4, g.size());

		PrimGraph r = g.merge(new Edge(3, 4));
		assertEquals(3, r.size());

		assertEquals(3, r.graph[0].length);
		assertEquals(2, r.graph[0][0]);
		assertEquals(3, r.graph[0][1]);
		assertEquals(3, r.graph[0][2]);

		assertEquals(3, r.graph[1].length);
		assertEquals(1, r.graph[1][0]);
		assertEquals(3, r.graph[1][1]);
		assertEquals(3, r.graph[1][2]);

		assertEquals(4, r.graph[2].length);
		assertEquals(1, r.graph[2][0]);
		assertEquals(2, r.graph[2][1]);
		assertEquals(1, r.graph[2][2]);
		assertEquals(2, r.graph[2][3]);
	}

	@Test
	public void testEdgNum1() {
		PrimGraph g = new PrimGraph(new int[][] {{2,3,4}, {1,3,4}, {1,2,4}, {1,2,3}});
		assertEquals(1, g.edgNum(1,2));
		assertEquals(1, g.edgNum(2,3));
	}

	@Test
	public void testEdgNum2() {
		PrimGraph g = new PrimGraph(new int[][] {{2,3,2,3}, {1,1,3}, {2,1,1},});
		assertEquals(2, g.edgNum(1,2));
		assertEquals(2, g.edgNum(2,1));
		assertEquals(1, g.edgNum(2,3));
		assertEquals(1, g.edgNum(3,2));
		assertEquals(2, g.edgNum(1,3));
		assertEquals(2, g.edgNum(3,1));
	}

//	@Test (expected = IllegalStateException.class)
//	public void testMergeNoSuchEdge() {
//		PrimGraph g = new PrimGraph(new int[][] {{2,3}, {1}, {1}});
//		g.merge(new Edge(2, 3));
//	}
}
