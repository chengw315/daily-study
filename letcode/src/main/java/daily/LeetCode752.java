package daily;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * description 打开密码锁的最少次数
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/7/3
 **/
public class LeetCode752 {

    public static void main(String[] args) {
        int i = new LeetCode752().openLock(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202");
    }

    public int openLock(String[] deadends, String target) {
        HashSet<String> deadEnds = new HashSet<>(10000,1f);

        for(int i = 0; i < deadends.length; i++) {
            deadEnds.add(deadends[i]);
        }
        int result = 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                String cur = queue.poll();
                if(target.equals(cur)) {
                    return result;
                }
                if(deadEnds.contains(cur)) {
                    continue;
                }
                deadEnds.add(cur);
                for (int i = 0; i < 4; i++) {
                    char[] chars = cur.toCharArray();
                    if(chars[i] == '9') {
                        chars[i] = '0';
                        queue.offer(new String(chars));
                    } else {
                        chars[i]++;
                        queue.offer(new String(chars));
                    }
                    chars = cur.toCharArray();
                    if(chars[i] == '0') {
                        chars[i] = '9';
                        queue.offer(new String(chars));
                    } else {
                        chars[i]--;
                        queue.offer(new String(chars));
                    }
                }
            }
            result ++;
        }
        return result;
    }
}
