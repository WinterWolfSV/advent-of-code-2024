package D11;

import util.Util;

import java.util.*;

public class D11P2 {
    public static void main(String[] arg) {
        String[] input = Util.fileToArray("src/D11/input.txt", " ");
        List<Long> stones = Arrays.stream(input).map(Long::parseLong).toList();
        long tot = 0;
        Map<Long, Long> sMap = new HashMap<>();
        for (Long stone : stones) {
            sMap.put(stone, 1L);
        }

        for (int i = 0; i < 75; i++) {
            sMap = blink(sMap);
        }
        for (Long stone : sMap.keySet()) {
            tot += sMap.get(stone);
        }
        System.out.println(tot);
    }

    public static Map<Long, Long> blink(Map<Long, Long> stones) {
        Map<Long, Long> returnStones = new HashMap<>();
        int t1;
        for (Long stone : stones.keySet()) {
            if (stone == 0) {
                returnStones.put(1L, returnStones.getOrDefault(1L, 0L) + stones.getOrDefault(stone, 1L));
            } else if ((t1 = (int) Math.log10(stone) + 1) % 2 == 0) {
                long i1 = stone % ((int) Math.pow(10, t1 / 2));
                long i2 = stone / ((int) Math.pow(10, t1 / 2));
                returnStones.put(i1, returnStones.getOrDefault(i1, 0L) + stones.getOrDefault(stone, 1L));
                returnStones.put(i2, returnStones.getOrDefault(i2, 0L) + stones.getOrDefault(stone, 1L));
            } else {
                returnStones.put(stone * 2024, returnStones.getOrDefault(stone * 2024, 0L) + stones.getOrDefault(stone, 1L));
            }
        }
        return returnStones;
    }
}

