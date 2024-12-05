package D03;

import util.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class D03P1 {
    public static void main(String[] s){
        String data = Util.fileToString("src/D03/input.txt");
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        Matcher matcher = pattern.matcher(data);
        int tot = 0;
        while (matcher.find()) {
            int a = Integer.parseInt(matcher.group(1));
            int b = Integer.parseInt(matcher.group(2));
            tot += a*b;
        }
        System.out.println(tot);
    }
}
