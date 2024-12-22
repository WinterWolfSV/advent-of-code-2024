package D22;

import util.Util;

public class D22P1 {
    public static void main(String[] args) {
        String[] data = Util.fileToArray("src/D22/input.txt");
        long tot = 0;
        for (String s : data) {
            tot += evolve(Integer.parseInt(s), 2000);
        }
        System.out.println(tot);
    }

    static int evolve(long s, int amount) {
        for (int i = 0; i < amount; i++) {
            long t = s << 6;
            s = mixPrune(s, t);
            t = s >> 5;
            s = mixPrune(s, t);
            t = s << 11;
            s = mixPrune(s, t);
        }
        return (int) s;
    }

    static long mixPrune(long n1, long n2) {
        return (n1 ^ n2) % (1 << 24);
    }
}
