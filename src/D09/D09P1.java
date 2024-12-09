package D09;

import util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class D09P1 {
    public static void main(String[] args) {
        String[] data = Util.fileToArray("src/D09/input.txt", "");
        List<Integer> files = new ArrayList<>();
        boolean flag = true;
        for (int i = 0; i < data.length; i++) {
            files.addAll(Collections.nCopies(Integer.parseInt(data[i]), flag ? i / 2 : -1));
            flag = !flag;
        }

        while (files.contains(-1)) {
            while (files.getLast() == -1) {
                files.removeLast();
            }
            int firstIndex = files.indexOf(-1);
            int lastId = files.removeLast();
            if (firstIndex != -1) {
                files.set(firstIndex, lastId);
            }
        }

        long checkSum = 0;
        for (int i = 0; i < files.size(); i++) {
            checkSum += files.get(i) * i;
        }
        System.out.println(checkSum);
    }
}
