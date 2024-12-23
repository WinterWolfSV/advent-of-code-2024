package D23;

import util.Util;

import java.util.*;

public class D23P2 {
    private static HashMap<String, HashSet<String>> graph = new HashMap<>();
    public static void main(String[] args) {
        String[] input = Util.fileToArray("src/D23/input.txt");
        for (String conn : input) {
            String[] split = conn.split("-");
            String a = split[0], b = split[1];
            graph.put(a, graph.getOrDefault(a, new HashSet<>()));
            graph.get(a).add(b);
            graph.put(b, graph.getOrDefault(b, new HashSet<>()));
            graph.get(b).add(a);
        }
        Set<String> maxClique = new HashSet<>();
        BronKerbosch(new HashSet<>(), graph.keySet(), new HashSet<>(), maxClique);
        List<String> sortedClique = new ArrayList<>(maxClique.stream().toList());
        Collections.sort(sortedClique);
        System.out.println(String.join(",",sortedClique));
    }

    static void BronKerbosch(Set<String> r, Set<String> p, Set<String> x, Set<String> max) {
        if (p.isEmpty() && x.isEmpty()) {
            if (r.size() > max.size()) {
                max.clear();
                max.addAll(r);
            }
            return;
        }
        HashSet<String> pCopy = new HashSet<>(p);
        for (String node : pCopy) {
            Set<String> neighbours = graph.get(node);
            BronKerbosch(
                    union(r, node),
                    intersect(p, neighbours),
                    intersect(x, neighbours),
                    max);
            p.remove(node);
            x.add(node);
        }
    }

    static Set<String> union(Set<String> s1, String s2) {
        Set<String> retVal = new HashSet<>(s1);
        retVal.add(s2);
        return retVal;
    }

    static Set<String> intersect(Set<String> s1, Set<String> s2) {
        Set<String> retVal = new HashSet<>(s1);
        retVal.retainAll(s2);
        return retVal;
    }
}
