package edu.algo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public final class Utils {

    private Utils() {};

    public static void append(String str) {
        System.out.print(str);
    }

    public static void print(String str) {
        System.out.println(str);
    }

    public static void print(Number str) {
        System.out.println(str);
    }

    public static void print(String str, String... strings) {
        StringBuilder sb = new StringBuilder(str);
        for (String string : strings) {
            sb.append(", ").append(string);
        }
        print(sb.toString());
    }

    public static void printf(String pattern, Object... args) {
        System.out.printf(pattern, args);
    }

    public static Scanner openFile(String path) throws FileNotFoundException {
        File file = new File(path);
        return new Scanner(file);
    }
}
