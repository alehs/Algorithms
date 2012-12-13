package edu.algo;

import java.util.ArrayList;
import java.util.List;

public final class ArrayUtils {

	public static boolean in(int[] arr, int num) {
		for (int i : arr) {
			if (i == num) {
				return true;
			}
		}
		return false;
	}

    public static int[][] fromList(List<List<Integer>> list) {
        int[][] result = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            result[i] = new int[list.get(i).size()];

            List<Integer> edgesList = list.get(i);
            int j = 0;
            for (int k : edgesList) {
                result[i][j++] = k;
            }
        }

        return result;
    }

    public static int[] fromList(ArrayList<Integer> list) {
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

}