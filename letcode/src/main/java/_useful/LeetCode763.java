package _useful;

import java.util.*;

/**
 * description 划分字符区间，把字符串分成几段，这几段里面不能有相同的字符
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/10/22
 **/
public class LeetCode763 {
    class Solution {
        /**
         * 优化点：无需存放每个字符first的位置，但这是以第二次遍历时要遍历整个数组为代价的
         * 而我的算法中，第二次遍历的不是原数组，而是已知的26个字符两个边界
         * @param S
         * @return
         */
        public List<Integer> partitionLabels(String S) {
            int[] last = new int[26];
            int length = S.length();
            for (int i = 0; i < length; i++) {
                last[S.charAt(i) - 'a'] = i;
            }
            List<Integer> partition = new ArrayList<>();
            int start = 0, end = 0;
            for (int i = 0; i < length; i++) {
                end = Math.max(end, last[S.charAt(i) - 'a']);
                if (i == end) {
                    partition.add(end - start + 1);
                    start = end + 1;
                }
            }
            return partition;
        }
    }

    public List<Integer> partitionLabels(String S) {
        ArrayList<Integer> result = new ArrayList<>(27);
        int[] first = new int[26];
        int[] last = new int[26];
        for (int i = 0; i < 26; i++) {
            first[i] = -1;
            last[i] = -1;
        }
        //一次遍历，对每个字符划分区间
        for (int i = 0; i < S.length(); i++) {
            int index = S.charAt(i) - 'a';
            if (first[index] == -1) {
                first[index] = i;
            }
            last[index] = i;
        }
        //26个区间做拼接，有重复子区间的两个区间进行合并
        //方案1：用栈，对所有区间的两个端点排序，然后first入栈，last出栈，栈空一次，就是一段 (x 直接用栈合并不了部分重叠的区间)
        //方案2：水平扫描，找到一个区间内所有可以扩展的点（只有first，没有last的点）
        //需要一个数据结构，存储所有区间内只有first，没有last的点，找到这个点的last在nodes中的位置 HashSet
        HashSet<Integer> set = new HashSet<>(26);
        Node[] nodes = new Node[52];
        for (int i = 0; i < 26; i++) {
            nodes[2 * i] = new Node(i,true,first[i]);
            nodes[2 * i + 1] = new Node(i,false,last[i]);
        }
        Arrays.sort(nodes, Comparator.comparingInt(o -> o.index));
        int lastSeq = -1;
        for(int i = 0; i < 52; i++) {
            if(nodes[i].index == -1) {
                continue;
            }
            if (nodes[i].isFirst) {
                set.add(nodes[i].charSeq);
            } else {
                set.remove(nodes[i].charSeq);
                if (set.isEmpty()) {
                    result.add(nodes[i].index - lastSeq);
                    lastSeq = nodes[i].index;
                }
            }
        }
        return result;
    }

    class Node {
        int charSeq;
        boolean isFirst;
        int index;

        public Node(int charSeq, boolean isFirst,int index) {
            this.charSeq = charSeq;
            this.isFirst = isFirst;
            this.index = index;
        }
    }
}
