package D06;

import util.Util;

import java.util.*;
import java.util.stream.Collectors;

public class D06P2 {
    public static void main(String[] s) {
        String[] map = Util.fileToArray("src/D06/input.txt", "\n");
        List<int[]> obstacles = new ArrayList<>();
        int[] sPos = new int[2];
        int direction = 1; // 1: up, 2: right, 3: down, 4: left
        int maxY = map.length;
        int maxX = map[0].length();
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length(); x++) {
                char coordChar = map[y].charAt(x);
                if (coordChar == '#') {
                    obstacles.add(new int[]{x, y});
                }
                if (coordChar == '^') {
                    sPos[0] = x;
                    sPos[1] = y;
                }
            }
        }
        int tot = 0;
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                int[] c = new int[]{j, i};
                if (obstacles.stream().anyMatch(arr -> Arrays.equals(arr, c)) || Arrays.equals(c, sPos)) {
                    continue;
                }
                List<int[]> obsies = new ArrayList<>(obstacles.stream().toList());
                obsies.add(new int[]{j, i});
                if (isLoop(direction, sPos.clone(), obsies, maxX, maxY)) {
                    tot++;
                    System.out.println("loop found! " + j + " " + i + " " + tot);
                } else {
                    System.out.println("No loop found. " + i + "/" + maxY + " " + j + "/" + maxX);
                }
            }
        }
        System.out.println("Loops found: " + tot);
    }

    public static boolean isLoop(int startDir, int[] startPos, List<int[]> obstacles, int maxX, int maxY) {
        boolean flag = true;
        int[] sPos = startPos.clone();
        int direction = startDir;
        Set<String> visited = new HashSet<>();
        visited.add(sPos[0] + "," + sPos[1] + "," + direction);
        Set<String> obsies = obstacles.stream().map(arr -> arr[0] + "," + arr[1]).collect(Collectors.toSet());
        while (flag) {
            int x = sPos[0];
            int y = sPos[1];
            switch (direction) {
                case 1:
                    if (y - 1 >= 0) {
                        if (obsies.contains(x + "," + (y - 1))) {
                            direction += 1;
                        } else {
                            sPos[1] -= 1;
                        }
                    } else {
                        flag = false;
                    }
                    break;
                case 2:
                    if (x + 1 < maxX) {
                        if (obsies.contains((x + 1) + "," + y)) {
                            direction += 1;
                        } else {
                            sPos[0] += 1;
                        }
                    } else {
                        flag = false;
                    }
                    break;
                case 3:
                    if (y + 1 < maxY) {
                        if (obsies.contains(x + "," + (y + 1))) {
                            direction += 1;
                        } else {
                            sPos[1] += 1;
                        }
                    } else {
                        flag = false;
                    }
                    break;
                case 4:
                    if (x - 1 >= 0) {
                        if (obsies.contains((x - 1) + "," + y)) {
                            direction = 1;
                        } else {
                            sPos[0] -= 1;
                        }
                    } else {
                        flag = false;
                    }
                    break;
            }
            if (flag && visited.contains(sPos[0] + "," + sPos[1] + "," + direction)) {
                return true;
            }
            visited.add(sPos[0] + "," + sPos[1] + "," + direction);
        }
        return false;
    }
}
