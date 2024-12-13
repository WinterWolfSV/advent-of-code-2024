package D13;

import util.Util;

public class D13P1 {
    public static void main(String[] args) {
        String[] data = Util.fileToArray("src/D13/input.txt", "\n\n");
        int tot = 0;
        for (String d : data) {
            float[] dX = new float[3];
            float[] dY = new float[3];
            String[] tmp;
            for (int i = 0; i < (tmp = d.split("\n")).length; i++) {
                String[] vals = tmp[i].split(",");
                dX[i] = Integer.parseInt(vals[0].split("[+=]")[1]);
                dY[i] = Integer.parseInt(vals[1].split("[+=]")[1]);
            }
            float b = (dY[2] - dY[0] * dX[2] / dX[0]) / (dY[1] - dY[0] * dX[1] / dX[0]);
            float a = (dX[2] - dX[1] * b) / dX[0];

            if (Math.abs((Math.round(b)) - b) < 0.0001) {
                tot += Math.round(b + a * 3);
            }
        }
        System.out.println(tot);
    }
}
