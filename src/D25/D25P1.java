package D25;

import util.Util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class D25P1 {
    public static void main(String[] args) {
        String[] data = Util.fileToArray("src/D25/input.txt", "\n\n");
        Set<Integer[]> keys = new HashSet<>();
        Set<Integer[]> locks = new HashSet<>();
        for (String ent : data) {
            String[] lines = ent.split("\n");
            Integer[] rep = new Integer[6];
            Arrays.fill(rep, 0);
            for (String line : lines) {
                for (int i = 0; i < line.length(); i++) {
                    rep[i] += line.charAt(i) == '#' ? 1 : 0;
                }
            }
            if (Objects.equals(lines[0], "#####")) {
                locks.add(rep);
            } else keys.add(rep);
        }
        long tot = 0;
        for (Integer[] key : keys) {
            for (Integer[] lock : locks) {
                boolean flag = true;
                for (int i = 0; i < 5; i++) {
                    if (key[i] + lock[i] > 7) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    tot++;
                }
            }
        }
        System.out.println(tot);
    }
}
