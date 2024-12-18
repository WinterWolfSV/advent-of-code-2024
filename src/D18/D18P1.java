package D18;

import util.Util;

import java.util.Comparator;
import java.util.PriorityQueue;

public class D18P1 {
    static int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    public static void main(String[] args) {
        int mapSize = 71;
        int iterations = 1024;
        String[] data = Util.fileToArray("src/D18/input.txt", "\n");
        int[][] coords = new int[data.length][2];
        for (int i = 0; i < data.length; i++) {
            String[] sDat = data[i].split(",");
            coords[i] = new int[]{Integer.parseInt(sDat[0]), Integer.parseInt(sDat[1])};
        }
        int[][] map = createMap(coords, mapSize, iterations);
        System.out.println(pathFinder(map));
    }

    public static int pathFinder(int[][] map) {
        int size = map.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s[2]));
        boolean[][] visited = new boolean[size][size];
        pq.add(new int[]{0, 0, 0});
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            if (current[0] == size - 1 && current[1] == size - 1) return current[2];
            if (visited[current[0]][current[1]]) continue;
            visited[current[0]][current[1]] = true;
            for (int d = 0; d < 4; d++) {
                int dX = current[0] + directions[d][0];
                int dY = current[1] + directions[d][1];
                if (dX >= 0 && dY >= 0 && dX < size && dY < size && map[dY][dX] == 0) {
                    if (!visited[dX][dY]) {
                        pq.add(new int[]{dX, dY, current[2] + 1});
                    }
                }
            }
        }
        return -1;
    }

    public static int[][] createMap(int[][] coords, int size, int iterations) {
        int[][] retVal = new int[size][size];
        for (int i = 0; i < iterations; i++) {
            int[] c = coords[i];
            retVal[c[1]][c[0]] = 1;
        }
        return retVal;
    }
}
