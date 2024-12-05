package D03;

import util.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class D03P2 {
    public static void main(String[] s) {
        String data = Util.fileToString("src/D03/input.txt");
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\)");
        Matcher matcher = pattern.matcher(data);
        int tot = 0;
        boolean doit = true;
        while (matcher.find()) {
            String instruction = matcher.group(0);
            if (instruction.equals("do()")) {
                doit = true;
            } else if (instruction.equals("don't()")) {
                doit = false;
            } else if (doit) {
                int a = Integer.parseInt(matcher.group(1));
                int b = Integer.parseInt(matcher.group(2));
                tot += a * b;
            }

        }
        System.out.println(tot);
    }

}
