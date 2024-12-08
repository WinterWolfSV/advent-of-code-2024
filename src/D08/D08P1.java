package D08;

import util.Util;

import java.awt.*;
import java.util.*;
import java.util.List;

public class D08P1 {
    public static void main(String[] args) {
        String[] input = Util.fileToArray("src/D08/input.txt", "\n");
        HashMap<Character, ArrayList<Point>> antennas = new HashMap<>();
        int maxX = input[0].length();
        int maxY = input.length;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length(); j++) {
                char indexChar = input[i].charAt(j);
                if (indexChar == '.') continue;
                ArrayList<Point> coords = antennas.getOrDefault(indexChar, new ArrayList<>());
                coords.add(new Point(j, i));
                antennas.put(indexChar, coords);
            }
        }
        Set<Point> antinodes = new HashSet<>();
        for (char c : antennas.keySet()) {
            ArrayList<Point> points = antennas.get(c);
            for (Point p : points) {
                antinodes.addAll(getPointsForCoord(p, points.stream().filter(el -> !el.equals(p)).toList()));
            }
        }
        int tot = 0;
        for (Point node : antinodes) {
            int nX = node.x;
            int nY = node.y;
            if (nX < 0 || nX >= maxX || nY < 0 || nY >= maxY) {
                continue;
            }
            tot++;
        }
        System.out.println(tot);
    }

    public static ArrayList<Point> getPointsForCoord(Point point, List<Point> points) {
        ArrayList<Point> retval = new ArrayList<>();
        for (Point ps : points) {
            int dX = point.x - ps.x;
            int dY = point.y - ps.y;
            retval.add(new Point(point.x + dX, point.y + dY));
        }
        return retval;
    }
}
