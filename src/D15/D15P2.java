package D15;

import util.Util;

import java.awt.*;
import java.util.*;

public class D15P2 {
    static HashSet<Point> walls = new HashSet<>();
    static HashSet<Point> boxes = new HashSet<>();
    static Point robot = new Point();
    static Map<Character, int[]> dirs = Map.of(
            '^', new int[]{0, -1},
            'v', new int[]{0, 1},
            '<', new int[]{-1, 0},
            '>', new int[]{1, 0}
    );

    public static void main(String[] args) {
        String[] data = Util.fileToArray("src/D15/input.txt", "\n");
        String[] map = data[0].split("\n");

        for (int i = 0; i < map.length; i++) {
            String[] line = map[i].split("");
            for (int j = 0; j < line.length; j++) {
                switch (line[j]) {
                    case "#" -> {
                        walls.add(new Point(j * 2, i));
                        walls.add(new Point(j * 2 + 1, i));
                    }
                    case "O" -> boxes.add(new Point(j * 2, i));
                    case "@" -> robot.move(j * 2, i);
                }
            }
        }

        for (char c : data[1].replace("\n", "").toCharArray()) {
            moveBot(dirs.get(c));
        }

        long tot = 0;
        for (Point box : boxes) {
            tot += 100L * box.y + box.x;
        }
        System.out.println(tot);

    }

    static void moveBot(int[] dir) {
        int nX = dir[0] + robot.x;
        int nY = dir[1] + robot.y;
        Point nP = new Point(nX, nY);
        HashMap<Point, Point> queue = new HashMap<>();
        if (walls.contains(nP)) return;
        if (boxes.contains(nP)) {
            if (!moveBox(nP, dir, queue)) {
                return;
            }
        }
        if (boxes.contains(new Point(nX - 1, nY))) {
            if (!moveBox(new Point(nX - 1, nY), dir, queue)) {
                return;
            }
        }
        updateQueue(queue);
        robot = nP;
    }

    static void updateQueue(HashMap<Point, Point> q) {
        Queue<Point> queue = new ArrayDeque<>(q.keySet());
        while (!queue.isEmpty()) {
            Point init = queue.poll();
            Point target = q.get(init);
            if (boxes.contains(target)) {
                queue.add(init);
                continue;
            }
            boxes.remove(init);
            boxes.add(target);
        }
    }

    static boolean moveBox(Point box, int[] dir, HashMap<Point, Point> queue) {
        int nX = box.x + dir[0];
        int nY = box.y + dir[1];
        Point nBl = new Point(nX, nY);
        Point nBr = new Point(nX + 1, nY);
        if (walls.contains(nBl) || walls.contains(nBr)) {
            System.out.println("1");
            return false;
        }
        if (boxes.contains(nBl) && !nBl.equals(box)) {
            if (!moveBox(nBl, dir, queue)) {
                System.out.println("2");
                return false;
            }
        }
        if (boxes.contains(nBr) && !nBr.equals(box)) {
            if (!moveBox(nBr, dir, queue)) {
                System.out.println(3);
                return false;
            }
        }
        if (boxes.contains(new Point(nX - 1, nY)) && !(new Point(nX - 1, nY).equals(box))) {
            if (!moveBox(new Point(nX - 1, nY), dir, queue)) {
                System.out.println(4);
                return false;
            }
        }
        queue.put(box, nBl.getLocation());
        return true;
    }

    static void printMap() {
        int maxX = walls.stream().mapToInt(p -> p.x).max().orElseThrow() + 1;
        int maxY = walls.stream().mapToInt(p -> p.y).max().orElseThrow() + 1;
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                String v = ".";
                Point p = new Point(x, y);
                if (walls.contains(p)) v = "#";
                if (boxes.contains(p)) v = "[";
                if (boxes.contains(new Point(x - 1, y))) v = "]";
                if (robot.equals(p)) v = "@";
                System.out.print(v);
            }
            System.out.println();
        }
    }
}
