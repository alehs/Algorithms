package edu.algo.unionfind;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.function.Function;

import edu.algo.Utils;

public class UnionFindLoader {

	public static <T extends Union> T loadUnionFind(String path, Function<Integer, T> supplier) {
		T union = null;
		try (Scanner scanner = Utils.openFile(path)) {
			union = supplier.apply(Integer.valueOf(scanner.nextLine()));
			readUnionPairs(scanner, union);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return union;
	}

	private static void readUnionPairs(Scanner scanner, Union union) {
		while(scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] parts = line.split("\\s");
			union.union(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]));
		}
	}

}
