package D20;

import util.Util;

import java.util.*;


public class D20P2 {
    static int[][] map;
    static int[] sPos;
    static int[] ePos;
    static int[][] dirs = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    static HashMap<List<Integer>, Integer> valPairs = new HashMap<>();
    static int maxLen = -1;

    public static void main(String[] args) {
        String[] input = Util.fileToArray("src/D20/input.txt");
        map = new int[input.length][input[0].length()];
        for (int y = 0; y < input.length; y++) {
            String r = input[y];
            for (int x = 0; x < r.length(); x++) {
                if (r.charAt(x) == '#') {
                    map[y][x] = 1;
                } else {
                    maxLen++;
                }
                if (r.charAt(x) == 'S') sPos = new int[]{x, y};
                if (r.charAt(x) == 'E') ePos = new int[]{x, y};
            }
        }
        initVals();
        int tot = 0;
        for (List<Integer> pos : valPairs.keySet()) {
            Integer value = valPairs.get(pos);
            ArrayList<List<Integer>> cheats = getCheats(pos, 20);
            for (List<Integer> cheat : cheats) {
                if (value - valPairs.get(cheat) - getTaxi(pos, cheat) >= 100) {
                    tot++;
                }
            }
        }

        System.out.println(tot);
    }

    static void initVals() {
        List<Integer> curr = List.of(sPos[0], sPos[1]);
        for (int i = maxLen; i >= 0; i--) {
            for (int j = 0; j < 4; j++) {
                List<Integer> nPos = List.of(curr.get(0) + dirs[j][0], curr.get(1) + dirs[j][1]);
                if (map[nPos.get(1)][nPos.get(0)] == 0 && !valPairs.containsKey(nPos)) {
                    valPairs.put(new ArrayList<>(curr), i);
                    curr = nPos;
                    break;
                }
            }
        }
        valPairs.put(List.of(ePos[0], ePos[1]), 0);
    }

    static ArrayList<List<Integer>> getCheats(List<Integer> coord, int distance) {
        ArrayList<List<Integer>> cheats = new ArrayList<>();
        for (int y = -distance; y <= distance; y++) {
            for (int x = -distance; x <= distance; x++) {
                List<Integer> p = List.of(x + coord.get(0), y + coord.get(1));
                if (isTaxi(coord, p, distance)) {
                    if (map[p.get(1)][p.get(0)] == 0) {
                        cheats.add(p);
                    }
                }
            }
        }
        return cheats;
    }


    static boolean isTaxi(List<Integer> p, List<Integer> tp, int max) {
        if (!(tp.get(0) >= 0 && tp.get(0) < map[0].length && tp.get(1) >= 0 && tp.get(1) < map.length)) {
            return false;
        }
        return getTaxi(p, tp) <= max;
    }

    static int getTaxi(List<Integer> a, List<Integer> b) {
        return (Math.abs(a.get(0) - b.get(0)) + Math.abs(a.get(1) - b.get(1)));
    }


}
