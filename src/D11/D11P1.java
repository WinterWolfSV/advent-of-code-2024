package D11;

import util.Util;

import java.util.ArrayList;
import java.util.List;

public class D11P1 {
    public static void main(String[] arg) {
        String[] input = Util.fileToArray("src/D11/input.txt", " ");
        long tot = 0;
        for (String s : input) {
            String si;
            List<Long> stones = new ArrayList<>();
            stones.add(Long.parseLong(s));
            for (int i = 0; i < 25; i++) {
                List<Long> newStones = new ArrayList<>();
                for (long stone : stones) {
                    if (stone == 0) {
                        newStones.add(1L);
                    } else if ((si = Long.toString(stone)).length() % 2 == 0) {
                        long i1 = Long.parseLong(si.substring(si.length() / 2));
                        long i2 = Long.parseLong(si.substring(0, si.length() / 2));
                        newStones.addAll(List.of(i1, i2));
                    } else {
                        newStones.add(stone * 2024);
                    }
                }
                stones = new ArrayList<>(newStones);
            }
            tot += stones.size();
        }
        System.out.println(tot);
    }
}
