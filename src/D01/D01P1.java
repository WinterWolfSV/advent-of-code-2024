package D01;

import util.Util;

import java.util.*;

public class D01P1 {
    public static void main(String[] s) {
        String[] content = Util.fileToArray("src/D01/input.txt", "\n");
        int tot = 0;
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        for (String string : content) {
            String[] splitstring = string.split(" +");
            l1.add(Integer.parseInt(splitstring[0]));
            l2.add(Integer.parseInt(splitstring[1]));
        }
        Collections.sort(l1);
        Collections.sort(l2);
        for (int i = 0; i < l1.size(); i++) {
            tot += Math.abs(l1.get(i) - l2.get(i));
        }
        System.out.println(tot);
    }
}