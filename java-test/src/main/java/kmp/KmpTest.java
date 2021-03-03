package kmp;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/8/17
 **/
public class KmpTest {

    public static void main(String[] args) {
        String pat = "ABBA";

        String target = "ABASDNLKSANKLABABABABABABABABABCASDSABABABBABBABABCABC";

        int i = findByDfa(pat, target);
        int i1 = findByKmp(pat, target);
        int i2 = target.indexOf(pat);

    }

    /**
     * 通过KMP查找子串
     *
     * @param pat
     * @param target
     * @return
     */
    public static int findByKmp(String pat, String target) {
        int[] next = getNext(pat);
        int curState = 0;
        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) == pat.charAt(curState)) {
                if (curState == pat.length() - 1) {
                    return i - pat.length() + 1;
                }
                curState++;
            } else if (curState != 0) {
                //KMP查找不匹配时需要使用“重生点”（next）再次匹配，此时目的串不能前进
                i--;
                curState = next[curState];
            }
        }
        return -1;
    }

    private static int[] getNext(String pat) {
        int[] next = new int[pat.length()];
        //next数组代表复活点，匹配失效后从此状态开始重新匹配（目标串不前进）
        next[0] = 0;
        for (int i = 1; i < pat.length(); i++) {
            int last = next[i - 1];
            //复活点的前一状态必然能够通过charI到达复活点，因为到达当前状态的前置字符已经
            //分而治之，数组每个元素只要保证自己和自己前一位的某种关系，就能保证整个数组按此关系传递
            while (pat.charAt(i - 1) != pat.charAt(Math.max(0, last - 1)) && last != 0) {
                next[last] = next[next[last]];
            }
            next[i] = last;
        }
        return next;
    }

    /**
     * 通过DFA查找子串
     *
     * @param pat
     * @param target
     * @return
     */
    public static int findByDfa(String pat, String target) {
        int[][] dfa = getDfa(pat);
        int curState = 0;
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            curState = dfa[c][curState];
            if (curState == pat.length()) {
                return i - pat.length() + 1;
            }
        }
        return -1;
    }

    /**
     * 生成确定有限状态字符串自动机
     *
     * @param pat
     * @return
     */
    private static int[][] getDfa(String pat) {
        int R = 256;
        int[][] dfa = new int[R][pat.length()];
        dfa[pat.charAt(0)][0] = 1;
        for (int X = 0, j = 1; j < pat.length(); j++) {
            // 计算dfa[][j]
            for (int c = 0; c < R; c++) {
                // 复制匹配失败情况下的值
                dfa[c][j] = dfa[c][X];
            }
            // 设置匹配成功情况下的值
            char charJ = pat.charAt(j);
            dfa[charJ][j] = j + 1;
            // 更新重启状态（下一个状态失败时会回到X）
            // 原理：当前状态通过charJ到达下一状态，那么当前状态的重启状态通过charJ到达的状态，即是下一状态的重启状态
            // 实际：当前状态的重启状态遇到charJ，能匹配到下一状态就+1，否则相当于跳到了重启状态的重启状态（因为复制了匹配失败的值）
            X = dfa[charJ][X];
        }
        return dfa;
    }


}
