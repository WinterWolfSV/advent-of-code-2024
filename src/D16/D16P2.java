package D16;

import util.Util;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class D16P2 {
    // Up, right, down, left
    static int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    public static void main(String[] args) {
        String[] data = Util.fileToArray("src/D16/input.txt", "\n");
        Point start = new Point(0, 0);
        Point end = new Point(0, 0);
        int[][] maze = new int[data.length][data[0].length()];
        for (int y = 0; y < data.length; y++) {
            String datum = data[y];
            String[] r = datum.split("");
            for (int x = 0; x < r.length; x++) {
                String c = r[x];
                switch (c) {
                    case "#" -> maze[y][x] = 1;
                    case "S" -> start = new Point(x, y);
                    case "E" -> end = new Point(x, y);
                }
            }
        }
        System.out.println(subparStarMazeSolver(maze, start, end));
    }

    static long subparStarMazeSolver(int[][] maze, Point start, Point goal) {
        ArrayList<MazeState> completed = new ArrayList<>();
        long minCost = Long.MAX_VALUE;
        int rows = maze.length;
        int cols = maze[0].length;
        PriorityQueue<MazeState> pq = new PriorityQueue<>(Comparator.comparingLong(MazeState::cost));
        boolean[][][] visited = new boolean[rows][cols][4];
        pq.add(new MazeState(start.x, start.y, 1, 0, new ArrayList<>()));
        while (!pq.isEmpty()) {
            MazeState current = pq.poll();
            int x = current.x;
            int y = current.y;
            current.visited.add(new Point(x, y));
            if (current.x() == goal.x && current.y() == goal.y && current.cost <= minCost) {
                minCost = current.cost;
                completed.add(current);
            }
            visited[current.y()][current.x()][current.direction()] = true;
            for (int d = 0; d < 4; d++) {
                int nx = x + directions[d][0];
                int ny = y + directions[d][1];
                if (nx >= 0 && ny >= 0 && nx < cols && ny < rows && maze[ny][nx] == 0) {
                    int turnCost = (d == current.direction()) ? 0 : 1000;
                    long newCost = current.cost() + 1 + turnCost;
                    if (!visited[ny][nx][d]) {
                        pq.add(new MazeState(nx, ny, d, newCost, (ArrayList<Point>) current.visited.clone()));
                    }
                }
            }
        }
        HashSet<Point> visitedPoints = new HashSet<>();
        for (MazeState m : completed) {
            if (m.cost != minCost) continue;
            visitedPoints.addAll(m.visited);
        }
        return visitedPoints.size();
    }

    record MazeState(int x, int y, int direction, long cost, ArrayList<Point> visited) {
    }
}