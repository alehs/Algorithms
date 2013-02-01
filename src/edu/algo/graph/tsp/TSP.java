package edu.algo.graph.tsp;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import edu.algo.Utils;

/**
 * DP Algorithm for TSP.
 */
public class TSP {

	/**
	 * First index denotes encoded subsets S <= {1,2...n} that contains 1 and all destinations.
	 * Subset are encoded in bits, start vertex is excluded. I.e.:
	 * S = 0 = 0000 - equivalent to S{1}.
	 * S = 1 = 0001 - equivalent to S{1,2}.
	 * S = 5 = 0101 - equivalent to S{1,2,4}.
	 * S = 8 = 1000 - equivalent to S{1,5}.
	 * 
	 * Second index denotes final destinations. {1,2... n}
	 */
	public double[][] A;
	public int n;
	public int B;
	//public BitSet[] bs;// = new BitSet(25)[];

	public TSP(int size) {
		this.n = size;
		this.B = (int)Math.pow(2, (size-1)) - 1; // warning! possible data loose.
		Utils.print("using bitset of size " + this.B);

		this.A = new double[B+1][n];
		//base case
		for (int i = 0; i < B; i++) {
			A[i][0] = Integer.MAX_VALUE; //+inf 
		}
		A[0][0] = 0;
		Utils.print("done base case.");
	}

	public static void main(String[] args) throws FileNotFoundException {
		TspMap data = TspMapLoader.loadTSP("data/tsp.txt");

		long startTime = System.currentTimeMillis();

		TSP tsp = new TSP(data.size());
		Utils.print("Minimum cost = " + tsp.execute(data));

		Utils.print("Finished in " + (System.currentTimeMillis() - startTime)/1000 + "sec");
	}

	public double execute(TspMap data) {
		// subproblem size from 2 to n (actual start from 1 as indexing begins from 0).
		for (int m = 1; m < n; m++) {

			// over all sets S of size n (that contains start point 1)
			List<Integer> mSets = getSetsOfCoordinality(m, this.B);
			for (int set : mSets) {

				// over all destination vertices of S (excluding start)
				List<Integer> dists = indexesFromSet(set);

				for (int j : dists) {
					A[set][j] = minPath(set, j, data);
				}
			}
		}

		Utils.print("done recursion.");
		double minCost = Integer.MAX_VALUE;
		for (int j = 1; j<n; j++ ) {
			double cost = A[B][j] + data.getDistance(j, 0);
			if (cost < minCost) {
				minCost = cost;
			}
		}
		return minCost;
	}

	private double minPath(int set, int j, TspMap data) {

		int sj = minusVertex(set, j-1);
		double min = Integer.MAX_VALUE;

		List<Integer> dists = indexesFromSet(set);
		dists.add(0); // include start vertex here
		for (int k : dists) {
			if (k != j && A[sj][k] < Integer.MAX_VALUE) {
				double dist = A[sj][k] + data.getDistance(k, j);
				if (dist < min) {
					min = dist;
				}
			}
		}

		return min;
	}

	/**
	 * Excludes vertex from the given set.
	 * @return set fith vertex excluded.
	 */
	static int minusVertex(int set, int vertex) {
		return set ^ 1 << vertex;
	}

	/**
	 * Returns numbers of all destination vertices in the given set. 
	 * @param set
	 * @return
	 */
	static List<Integer> indexesFromSet(int set) {
		List<Integer> bits = new ArrayList<>();

		int num = set;
		while (num != 0) {
			int bitNumber = toBitNum(Integer.lowestOneBit(num));
			bits.add(bitNumber + 1); // add +1 as start vertex is excluded from set.
			num = minusVertex(num, bitNumber);
		}

		return bits;
	}

	private static int toBitNum(int num) {
		if (num == 1) {
			return 0;
		}
		int count = 0;
		while (num > 1) {
			num = num / 2;
			count ++;
		}
		return count;
	}

	static List<Integer> getSetsOfCoordinality(int coord, int max) {
		List<Integer> result = new ArrayList<>();
		for (int i = 1; i <= max; i++) {
			if (coord == numOfSetBits(i)) {
				result.add(i);
			}
		}
		return result;
	}

	static int numOfSetBits(int i) {
	    i = i - ((i >> 1) & 0x55555555);
	    i = (i & 0x33333333) + ((i >> 2) & 0x33333333);
	    return (((i + (i >> 4)) & 0x0F0F0F0F) * 0x01010101) >> 24;
	}

}
