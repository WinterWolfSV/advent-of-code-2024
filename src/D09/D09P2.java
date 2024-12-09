package D09;

import util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class D09P2 {
    public static void main(String[] args) {
        String[] data = Util.fileToArray("src/D09/input.txt", "");
        List<Integer> files = new ArrayList<>();
        boolean flag = true;
        HashMap<Integer, Integer> amounts = new HashMap<>();
        for (int i = 0; i < data.length; i++) {
            int amount = Integer.parseInt(data[i]);
            files.addAll(Collections.nCopies(amount, flag ? i / 2 : -1));
            if(flag) amounts.put(i/2,amount);
            flag = !flag;
        }
        for (int i = data.length/2; i >= 0 ; i--) {
            int fileAmount = amounts.get(i);
            int indexOfSpace = Collections.indexOfSubList(files,Collections.nCopies(fileAmount,-1));
            int indexOfNumber = Collections.indexOfSubList(files,Collections.nCopies(fileAmount,i));
            if(indexOfNumber>indexOfSpace && indexOfSpace != -1){
                for (int j = indexOfSpace; j < indexOfSpace+fileAmount; j++) {
                    files.set(j,i);
                    files.set(j+indexOfNumber-indexOfSpace,-1);
                }
            }
        }
        long checkSum = 0;
        for (int i = 0; i < files.size(); i++) {
            if(files.get(i) == -1) continue;
            checkSum += files.get(i) * i;
        }

        System.out.println(checkSum); // Result: 6183632723350
    }
}
