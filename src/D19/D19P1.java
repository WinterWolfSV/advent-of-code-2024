package D19;

import util.Util;

public class D19P1 {
    public static void main(String[] args) {
        String[] data = Util.fileToArray("src/D19/input.txt", "\n\n");
        String[] towels = data[0].split(", ");
        String[] patterns = data[1].split("\n");
        int tot = 0;
        for (String pattern : patterns) {
            if(canSolve(pattern, towels)) tot++;
        }
        System.out.println(tot);
    }

    static boolean canSolve(String patt, String[] towels) {
        boolean retVal = false;
        for (String towel : towels) {
            if (patt.startsWith(towel)) {
                if (patt.substring(towel.length()).isEmpty()) {
                    return true;
                } else {
                    if (canSolve(patt.substring(towel.length()), towels)) {
                        return true;
                    }
                }
            }
        }
        return retVal;
    }
}
