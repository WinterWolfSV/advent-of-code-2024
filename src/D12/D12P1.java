package D12;

import util.Util;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

public class D12P1 {
    public static void main(String[] args) {
        List<Point> found = new ArrayList<>();
        List<List<Point>> regions = new ArrayList<>();
        String[] data = Util.fileToArray("src/D12/input.txt", "\n");

        for (int y = 0; y < data.length; y++) {
            for (int x = 0; x < data[0].length(); x++) {
                Point p = new Point(x, y);
                if (found.contains(p)) continue;
                List<Point> region = floodFillIsh(data, p);
                found.addAll(region);
                regions.add(region);
            }
        }

        int tot = 0;

        for (List<Point> region : regions) {
            int fenceCost = region.stream().mapToInt(p -> getFenceCost(region, p)).sum();
            tot += region.size() * fenceCost;
        }
        System.out.println(tot);
    }

    public static List<Point> floodFillIsh(String[] map, Point point) {
        Set<Point> visited = new HashSet<>();
        Deque<Point> queue = new ArrayDeque<>();
        List<Point> matches = new ArrayList<>();
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
                new Point(p.x + (p.x == 0 ? 0 : -1), p.y),
                new Point(p.x, p.y + (p.y == maxY ? 0 : 1)),
                new Point(p.x, p.y + (p.y == 0 ? 0 : -1)));
    }

    public static int getFenceCost(List<Point> points, Point p) {
        int x = p.x;
        int y = p.y;
        return (int) Stream.of(
                new Point(x + 1, y),
                new Point(x - 1, y),
                new Point(x, y + 1),
                new Point(x, y - 1)
        ).filter(po -> !points.contains(po)).count();
    }


}
