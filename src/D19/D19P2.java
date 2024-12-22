package D19;

import util.Util;

import java.util.HashMap;
import java.util.Map;

public class D19P2 {
    static Map<String, Long> cache = new HashMap<>();
    public static void main(String[] args) {
        String[] data = Util.fileToArray("src/D19/input.txt", "\n\n");
        String[] towels = data[0].split(", ");
        String[] patterns = data[1].split("\n");
        long tot = 0;
        for (String pattern : patterns) {
            long a = canSolve(pattern, towels);
            tot += a;
        }
        System.out.println(tot);
    }

    static long canSolve(String patt, String[] towels) {
        if(cache.containsKey(patt)){
            return cache.get(patt);
        }
        if (patt.isEmpty()) {
            return 1;
        }
        long retVal = 0;
        for (int  i = 0; i < towels.length; i++) {
            String towel = towels[i];
            if (patt.startsWith(towel)) {
                if (patt.substring(towel.length()).isEmpty()) {
                    retVal++;
                    continue;
                }
                retVal += canSolve(patt.substring(towel.length()), towels);
            }
        }
        cache.put(patt, retVal);
        return retVal;
    }
}
