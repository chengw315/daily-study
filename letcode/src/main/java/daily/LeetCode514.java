package daily;

import java.util.ArrayList;
import java.util.List;

/**
 * description 转动钥匙盘的最小次数
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/11/11
 **/
public class LeetCode514 {
    public static void main(String[] args) {
        boolean b = new LeetCode514().findRotateSteps("caotmcaataijjxi", "oatjiioicitatajtijciocjcaaxaaatmctxamacaamjjx") == 10;
        boolean b3 = new LeetCode514().findRotateSteps("edcba", "abcde") == 10;
        boolean b2 = new LeetCode514().findRotateSteps("godding", "godding") == 13;
        boolean b1 = new LeetCode514().findRotateSteps("godding", "gd") == 4;
    }

    class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public int findRotateSteps(String ring, String key) {
        //次数 = 转动次数 + 按按钮次数key.length
        //方案1：回溯算法，以key的指向为路径，对ring建图，然后回溯找到最短路径
        // 先收集所有字母在ring的位置 O(n)
        List[] letterIndexes = new List[26];
        for (int i = 0; i < 26; i++) {
            letterIndexes[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < ring.length(); i++) {
            letterIndexes[ring.charAt(i) - 'a'].add(i);
        }
        List[] edges = new List[ring.length()];
        for (int i = 0; i < ring.length(); i++) {
            edges[i] = new ArrayList<Edge>();

        }
        boolean[][] flags = new boolean[26][26];
        // 建图 O(n方)
        for (int i = -1; i < key.length() - 1; i++) {
            //起点
            int fromChar;
            int toChar = key.charAt(i + 1) - 'a';
            if (i == -1) {
                fromChar = ring.charAt(0) - 'a';
            } else {
                fromChar = key.charAt(i) - 'a';
            }
            if (flags[fromChar][toChar]) {
                continue;
            }
            List indexFrom = letterIndexes[fromChar];
            List indexTo = letterIndexes[toChar];
            for (int j = 0; j < indexFrom.size(); j++) {
                for (int k = 0; k < indexTo.size(); k++) {
                    int from = (Integer) indexFrom.get(j);
                    int to = (Integer) indexTo.get(k);
                    int big = Math.max(from, to);
                    int small = Math.min(from, to);
                    int weight = Math.min(big - small, small + ring.length() - big);
                    edges[from].add(new Edge(from, to, weight));
                }
            }
            flags[fromChar][toChar] = true;
        }

        // 最后回溯
        int num = 0;
        return getMinPath(edges, 0, ring, num, key) + key.length();
    }

    private int getMinPath(List[] edges, int from, String ring, int num, String key) {
        if (num == key.length()) {
            return 0;
        }
        char c = key.charAt(num);
        List edge = edges[from];
        int minPath = Integer.MAX_VALUE;
        for (Object o : edge) {
            Edge e = (Edge) o;
            if (ring.charAt(e.to) == c) {
                minPath = Math.min(e.weight + getMinPath(edges, e.to, ring, num + 1, key), minPath);
            }
        }
        return minPath;
    }
}
