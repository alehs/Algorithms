package edu.algo.c2sat;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import edu.algo.Utils;


/**
 * 2-SAT Papadimitriou's Algorithm.
 */
public class Papadimitriu {

	public static Random random = new Random();

	public static void main(String[] args) throws FileNotFoundException {
		Sat sat = SatLoader.load("data/2sat6.txt", true);

		if (sat.size() == 0) {
			Utils.print("Preprocessing step removed all data. Disabling it...");
			sat = SatLoader.load("data/2sat-test1.txt", false);
		}

		long startTime = System.currentTimeMillis();
		Map<Integer, Boolean> solution = execute(sat);

		if (solution.isEmpty()) {
			Utils.print("\nFailed to find solution.");
		} else {
			Utils.print("\nSolution is " + solution.toString());
		}

		Utils.print("Finished in " + (System.currentTimeMillis() - startTime) + "ms");
	}

	public static Map<Integer, Boolean> execute(Sat sat) {
		Utils.append("Start processing for size " + sat.size());

		long n = sat.size();
		long logn = (long) (Math.log(n) / Math.log(2)) + 1;
		long n2 = 2 * n * n;

		if (n2 < 0) {
			throw new IllegalArgumentException("Type overflow, size too big");
		}

		for (int i = 0; i < logn; i++) {
			Utils.append("\rattempt " + i);

			Map<Integer, Boolean> solution = randomSolution(sat);
			//Utils.print("New solution is " + solution);

			for (int j = 0; j < n2; j++) {
				int f = fits(sat, solution);
				if (f == -1) {
					return solution;
				} else {
					modify(solution, sat, f);
				}
			}
		}
		return Collections.emptyMap();
	}

	/**
	 * Randomly flips value of one of a variables of the given unsatisfied solution.
	 * @param solution
	 * @param sat
	 * @param index
	 */
	private static void modify(Map<Integer, Boolean> solution, Sat sat, int index) {
		Clause failed = sat.clauses.get(index);
		if (getRandomBoolean()) {
			swap(solution, failed.getIndexX());
		} else {
			swap(solution, failed.getIndexY());
		}
		//Utils.print("Modified solution is " + solution);
	}

	public static void swap(Map<Integer, Boolean> solution, Integer var) {
		Boolean val = solution.get(var);
		solution.put(var, !val);
	}

	/**
	 * Checks if current solution fits the given problem.
	 * @return <code>-1</code> if fits, or number of failed clause; 
	 */
	private static int fits(Sat sat, Map<Integer, Boolean> solution) {
		int failed = -1;
		int index = 0;
		for (Clause cl : sat.clauses) {
			if (cl.check(solution.get(cl.getIndexX()), solution.get(cl.getIndexY()))) {
				index ++;
			} else {
				failed = index;
				break;
			}
		}

		return failed;
	}

	private static Map<Integer, Boolean> randomSolution(Sat sat) {
		Map<Integer, Boolean> solution = new HashMap<Integer, Boolean>(sat.size());
		for (Integer var : sat.variables) {
			solution.put(var, getRandomBoolean());
		}
		return solution;
	}

	private static Boolean getRandomBoolean() {
		return random.nextBoolean();
	}
}
