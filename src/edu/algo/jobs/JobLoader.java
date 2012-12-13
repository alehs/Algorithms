package edu.algo.jobs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JobLoader {

    protected static List<Job> load(String fileName) throws IOException {
        File file = new File(fileName);
        List<Job> list = null;
        Scanner scanner = new Scanner(file);
        try {
            String totalStr = scanner.nextLine();
            int total = Integer.valueOf(totalStr);

            list = new ArrayList<Job>(total);

            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\s");
                Job j = new Job(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]));
                list.add(j);
            }

        } finally {
           scanner.close();
        }
        return list;
     }

    
}
