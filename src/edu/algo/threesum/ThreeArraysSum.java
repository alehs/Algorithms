package edu.algo.threesum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * {@link ThreeSum} applied to 3 arrays
 */
public class ThreeArraysSum {

	public static void main(String[] args) {
		int[] x = new int[] { -2, -1, 0, 2, 4 };
		int[] y = new int[] { -3, 0, 1, 2 };
		int[] z = new int[] { -1, 1, 2 };

		List<ThreeSum.Triple> result = sum3x3(x, y, z);
		//List<ThreeSum.Triple> result = sum3x3_merging(x, y, z);

		for (ThreeSum.Triple t : result) {
			System.out.println(t);
		}
	}

	private static List<ThreeSum.Triple> sum3x3_merging(int[] x, int[] y, int[] z) {
		int[] arr = new int[x.length + y.length + z.length];
		Map<Integer, Integer> xHash = IntStream.range(0, x.length - 1).boxed().collect(Collectors.toMap(i->x[i], Function.identity()));
		Map<Integer, Integer> yHash = IntStream.range(0, x.length - 1).boxed().collect(Collectors.toMap(i->x[i], Function.identity()));
		Map<Integer, Integer> zHash = IntStream.range(0, x.length - 1).boxed().collect(Collectors.toMap(i->x[i], Function.identity()));
		int curr = 0;
		for (int i=0; i<x.length; i++) {
			int adj = x[i] * 10;
			xHash.put(adj, x[i]);
			arr[curr++] = adj;
		}
		for (int i=0; i<y.length; i++) {
			int adj = y[i] * 10 + 1;
			yHash.put(adj, y[i]);
			arr[curr++] = adj;
		}
		for (int i=0; i<z.length; i++) {
			int adj = z[i] * 10 - 1;
			yHash.put(adj, z[i]);
			arr[curr++] = adj;
		}
		List<ThreeSum.Triple> result = ThreeSum.find3sum_hash(arr);
		List<ThreeSum.Triple> restored = new ArrayList<>(result.size());

		for (ThreeSum.Triple i : result) {
			Integer a = xHash.getOrDefault(i.a, yHash.getOrDefault(i.a, zHash.get(i.a)));
			Integer b = xHash.getOrDefault(i.b, yHash.getOrDefault(i.b, zHash.get(i.b)));
			Integer c = xHash.getOrDefault(i.c, yHash.getOrDefault(i.c, zHash.get(i.c)));
			restored.add(new ThreeSum.Triple(a, b, c));
		}
		return restored;
	}

	private static List<ThreeSum.Triple> sum3x3(int[] x, int[] y, int[] z) {
		Map<Integer, Integer> xHash = IntStream.range(0, x.length).boxed().collect(Collectors.toMap(i->x[i], Function.identity()));
		List<ThreeSum.Triple> result = new ArrayList<>();

		for (int i = 0; i < y.length; i++) {
			for (int j = 0; j < z.length; j++) {
				int adj = -(y[i] + z[j]);
				if (xHash.containsKey(adj)) {
					result.add(new ThreeSum.Triple(adj, y[i], z[j]));
				}
			}
		}
		return result;
	}
}
