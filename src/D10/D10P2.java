package D10;

import util.Util;

import java.awt.*;
import java.util.*;
import java.util.List;

public class D10P2 {
    public static void main(String[] args) {
        int[][] map = Util.toIntMatrix(Util.fileToArray("src/D10/input.txt", "\n"));
        int tot = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 0) {
                    tot += checkPath(new Point(j,i),map).size();
                }
            }
        }
        System.out.println(tot);
    }

    static List<Point> directions = List.of(
            new Point(1, 0),
            new Point(-1, 0),
            new Point(0, 1),
            new Point(0, -1)
    );

    public static List<Point> checkPath(Point coord, int[][] map) {
        int pVal = map[coord.y][coord.x];
        int sizeX = map[0].length;
        int sizeY = map.length;
        List<Point> retval = new ArrayList<>();
        for (Point dir : directions) {
            int x = coord.x + dir.x;
            int y = coord.y + dir.y;
            if (x < 0 || x >= sizeX || y < 0 || y >= sizeY) continue;
            if (pVal + 1 == map[y][x]) {
                Point p = new Point(x, y);
                retval.addAll(pVal == 8 ? List.of(p) : checkPath(p, map));
            }
        }
        return retval;
    }
}
