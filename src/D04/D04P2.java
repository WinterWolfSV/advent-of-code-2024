package D04;

import util.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class D04P2 {
    public static void main(String[] a) {
        String data = Util.fileToString("src/D04/input.txt");
        String[] splitData = data.split("\n");
        int rows = splitData.length;
        int cols = splitData[0].length();
        int tot = 0;
        for (int row = 1; row < rows - 1; row++) {
            for (int col = 1; col < cols - 1; col++) {
                if (isXMas(splitData, row, col)) {
                    tot++;
                }
            }
        }
        System.out.println(tot);
    }

    public static boolean isXMas(String[] grid, int row, int col) {
        try {
            String[] smallGrid = {grid[row - 1].substring(col - 1, col + 2), grid[row].substring(col - 1, col + 2), grid[row + 1].substring(col - 1, col + 2)};
            Pattern pattern = Pattern.compile("(?=M.{3}A.{3}S|S.{3}A.{3}M)(?=.{2}M.A.S|.{2}S.A.M).");
            Matcher m = pattern.matcher(String.join("", smallGrid));
            return m.find();
        } catch (Exception e) {
            return false;
        }
    }
}