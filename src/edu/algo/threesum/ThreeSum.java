package edu.algo.threesum;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given N distinct integer, how many triples sum to exactly zero?
 *
 * solutions:
 * 1) N2 log N - sort array, for each pair a[i] + a[j] - binary search for -(a[i] + a[j])
 * 2) N2 using hash - hash put a[i] = a[i], for each pair a[i] + a[j], search for hash(a[i]) = -(a[i] + a[j])
 * 3) N2 for sorted array limit start and end, if (a+b+c) !=0 -> if (a + b + c) > 0 -> end--; else start ++
 *
 */
public class ThreeSum {

	public static void main(String[] args) {
		int[] input = new int[] { 30, -40, -20, -10, 40, 0, 10, 5};
		//List<Triple> result = find3sum_bruteForce(input);
		//List<Triple> result = find3sum_hash(input);
		List<Triple> result = find3sum_sorted(input);
		for (Triple t : result) {
			System.out.println(t);
		}
	}


	public static class Triple {
		Triple(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
		int a, b, c = 0;

		@Override
		public String toString() {
			return a + "," + b + "," + c;
		}
	}

	/**
	 * Once we have sorted array, we can assume that
	 * if sum is bigger than 0,
	 *  - then all numbers after it will add up even more
	 *  - and if sum is less than 0, then all numbers below will add up less
	 *
	 */
	public static List<Triple> find3sum_sorted(int[] input) {
		// -2, -1, 0, 1, 2, 4
		Arrays.sort(input);
		List<Triple> result = new ArrayList<>();

		for (int i = 0; i<input.length; i++) {
			int start = i + 1;
			int end = input.length - 1;
			while (start < end) {
				int sum = input[i] + input[start] + input[end];
				if (sum == 0) {
					result.add(new Triple(input[i], input[start], input[end]));
					start = start + 1;
					end = end - 1;
				} else if (sum > 0) {
					end = end - 1;
				} else {
					start = start + 1;
				}
			}
		}

		return result;
	}

	/**
	 * Having a hash of arr[i] -> i,
	 * for each pair of a[i] and a[j] if hash contains -(a[i]+[j]) -> triple
	 * @param input
	 * @return
	 */
	public static List<Triple> find3sum_hash(int[] input) {
		List<Triple> result = new ArrayList<>();
		Map<Integer, Integer> map = IntStream.range(0, input.length - 1).boxed().collect(Collectors.toMap(i->input[i], Function.identity()));
				Arrays.stream(input).boxed().collect(Collectors.toMap(k->k, v->v));
		for (int i = 0; i<input.length; i++) {
			for (var j = i + 1; j < input.length; j++) {
				int adj = - (input[i] + input[j]);
				Integer kIndex = map.get(adj);
				if (kIndex != null && kIndex > i && kIndex > j) {  // to avoid including numbers 2 times only consider hash with index greater (or less) then both i and j
					result.add(new Triple(adj, input[i], input[j]));
				}
			}
		}
		return result;
	}

	public static List<Triple> find3sum_bruteForce(int[] input) {
		List<Triple> result = new ArrayList<>();
		for (int i = 0; i < input.length; i++) {
			for (int j = i + 1; j < input.length; j++) {
				for (int k = j + 1; k < input.length; k++) {
					if ((input[i] + input[j] + input[k]) == 0) {
						result.add(new Triple(input[i], input[j], input[k]));
					}
				}
			}
		}
		return result;
	}

}
