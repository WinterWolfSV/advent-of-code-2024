package D02;

import util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// it ain't pretty but it works

public class D02P2 {
    public static void main(String[] a) {
        String[] content = Util.fileToArray("src/D02/input.txt", "\n");
        int tot = 0;
        for (String line : content) {
            if (checkLine(line, true)) {
                tot += 1;
            }
        }
        System.out.println(tot);
    }

    public static boolean checkLine(String str, boolean flag) {
        String[] splitcont = str.split(" ");
        boolean decreasing = Integer.parseInt(splitcont[0]) - Integer.parseInt(splitcont[1]) < 0;
        boolean retval = true;
        for (int i = 0; i < splitcont.length - 1; i++) {
            int aa = Integer.parseInt(splitcont[i]);
            int bb = Integer.parseInt(splitcont[i + 1]);
            if (!(aa < bb && bb - aa <= 3 && decreasing || aa > bb && aa - bb <= 3 && !decreasing)) {
                if (flag) {
                    List<String> l1 = new ArrayList<>(Arrays.asList(splitcont));
                    List<String> l2 = new ArrayList<>(Arrays.asList(splitcont));
                    List<String> l3 = new ArrayList<>(Arrays.asList(splitcont));
                    l1.remove(i);
                    l2.remove(i + 1);
                    l3.remove(i == 0 ? 0 : i - 1);
                    String result1 = String.join(" ", l1);
                    String result2 = String.join(" ", l2);
                    String result3 = String.join(" ", l3);
                    if (checkLine(result1, false) || checkLine(result2, false) || checkLine(result3, false)) {
                        break;
                    }
                }
                retval = false;
                break;
            }
        }
        return retval;
    }
}