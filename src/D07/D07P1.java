package D07;

import util.Util;

import java.util.*;

public class D07P1 {
    public static void main(String[] args) {
        String[] data = Util.fileToArray("src/D07/input.txt", "\n");
        long tot = 0;
        for (String l : data) {
            long val = Long.parseLong(l.split(": ")[0]);
            List<Integer> nums = Arrays.stream(l.split(": ")[1].split(" ")).map((Integer::parseInt)).toList();
            for (int j = 0; j < Math.pow(2, nums.size() - 1); j++) {
                List<Integer> operations = String.format("%" + (nums.size() - 1) + "s", Integer.toBinaryString(j)).replace(' ', '0').chars().map(c -> c - '0').boxed().toList();
                if(eval(nums,operations)==val){
                    tot += val;
                    break;
                }
            }
        }
        System.out.println(tot);
    }

    // 0: +, 1: *
    public static long eval(List<Integer> nums, List<Integer> operations) {
        long num = nums.getFirst();
        for (int i = 1; i < nums.size(); i++) {
            num = operations.get(i - 1) == 0 ? num + nums.get(i) : num * nums.get(i);
        }
        return num;
    }
}
