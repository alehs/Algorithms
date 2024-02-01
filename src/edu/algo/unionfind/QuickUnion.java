package edu.algo.unionfind;

import java.util.Arrays;

/**
 * Lazy approach to UnionFind problem
 *
 * - int array of size N
 * - id[i] is parent of i
 * - root of i is id[id[id[...id[i]...]]]
 *
 * components are connected if they have the same root.
 *
 */
public class QuickUnion implements Union {

	int id[];

	public QuickUnion(int N) {
		id = new int[N];
		for (var i = 0; i<N; i++) {
			id[i] = i;
		}
	}

	public static QuickUnion init(int N) {
		return new QuickUnion(N);
	}

	@Override
	public void union(int p, int q) {
		int pRoot = root(p);
		int qRoot = root(q);
		id[pRoot] = qRoot;
	}

	@Override
	public boolean connection(int p, int q) {
		int pRoot = root(p);
		int qRoot = root(q);
		return pRoot == qRoot;
	}

	private int root(int i) {
		while (i != id[i]) {
			i = id[i];
		}
		return i;
	}

 	@Override
	public String toString() {
		return Arrays.toString(id);
	}
}
