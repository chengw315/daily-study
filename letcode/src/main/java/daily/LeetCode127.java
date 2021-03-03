package daily;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/11/5
 **/
public class LeetCode127 {

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("hot");
        strings.add("dot");
        strings.add("dog");
        strings.add("lot");
        strings.add("log");
        strings.add("cog");
        int i = new LeetCode127().ladderLength("hit", "cog", strings);
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //特殊情况
        if (beginWord.equals(endWord)) {
            return 0;
        }
        int endIndex = -1;
        for (int i = 0; i < wordList.size(); i++) {
            if(wordList.get(i).equals(endWord)) {
                endIndex = i;
                break;
            }
        }
        if (endIndex < 0) {
            return 0;
        }

        if (gapOne(beginWord,endWord)) {
            return 2;
        }

        boolean[] used = new boolean[wordList.size()];
        int result = 2;
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < wordList.size(); i++) {
            if(!used[i] && gapOne(beginWord,wordList.get(i))) {
                used[i] = true;
                queue.offer(i);
            }
        }

//        boolean[] usedTail = new boolean[wordList.size()];
//        LinkedList<Integer> queueTail = new LinkedList<>();
//        queueTail.offer(endIndex);
//        usedTail[endIndex] = true;
        //双向BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            result++;
            for (int i = 0; i < size; i++) {
                Integer head = queue.poll();
                for (int j = 0; j < wordList.size(); j++) {
                    if(!used[j] &&  gapOne(wordList.get(head),wordList.get(j))) {
                        if(j == endIndex) {
                            return result;
                        }
                        used[j] = true;
                        queue.offer(j);
                    }
                }
            }
//            result++;
//            int sizeTail = queueTail.size();
//            for (int i = 0; i < sizeTail; i++) {
//                Integer head = queueTail.poll();
//                for (int j = 0; j < wordList.size(); j++) {
//                    if(!usedTail[j] &&  gapOne(wordList.get(head),wordList.get(j))) {
//                        if(used[j]) {
//                            return result;
//                        }
//                        usedTail[j] = true;
//                        queueTail.offer(j);
//                    }
//                }
//            }
        }
        return 0;
    }

    private boolean gapOne(String beginWord, String endWord) {
        boolean hasDifference = false;
        for (int i = 0; i < beginWord.length(); i++) {
            if (beginWord.charAt(i) != endWord.charAt(i)) {
                if (hasDifference) {
                    return false;
                }
                hasDifference = true;
            }
        }
        return true;
    }
}
