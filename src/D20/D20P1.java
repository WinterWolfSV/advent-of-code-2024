package D20;

import util.Util;

import java.util.*;


public class D20P1 {
    static int[][] map;
    static int[] sPos;
    static int[] ePos;
    static int[][] dirs = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    public static void main(String[] args) {
        String[] input = Util.fileToArray("src/D20/input.txt");
        map = new int[input.length][input[0].length()];
        for (int y = 0; y < input.length; y++) {
            String r = input[y];
            for (int x = 0; x < r.length(); x++) {
                map[y][x] = r.charAt(x) == '#' ? 1 : 0;
                if (r.charAt(x) == 'S') sPos = new int[]{x, y};
                if (r.charAt(x) == 'E') ePos = new int[]{x, y};
            }
        }
        HashSet<int[]> cheats = new HashSet<>();
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                int[] tester = new int[]{x, y};
                if (canBeCheated(tester)) {
                    cheats.add(tester);
                }
            }
        }
        int tot = 0;
        int ref = aStar(new int[]{-1, -1});
        for (int[] cheat : cheats) {

            if (ref - aStar(cheat) >= 100) {
                tot++;
            }
        }
        System.out.println(tot);
    }

    static int aStar(int[] cheat) {
        int sizeX = map[0].length;
        int sizeY = map.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s[2]));
        boolean[][] visited = new boolean[sizeX][sizeY];
        pq.add(new int[]{sPos[0], sPos[1], 0});
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            if (current[0] == ePos[0] && current[1] == ePos[1]) return current[2];
            if (visited[current[0]][current[1]]) continue;
            visited[current[0]][current[1]] = true;
            for (int d = 0; d < 4; d++) {
                int dX = current[0] + dirs[d][0];
                int dY = current[1] + dirs[d][1];
                if (dX >= 0 && dY >= 0 && dX < sizeX && dY < sizeY && (map[dY][dX] == 0 || Arrays.equals(new int[]{dX, dY}, cheat))) {
                    if (!visited[dX][dY]) {
                        pq.add(new int[]{dX, dY, current[2] + 1});
                    }
                }
            }
        }
        return -1;
    }

    static boolean canBeCheated(int[] coords) {
        int x = coords[0];
        int y = coords[1];
        int nearby = 0;

        if (map[coords[1]][coords[0]] != 1) return false;
        for (int i = 0; i < 4; i++) {
            int[] delta = dirs[i];
            int nX = delta[0] + x;
            int nY = delta[1] + y;
            if (nX < 0 || nX >= map[0].length || nY < 0 || nY >= map.length) {
                continue;
            }
            if (map[y + delta[1]][x + delta[0]] == 0) {
                nearby++;
            }
        }
        return nearby > 1;
    }


}
