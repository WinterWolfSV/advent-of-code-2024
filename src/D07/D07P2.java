package D07;

import util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class D07P2 {
    public static void main(String[] args) {
        String[] data = Util.fileToArray("src/D07/input.txt", "\n");
        long tot = 0;
        for (String l : data) {
            long val = Long.parseLong(l.split(": ")[0]);
            List<Integer> nums = Arrays.stream(l.split(": ")[1].split(" ")).map((Integer::parseInt)).toList();
            for (int j = 0; j < Math.pow(3, nums.size() - 1); j++) {
                List<Integer> operations = getOpsFromNum(j, nums.size() - 1);
                if (eval(nums, operations) == val) {
                    tot += val;
                    break;
                }
            }
        }
        System.out.println(tot);
    }

    // 0: +, 1: *, 2: ||
    public static long eval(List<Integer> nums, List<Integer> operations) {
        long num = nums.getFirst();
        for (int i = 1; i < nums.size(); i++) {
            num = handleTwoNums(num, nums.get(i), operations.get(i - 1));
        }
        return num;
    }

    public static long handleTwoNums(long n1, long n2, int operation) {
        return switch (operation) {
            case 0 -> n1 + n2;
            case 1 -> n1 * n2;
            case 2 -> (long) (n1 * Math.pow(10, ((int) Math.log10(n2) + 1)) + n2);
            default -> 0;
        };
    }

    public static List<Integer> getOpsFromNum(long num, int size) {
        List<Integer> retVal = new ArrayList<>(size);
        while (num > 0) {
            retVal.addFirst((int) (num % 3));
            num /= 3;
        }
        if (retVal.size() < size) {
            retVal.addAll(0, Collections.nCopies(size - retVal.size(), 0));
        }
        return retVal;
    }
}
