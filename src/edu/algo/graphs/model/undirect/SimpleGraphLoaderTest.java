package edu.algo.graphs.model.undirect;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleGraphLoaderTest {

	@Test
	public void testAdjacencyMatrixGraph() throws FileNotFoundException {
		Graph g = SimpleGraphLoader.loadAdjMatrixGraph("data/graph/simpleGraph.txt");
		assertEquals(g.vNum(), 13);
		assertEquals(List.of(1,2,5,6), g.adj(0));
		assertEquals(List.of(4,5), g.adj(3));
		assertEquals(List.of(10,11,12), g.adj(9));
	}

	@Test
	public void testAdjacencyListGraph() throws FileNotFoundException {
		Graph g = SimpleGraphLoader.loadAdjListGraph("data/graph/simpleGraph.txt");
		assertEquals(g.vNum(), 13);
		assertEquals(List.of(5,1,2,6), g.adj(0));
		assertEquals(List.of(4,5), g.adj(3));
		assertEquals(List.of(12,10,11), g.adj(9));
	}

	@Test
	public void testNodeGraph() throws FileNotFoundException {
		Graph g = SimpleGraphLoader.loadNodeGraph("data/graph/simpleGraph.txt");
		assertEquals(g.vNum(), 13);
		assertEquals(List.of(1, 5, 6, 2), g.adj(0));
		assertEquals(List.of(4, 5), g.adj(3));
		assertEquals(List.of(10, 11, 12), g.adj(9));
	}
}
