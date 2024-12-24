package D24;

import util.Util;

import java.util.*;

public class D24P1 {
    public static void main(String[] args) {
        String[] input = Util.fileToArray("src/D24/input.txt", "\n\n");
        HashMap<String, Boolean> values = new HashMap<>();
        for (String val : input[0].split("\n")) {
            values.put(val.split(": ")[0], Objects.equals(val.split(": ")[1], "1"));
        }
        Queue<Instruction> queue = new ArrayDeque<>();
        for (String ins : input[1].split("\n")) {
            String[] sIns = ins.split("\\W+");
            queue.add(new Instruction(sIns[0], sIns[2], sIns[3], sIns[1]));
        }
        while (!queue.isEmpty()) {
            Instruction ins = queue.poll();
            if (!values.containsKey(ins.reg1) || !values.containsKey(ins.reg2)) {
                queue.add(ins);
                continue;
            }
            boolean res = false;
            boolean r1 = values.get(ins.reg1);
            boolean r2 = values.get(ins.reg2);
            switch (ins.op) {
                case "OR" -> res = r1 | r2;
                case "AND" -> res = r1 & r2;
                case "XOR" -> res = r1 ^ r2;
            }
            values.put(ins.target, res);
        }
        List<String> sorted = new ArrayList<>(values.keySet().stream().toList());
        Collections.sort(sorted);
        int counter = 0;
        long tot = 0;
        for (String s : sorted) {
            if (s.startsWith("z")) {
                if (values.get(s)) tot += Math.pow(2, counter);
                counter++;
            }
        }
        System.out.println(tot);


    }

    record Instruction(String reg1, String reg2, String target, String op) {}
}
