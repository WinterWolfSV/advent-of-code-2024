package D17;

import util.Util;

import java.util.ArrayList;
import java.util.Arrays;

public class D17P1 {
    public static void main(String[] args) {
        String[] data = Util.fileToArray("src/D17/input.txt", "\n");
        int[] regs = new int[3];
        ArrayList<Integer> instructions = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            regs[i] = Integer.parseInt(data[i].split(" ")[2]);
        }
        for (String c : data[4].split(" ")[1].split(",")) {
            instructions.add(Integer.parseInt(c));
        }

        ArrayList<String> out = new ArrayList<>();

        for (int i = 0; i < instructions.size(); i += 2) {
            int op = instructions.get(i + 1);
            switch (instructions.get(i)) {
                case 0 -> regs[0] = regs[0] >> getCOp(regs, op);
                case 1 -> regs[1] = regs[1] ^ op;
                case 2 -> regs[1] = getCOp(regs, op) % 8;
                case 3 -> {
                    if (regs[0] != 0) i = op - 2;
                }
                case 4 -> regs[1] = regs[1] ^regs[2];
                case 5 -> out.add(Integer.toString(getCOp(regs, op)%8));
                case 6 -> regs[1] = regs[0] >> getCOp(regs,op);
                case 7 -> regs[2] = regs[0] >> getCOp(regs,op);
            }

        }
        System.out.println(String.join(",",out));
    }

    static int getCOp(int[] regs, int op) {
        if (op > 3) {
            return regs[op-4];
        }
        return op;
    }
}
