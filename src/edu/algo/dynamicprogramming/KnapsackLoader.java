package edu.algo.dynamicprogramming;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.algo.Utils;

public class KnapsackLoader {

	File file;
	private int capacity;

	public KnapsackLoader(String path) {
		this.file = new File(path);
	}

	public int getCapacity() {
		return capacity;
	}

	public List<Item> load() throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
        try {
            String totalStr = scanner.nextLine();
            String totals[] = totalStr.split("\\s");
            this.capacity = Integer.valueOf(totals[0]);
            int totalItems = Integer.valueOf(totals[1]);

            List<Item> items = new ArrayList<Item>(totalItems);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String lines[] = line.split("\\s");
                items.add(new Item(Integer.valueOf(lines[0]), Integer.valueOf(lines[1])));
            }

            Utils.printf("File scanning completed, # of items = %d, capasity = %d\n", items.size(), capacity);
            return items;
        } finally {
            scanner.close();
        }
    }
}
