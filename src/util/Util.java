package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Util {
    public static String fileToString(String path) {
        String retval = "";
        try {
            retval = Files.readString(Path.of(path));
        } catch (IOException e) {
            System.err.println("Could not read the file: " + path);
        }
        return retval;
    }

    public static String[] fileToArray(String path, String delimiter) {
        return fileToString(path).split(delimiter);
    }

    public static int[][] toIntMatrix(String[] input) {
        int[][] retval = new int[input.length][input[0].length()];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length(); j++) {
                retval[i][j] = Character.getNumericValue(input[i].charAt(j));
            }
        }
        return retval;
    }
}
