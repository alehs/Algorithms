package edu.algo.graph.impl;

import junit.framework.Assert;
import edu.algo.graph.impl.PrimGraph.Edge;

import org.junit.Test;

public class PrimGraphTest {

	@Test
	public void testMerge1() {
		PrimGraph g = new PrimGraph(new int[][] {{2,3}, {1}, {1}});
		Assert.assertEquals(3, g.size());

		PrimGraph r = g.merge(new Edge(1, 2));
		Assert.assertEquals(2, r.size());
		Assert.assertEquals(1, r.graph[0].length);
		Assert.assertEquals(2, r.graph[0][0]);

		Assert.assertEquals(1, r.graph[1].length);
		Assert.assertEquals(1, r.graph[1][0]);
	}

	@Test
	public void testMerge2() {
		PrimGraph g = new PrimGraph(new int[][] {{2,3,4}, {1,3,4}, {1,2,4}, {1,2,3}});
		Assert.assertEquals(4, g.size());

		PrimGraph r = g.merge(new Edge(2, 3));
		Assert.assertEquals(3, r.size());
		Assert.assertEquals(3, r.graph[0].length);
		Assert.assertEquals(2, r.graph[0][0]);
		Assert.assertEquals(2, r.graph[0][1]);
		Assert.assertEquals(3, r.graph[0][2]);

		Assert.assertEquals(4, r.graph[1].length);
		Assert.assertEquals(1, r.graph[1][0]);
		Assert.assertEquals(3, r.graph[1][1]);
		Assert.assertEquals(1, r.graph[1][2]);
		Assert.assertEquals(3, r.graph[1][3]);

		Assert.assertEquals(3, r.graph[2].length);
		Assert.assertEquals(1, r.graph[2][0]);
		Assert.assertEquals(2, r.graph[2][1]);
		Assert.assertEquals(2, r.graph[2][2]);
	}

	@Test
	public void testMerge3() {
		PrimGraph g = new PrimGraph(new int[][] {{2,3,4}, {1,3,4}, {1,2,4}, {1,2,3}});
		Assert.assertEquals(4, g.size());

		PrimGraph r = g.merge(new Edge(1, 2));
		Assert.assertEquals(3, r.size());

		Assert.assertEquals(4, r.graph[0].length);
		Assert.assertEquals(2, r.graph[0][0]);
		Assert.assertEquals(3, r.graph[0][1]);
		Assert.assertEquals(2, r.graph[0][2]);
		Assert.assertEquals(3, r.graph[0][3]);

		Assert.assertEquals(3, r.graph[1].length);
		Assert.assertEquals(1, r.graph[1][0]);
		Assert.assertEquals(1, r.graph[1][1]);
		Assert.assertEquals(3, r.graph[1][2]);

		Assert.assertEquals(3, r.graph[1].length);
		Assert.assertEquals(1, r.graph[2][0]);
		Assert.assertEquals(1, r.graph[2][1]);
		Assert.assertEquals(2, r.graph[2][2]);
	}

	@Test
	public void testMerge4() {
		PrimGraph g = new PrimGraph(new int[][] {{2,3,4}, {1,3,4}, {1,2,4}, {1,2,3}});
		Assert.assertEquals(4, g.size());

		PrimGraph r = g.merge(new Edge(3, 4));
		Assert.assertEquals(3, r.size());

		Assert.assertEquals(3, r.graph[0].length);
		Assert.assertEquals(2, r.graph[0][0]);
		Assert.assertEquals(3, r.graph[0][1]);
		Assert.assertEquals(3, r.graph[0][2]);

		Assert.assertEquals(3, r.graph[1].length);
		Assert.assertEquals(1, r.graph[1][0]);
		Assert.assertEquals(3, r.graph[1][1]);
		Assert.assertEquals(3, r.graph[1][2]);

		Assert.assertEquals(4, r.graph[2].length);
		Assert.assertEquals(1, r.graph[2][0]);
		Assert.assertEquals(2, r.graph[2][1]);
		Assert.assertEquals(1, r.graph[2][2]);
		Assert.assertEquals(2, r.graph[2][3]);
	}

	@Test
	public void testEdgNum1() {
		PrimGraph g = new PrimGraph(new int[][] {{2,3,4}, {1,3,4}, {1,2,4}, {1,2,3}});
		Assert.assertEquals(1, g.edgNum(1,2));
		Assert.assertEquals(1, g.edgNum(2,3));
	}

	@Test
	public void testEdgNum2() {
		PrimGraph g = new PrimGraph(new int[][] {{2,3,2,3}, {1,1,3}, {2,1,1},});
		Assert.assertEquals(2, g.edgNum(1,2));
		Assert.assertEquals(2, g.edgNum(2,1));
		Assert.assertEquals(1, g.edgNum(2,3));
		Assert.assertEquals(1, g.edgNum(3,2));
		Assert.assertEquals(2, g.edgNum(1,3));
		Assert.assertEquals(2, g.edgNum(3,1));
	}

	@Test (expected = IllegalStateException.class)
	public void testMergeNoSuchEdge() {
		PrimGraph g = new PrimGraph(new int[][] {{2,3}, {1}, {1}});
		g.merge(new Edge(2, 3));
	}
}
