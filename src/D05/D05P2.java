package D05;

import util.Util;

import java.util.*;

public class D05P2 {
    public static void main(String[] a) {
        String data = Util.fileToString("src/D05/input.txt");
        String[] sections = data.split("\n\n");
        int tot = 0;
        for (String s : sections[1].split("\n")) {
            String[] cs = checkRules(s.split(","), sections[0].split("\n"));
            if (!Arrays.equals(cs, s.split(","))) {
                tot += Integer.parseInt(cs[(cs.length - 1) / 2]);
            }
        }
        System.out.println(tot);
    }

    public static String[] checkRules(String[] l, String[] rules) {
        String[] retval = l;
        List<String> sl = List.of(l);
        for (String r : rules) {
            int p1 = sl.indexOf(r.split("\\|")[0]);
            int p2 = sl.indexOf(r.split("\\|")[1]);
            if (!(p1 == -1 | p2 == -1 | p1 < p2)) {
                String[] switched = switchPositions(l, p2, p1);
                retval = checkRules(switched, rules);
                break;
            }
        }
        return retval;
    }

    public static String[] switchPositions(String[] l, int i1, int i2) {
        String temp = l[i2];
        l[i2] = l[i1];
        l[i1] = temp;
        return l;
    }
}