package edu.algo.unionfind;

import java.util.Arrays;

/**
 *  Weighted quick-union
 *  Optimization of QuickUnion algo to avoid long trees
 *
 *  when linking parent elements to subtrees,
 *  chose as child a subtree of shorter length
 *  if (lengthPRoot > lengthQRoot) {
 *      id[qRoot] = pRoot
 *  } else {
 *      id[pRoot] = qRoot
 *  }
 *
 *
 *  Additional optimization: Path compression
 *  1) once found a root, make a new loop to set each examined node's parent to the root.
 *  2) during searching of the root node, make every other node in path point to its grandparent
 */
public class WeightedQuickUnion implements Union {
	int parent[];

	// tracks size of each tree under this element (root) to maintain balance during union operation
	int sz[];

	public WeightedQuickUnion(int N) {
		parent = new int[N];
		sz = new int[N];
		for (var i = 0; i<N; i++) {
			parent[i] = i;
			sz[i] = 1;
		}
	}

	public static QuickUnion init(int N) {
		return new QuickUnion(N);
	}

	@Override
	public void union(int p, int q) {
		int pRoot = root(p);
		int qRoot = root(q);
		if (pRoot == qRoot) {
			return;
		}
		if (sz[pRoot] < sz[qRoot]) {
			parent[pRoot] = qRoot;
			sz[qRoot] += sz[pRoot];
		} else {
			parent[qRoot] = pRoot;
			sz[pRoot] += sz[qRoot];
		}
	}

	@Override
	public boolean connection(int p, int q) {
		int pRoot = root(p);
		int qRoot = root(q);
		return pRoot == qRoot;
	}

	private int root(int i) {
		while (i != parent[i]) {
			// path compression options:
			// making every other node pointing to its grandparent
			parent[i] = parent[parent[i]];
			i = parent[i];
		}
		return i;
	}

	@Override
	public String toString() {
		return Arrays.toString(parent);
	}
}
