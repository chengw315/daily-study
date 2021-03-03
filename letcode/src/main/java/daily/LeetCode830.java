package daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/1/5
 **/
public class LeetCode830 {

    public static void main(String[] args) {
        //三个用例：
        //1.不存在大串
        List<List<Integer>> aabbccddaabbccdd = new LeetCode830().largeGroupPositions("aabbccddaabbccdd");
        //2.起始位置大串
        List<List<Integer>> aaabbccdddaabbccdd = new LeetCode830().largeGroupPositions("aaabbccdddaabbccdd");
        //3.末尾大串
        List<List<Integer>> aaabbccdddaabbccddd = new LeetCode830().largeGroupPositions("aaabbccdddaabbccddd");
    }

    public List<List<Integer>> largeGroupPositions(String s) {
        ArrayList<List<Integer>> lists = new ArrayList<>();
        char last = s.charAt(0);
        int num = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == last) {
                num++;
                continue;
            }
            if (num > 2) {
                lists.add(Arrays.asList(i-num,i-1));
            }
            last = s.charAt(i);
            num = 1;
        }
        if (num > 2) {
            lists.add(Arrays.asList(s.length()-num,s.length()-1));
        }
        return lists;
    }
}
