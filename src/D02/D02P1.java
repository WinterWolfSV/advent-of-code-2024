package D02;

import util.Util;

public class D02P1 {
    public static void main(String[] a) {
        String[] content = Util.fileToArray("src/D02/input.txt", "\n");
        int tot = 0;
        for (String line : content) {
            String[] splitcont = line.split(" ");
            boolean decreasing = Integer.parseInt(splitcont[0]) - Integer.parseInt(splitcont[1]) < 0;
            boolean safe = true;
            for (int i = 0; i < splitcont.length - 1; i++) {
                int aa = Integer.parseInt(splitcont[i]);
                int bb = Integer.parseInt(splitcont[i + 1]);
                if (!(aa < bb && bb - aa <= 3 && decreasing || aa > bb && aa - bb <= 3 && !decreasing)) {
                    safe = false;
                    break;
                }
            }
            if (safe) {
                tot += 1;
            }
        }
        System.out.println(tot);
    }
}