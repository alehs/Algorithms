package edu.algo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class Loader {

	public static int[] loadArr(String fileName) {
		File f = new File(fileName);
		ArrayList<Integer> list = new ArrayList<Integer>();
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(f));
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				list.add(Integer.valueOf(line.trim()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(br);
		}
		int[] arr = ArrayUtils.fromList(list);
		return arr;
	}

	private static void close(Reader br) {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
