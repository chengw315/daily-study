package daily;

import java.util.Arrays;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/12/25
 **/
public class LeetCode455 {

    public static void main(String[] args) {
        new LeetCode455().findContentChildren(new int[]{1,2,3},new int[]{1,1});
        new LeetCode455().findContentChildren(new int[]{1,2,3},new int[]{1,2});
    }

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int left = 0;
        int result = 0;
        for (int i = 0; i < s.length; i++) {
            for (int j = left; j < g.length; j++) {
                if (s[i] >= g[j]) {
                    result++;
                    left++;
                }
                break;
            }
        }
        return result;
    }
}
