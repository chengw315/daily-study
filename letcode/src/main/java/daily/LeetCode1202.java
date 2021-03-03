package daily;

import java.util.*;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/1/11
 **/
public class LeetCode1202 {
    public static void main(String[] args) {
        String dcab = new LeetCode1202().smallestStringWithSwaps("dcab", Arrays.asList(Arrays.asList(0, 3), Arrays.asList(1, 2), Arrays.asList(0, 2)));

    }

    private int[] parents;
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        parents = new int[s.length()];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
        pairs.forEach(list -> {
            union(list.get(0),list.get(1));
        });

        //连通图排序后存在队列中
        PriorityQueue<Character>[] unionQueues = new PriorityQueue[s.length()];
        //每个下标在哪个连通图中
        int[] cache = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            int queueIndex = find(i);
            cache[i] = queueIndex;
            if (unionQueues[queueIndex] == null) {
                unionQueues[queueIndex] = new PriorityQueue<Character>();
            }
            unionQueues[queueIndex].offer(s.charAt(i));
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            result.append(unionQueues[cache[i]].poll());
        }
        return result.toString();
    }
    private int find(int x) {
        if (parents[x] != x) {
            parents[x] = find(parents[x]);
        }
        return parents[x];
    }
    private void union(int a,int b) {
        parents[find(a)] = find(b);
    }
}
