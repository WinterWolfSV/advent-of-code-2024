package D23;

import util.Util;

import java.util.HashMap;
import java.util.HashSet;

public class D23P1 {
    public static void main(String[] args) {
        String[] input = Util.fileToArray("src/D23/input.txt");
        HashMap<String, HashSet<String>> tree = new HashMap<>();
        HashSet<String> visited = new HashSet<>();
        for (String conn : input) {
            String[] split = conn.split("-");
            String a = split[0], b = split[1];
            tree.put(a, tree.getOrDefault(a, new HashSet<>()));
            tree.get(a).add(b);
            tree.put(b, tree.getOrDefault(b, new HashSet<>()));
            tree.get(b).add(a);
        }

        int tot = 0;

        for (String node : tree.keySet()) {
            HashSet<String> neighbours = (HashSet<String>) tree.get(node).clone();
            visited.add(node);
            neighbours.removeAll(visited);
            for (String nei : (HashSet<String>) neighbours.clone()) {
                neighbours.remove(nei);
                if (visited.contains(nei)) continue;
                for (String n : tree.get(nei)) {
                    if (neighbours.contains(n)) {
                        if (node.startsWith("t") || nei.startsWith("t") || n.startsWith("t")) {
                            tot++;
                        }
                    }
                }
            }
        }
        System.out.println(tot);
    }
}
