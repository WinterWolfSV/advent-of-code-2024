package D01;

import util.Util;

import java.util.*;

public class D01P2 {
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
        Map<Integer, Integer> m2 = new HashMap<>();
        for (int num : l2) {
            m2.put(num, m2.getOrDefault(num, 0) + 1);
        }
        for (int num : l1) {
            tot += m2.getOrDefault(num, 0) * num;
        }
        System.out.println(tot);
    }
}