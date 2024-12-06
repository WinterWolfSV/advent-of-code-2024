package D06;

import util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class D06P1 {
    public static void main(String[] s) {
        String[] map = Util.fileToArray("src/D06/input.txt", "\n");
        List<int[]> obstacles = new ArrayList<>();
        List<int[]> poses = new ArrayList<>();
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
        poses.add(sPos.clone());

        boolean flag = true;
        while (flag) {
            int x = sPos[0];
            int y = sPos[1];
            switch (direction) {
                case 1:
                    if (y - 1 >= 0) {
                        if (obstacles.stream().anyMatch(arr -> Arrays.equals(arr, new int[]{x, y - 1}))) {
                            direction += 1;
                        } else {
                            sPos[1] -= 1;
                            if(poses.stream().noneMatch(arr->Arrays.equals(arr, sPos))){
                                poses.add(sPos.clone());
                            }
                        }
                    } else {
                        flag = false;
                    }
                    break;
                case 2:
                    if (x + 1 < maxX) {
                        if (obstacles.stream().anyMatch(arr -> Arrays.equals(arr, new int[]{x + 1, y}))) {
                            direction += 1;
                        } else {
                            sPos[0] += 1;
                            if(poses.stream().noneMatch(arr->Arrays.equals(arr, sPos))){
                                poses.add(sPos.clone());
                            }
                        }
                    } else {
                        flag = false;
                    }
                    break;
                case 3:
                    if (y + 1 < maxY) {
                        if (obstacles.stream().anyMatch(arr -> Arrays.equals(arr, new int[]{x, y + 1}))) {
                            direction += 1;
                        } else {
                            sPos[1] += 1;
                            if(poses.stream().noneMatch(arr->Arrays.equals(arr, sPos))){
                                poses.add(sPos.clone());
                            }
                        }
                    } else {
                        flag = false;
                    }
                    break;
                case 4:
                    if (x - 1 >= 0) {
                        if (obstacles.stream().anyMatch(arr -> Arrays.equals(arr, new int[]{x - 1, y}))) {
                            direction = 1;
                        } else {
                            sPos[0] -= 1;
                            if(poses.stream().noneMatch(arr->Arrays.equals(arr, sPos))){
                                poses.add(sPos.clone());
                            }
                        }
                    } else {
                        flag = false;
                    }
                    break;


            }
        }
        System.out.println(poses.size());

    }
}
