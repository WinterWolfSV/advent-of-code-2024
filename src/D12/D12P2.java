package D12;

import util.Util;

import java.awt.*;
import java.util.*;
import java.util.List;

public class D12P2 {
    public static void main(String[] args) {
        List<Point> found = new ArrayList<>();
        List<Set<Point>> regions = new ArrayList<>();
        String[] data = Util.fileToArray("src/D12/input.txt", "\n"); // 824992 Too low

        for (int y = 0; y < data.length; y++) {
            for (int x = 0; x < data[0].length(); x++) {
                Point p = new Point(x, y);
                if (found.contains(p)) continue;
                Set<Point> region = floodFillIsh(data, p);
                found.addAll(region);
                regions.add(region);
            }
        }

        int tot = 0;

        for (Set<Point> region : regions) {
            int fenceCost = getFenceCost(region);
            tot += region.size() * fenceCost;
        }
        System.out.println(tot);
    }

    public static Set<Point> floodFillIsh(String[] map, Point point) {
        Set<Point> visited = new HashSet<>();
        Deque<Point> queue = new ArrayDeque<>();
        Set<Point> matches = new HashSet<>();
        queue.add(point);

        char target = map[point.y].charAt(point.x);
        int mapY = map.length - 1;
        int mapX = map[0].length() - 1;

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            if (!visited.add(p)) {
                continue;
            }
            if (map[p.y].charAt(p.x) == target) {
                matches.add(p);
                queue.addAll(getNeighbours(p, mapX, mapY));
            }
        }
        return matches;
    }

    public static List<Point> getNeighbours(Point p, int maxX, int maxY) {
        return Arrays.asList(
                new Point(p.x + (p.x == maxX ? 0 : 1), p.y),
                new Point(p.x, p.y + (p.y == maxY ? 0 : 1)),
                new Point(p.x + (p.x == 0 ? 0 : -1), p.y),
                new Point(p.x, p.y + (p.y == 0 ? 0 : -1)));
    }

    public static int getFenceCost(Set<Point> points) {
        List<doubleTuple> corners = new ArrayList<>();
        int retval = 0;
        for (Point p : points) {
            corners.add(new doubleTuple(p.x + 0.5, p.y + 0.5));
            corners.add(new doubleTuple(p.x + 0.5, p.y - 0.5));
            corners.add(new doubleTuple(p.x - 0.5, p.y - 0.5));
            corners.add(new doubleTuple(p.x - 0.5, p.y + 0.5));
            if (points.contains(new Point(p.x + 1, p.y + 1)) // Ugly check to see if there are any cases of inside corners that meet.
                    && !points.contains(new Point(p.x + 1, p.y))
                    && !points.contains(new Point(p.x, p.y + 1))
                    || points.contains(new Point(p.x + 1, p.y - 1))
                    && !points.contains(new Point(p.x + 1, p.y))
                    && !points.contains(new Point(p.x, p.y - 1))) {
                retval += 2;
            }
        }
        for (doubleTuple c : new HashSet<>(corners)) {
            int freq = Collections.frequency(corners, c);
            if (freq % 2 == 1) { // If there are an odd number of points touching, it means that it is a corner.
                retval++;
            }
        }
        return retval;
    }

    public record doubleTuple(double a, double b) {
    }
}
