package D14;

import util.Util;

import java.awt.*;
import java.util.Arrays;

public class D14P1 {
    public static void main(String[] args) {
        int moves = 100;
        int sizeX = 101;
        int sizeY = 103;
        String[] data = Util.fileToArray("src/D14/input.txt", "\n");
        int[] quads = new int[4];
        Point midPoint = new Point(sizeX / 2, sizeY / 2);
        for (String s : data) {
            String[] a = s.split("[ ,=]");
            Point sP = new Point(Integer.parseInt(a[1]), Integer.parseInt(a[2]));
            Point sV = new Point(Integer.parseInt(a[4]), Integer.parseInt(a[5]));
            Point nP = moveRobot(sP, sV, sizeX, sizeY, moves);
            int dX = nP.x - midPoint.x;
            int dY = nP.y - midPoint.y;
            if (dX != 0 && dY != 0) {
                int quadIndex = (dX > 0 ? 2 : 0) + (dY > 0 ? 1 : 0);
                quads[quadIndex]++;
            }
        }
        int tot = Arrays.stream(quads).reduce(1, (a, b) -> a * b);
        System.out.println(tot);
    }

    public static Point moveRobot(Point sP, Point sV, int boardSizeX, int boardSizeY, int moves) {
        if (sV.x < 0) {
            sV.translate(boardSizeX, 0);
        }
        if (sV.y < 0) {
            sV.translate(0, boardSizeY);
        }
        int nX = ((sP.x + sV.x * moves)) % boardSizeX;
        int nY = ((sP.y + sV.y * moves)) % boardSizeY;
        return new Point(nX, nY);
    }
}
