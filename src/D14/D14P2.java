package D14;

import util.Util;

import java.awt.*;
import java.io.IOException;
import java.util.*;

public class D14P2 {
    public static void main(String[] args) {
        int moves = 0; // First occurrence at 6512
        int sizeX = 101;
        int sizeY = 103;
        String[] data = Util.fileToArray("src/D14/input.txt", "\n");
        while (true) {
            Set<Point> coords = new HashSet<>();
            moves++;
            for (String s : data) {
                String[] a = s.split("[ ,=]");
                Point sP = new Point(Integer.parseInt(a[1]), Integer.parseInt(a[2]));
                Point sV = new Point(Integer.parseInt(a[4]), Integer.parseInt(a[5]));
                Point nP = moveRobot(sP, sV, sizeX, sizeY, moves);
                coords.add(nP);
            }
            if (couldContainTree(coords)) {
                printRobots(coords, sizeX, sizeY);
                System.out.println(moves); // If the displayed image is a tree,
                try {
                    System.in.read(); // Waiting for the user to continue the program

                } catch (IOException i) {
                    System.out.println("Error handling 101");
                }
            }
        }
    }

    public static boolean couldContainTree(Set<Point> coords) {
        HashMap<Integer, Integer> rows = new HashMap<>();
        for (Point p : coords) {
            rows.put(p.y, rows.getOrDefault(p.y, 0) + 1);
        }

        return rows.containsValue(32);
    }

    public static void printRobots(Set<Point> coords, int maxX, int maxY) {
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                System.out.print(coords.contains(new Point(x, y)) ? "X" : " ");
            }
            System.out.println();
        }
    }

    public static Point moveRobot(Point sP, Point sV, int boardSizeX, int boardSizeY, int moves) {
        if (sV.x < 0) {
            sV.translate(boardSizeX, 0);
        }
        if (sV.y < 0) {
            sV.translate(0, boardSizeY);
        }
        int nX = ((sP.x + sV.x * moves)) % boardSizeX;
        int nY = ((sP.y + sV.y * moves)) % boardSizeY;
        return new Point(nX, nY);
    }
}
