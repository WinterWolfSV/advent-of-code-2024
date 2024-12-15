package D15;

import util.Util;

import java.awt.*;
import java.util.*;

public class D15P1 {
    public static Map<Character, int[]> dirs = Map.of(
            '^', new int[]{0, -1},
            'v', new int[]{0, 1},
            '<', new int[]{-1, 0},
            '>', new int[]{1, 0}
    );

    public static void main(String[] args) {
        String[] data = Util.fileToArray("src/D15/input.txt", "\n\n");
        String[] map = data[0].split("\n");
        HashSet<Point> walls = new HashSet<>();
        HashSet<Point> boxes = new HashSet<>();
        Point robot = new Point(0, 0);
        for (int i = 0; i < map.length; i++) {
            String[] line = map[i].split("");
            for (int j = 0; j < line.length; j++) {
                switch (line[j]) {
                    case "#" -> walls.add(new Point(j, i));
                    case "O" -> boxes.add(new Point(j, i));
                    case "@" -> robot = new Point(j, i);
                }
            }
        }
        for (Character dir : data[1].replace("\n", "").chars().mapToObj(c -> (char) c).toList()) {
            robot = moveRobot(dir, robot, walls, boxes);
        }
        long tot = 0;
        for (Point box : boxes) {
            tot += 100L * box.y + box.x;
        }
        System.out.println(tot);
    }

    public static boolean canMove(char dir, Point robot, HashSet<Point> walls, HashSet<Point> boxes) {
        Point newPos = robot.getLocation();
        newPos.translate(dirs.get(dir)[0], dirs.get(dir)[1]);
        if (walls.contains(newPos)) return false;
        if (boxes.contains(newPos)) {
            return canMove(dir, newPos, walls, boxes);
        }
        return true;
    }

    public static Point moveRobot(char dir, Point robot, HashSet<Point> walls, HashSet<Point> boxes) {
        Point newPos = robot.getLocation();
        newPos.translate(dirs.get(dir)[0], dirs.get(dir)[1]);
        if (!canMove(dir, robot, walls, boxes)) return robot;
        if (boxes.contains(newPos)) {
            Point moved = moveRobot(dir, newPos, walls, boxes);
            if (moved.equals(newPos)) {
                return robot;
            } else {
                boxes.remove(newPos);
                boxes.add(moved);
            }
        }
        return newPos;
    }
}
