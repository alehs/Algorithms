package edu.algo.unionfind;

/**
 * Eager algorithm to the Union Find problem (V pov(2) time)
 *
 * - int array of size N initialized with its index
 * - p and q are connected if hte have the same id
 *
 * to merge components containing p and q
 * change all entries whose id equal id[p] to id[q]
 *
 */
public class QuickFind implements Union {

	int[] id;

	private QuickFind(int N) {
		id = new int[N];
		for (var i = 0; i<N; i++) {
			id[i] = i;
		}
	}

	public static QuickFind init(int N) {
		return new QuickFind(N);
	}

	@Override
	public void union(int p, int q) {
		int pid = id[p];
		int qid = id[q];
		for (int i = 0; i<id.length; i++) {
			if (id[i] == pid) {
				id[i] = qid;
			}
		}
	}

	@Override
	public boolean connection(int p, int q) {
		return id[p] == id[q];
	}
}
