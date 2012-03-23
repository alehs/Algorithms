package edu.hw1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Inversions {

   public static void main(String[] args) throws IOException {
      List<Integer> list = load("data/IntegerArray.txt");
   }

   protected static List<Integer> load(String fileName) throws IOException {
      File file = new File(fileName);
      BufferedReader reader = new BufferedReader(new FileReader(file));
      List<Integer> list = new ArrayList<Integer>(10000);
      String line = reader.readLine();
      while (line != null) {
         list.add(Integer.valueOf(line));
         line = reader.readLine();
      }
      return list;
   }

}
