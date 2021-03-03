package daily;

import java.util.*;

/**
 * description 查找字符在每个字符串中的出现次数
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/10/14
 **/
public class LeetCode1002 {

    public List<String> commonChars(String[] A) {
        //入参校验
        if(A == null || A.length == 0) {
            return new ArrayList<>();
        }

        Map<Character,Integer> statistics = count(A[0]);
        for (int i = 1; i < A.length; i++) {
            statistics = merge(statistics,count(A[i]));
        }
        ArrayList<String> strings = new ArrayList<>();
        for (char c : statistics.keySet()) {
            Integer integer = statistics.get(c);
            for (int i = 0; i < integer; i++) {
                strings.add(String.valueOf(c));
            }
        }
        return strings;
    }

    private Map<Character, Integer> merge(Map<Character, Integer> statistics, Map<Character, Integer> count) {
        Object[] array = statistics.keySet().toArray();
        for (Object o : array) {
            char c = (char) o;
            if (!count.containsKey(c)) {
                statistics.remove(c);
            } else {
                statistics.put(c, Math.min(statistics.get(c), count.get(c)));
            }
        }
        return statistics;
    }

    private Map<Character, Integer> count(String s) {
        Map<Character,Integer> result = new HashMap<>(26);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(!result.containsKey(c)) {
                result.put(c,1);
            } else {
                result.put(c, result.get(c) + 1);
            }
        }
        return result;
    }
}
