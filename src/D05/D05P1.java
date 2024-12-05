package D05;

import util.Util;

public class D05P1 {
    public static void main(String[] a) {
        String[] sections = Util.fileToArray("src/D05/input.txt", "\n\n");
        String[] rules = sections[0].split("\n");
        String[] lists = sections[1].split("\n");
        int tot = 0;
        for (String l : lists) {
            boolean flag = true;
            for (String r : rules) {
                int p1 = l.indexOf(r.split("\\|")[0]);
                int p2 = l.indexOf(r.split("\\|")[1]);
                if (!(p1 == -1 | p2 == -1 | p1 < p2)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                String[] larr = l.split(",");
                tot += Integer.parseInt(larr[(larr.length - 1) / 2]);
            }
        }
        System.out.println(tot);
    }
}
