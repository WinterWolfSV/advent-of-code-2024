package D22;

import util.Util;

import java.util.*;

public class D22P2 {
    public static void main(String[] args) {
        String[] data = Util.fileToArray("src/D22/input.txt");
        HashMap<ArrayList<Byte>, Long> scores = new HashMap<>();
        for (String datum : data) {
            preCalcVals(Long.parseLong(datum), 2000, scores);
        }

        long mVal = 0;
        ArrayList<Byte> maxList = null;
        for (ArrayList<Byte> key : scores.keySet()) {
            long val = scores.get(key);
            if (mVal <= val) {
                mVal = val;
                maxList = key;
            }
        }
        System.out.println(mVal);
        System.out.println(maxList);
    }

    static void preCalcVals(long secret, int amount, HashMap<ArrayList<Byte>, Long> scores) {
        byte[] sequence = getSec(secret, amount);
        byte[] differences = calcDiff(sequence);
        ArrayList<Byte> fixedSizeList = new ArrayList<>(4);
        HashSet<ArrayList<Byte>> visited = new HashSet<>();
        fixedSizeList.add((byte) 0);
        for (int i = 0; i < 3; i++) {
            fixedSizeList.add(differences[i]);
        }
        for (int i = 3; i < differences.length; i++) {
            fixedSizeList.removeFirst();
            fixedSizeList.add(differences[i]);
            ArrayList<Byte> c = (ArrayList<Byte>) fixedSizeList.clone();
            if (visited.add(c)) scores.put(c, scores.getOrDefault(c, 0L) + sequence[i + 1]);
        }
    }

    static byte[] calcDiff(byte[] l) {
        byte[] diffs = new byte[l.length - 1];
        for (int i = 0; i < l.length - 1; i++) {
            diffs[i] = (byte) (l[i + 1] - l[i]);
        }
        return diffs;
    }

    static byte[] getSec(long s, int amount) {
        byte[] sec = new byte[amount];
        for (int i = 0; i < amount; i++) {
            sec[i] = (byte) (s % 10);
            long t = s << 6;
            s = (s ^ t) % (1 << 24);
            t = s >> 5;
            s = (s ^ t) % (1 << 24);
            t = s << 11;
            s = (s ^ t) % (1 << 24);
        }
        return sec;
    }
}
