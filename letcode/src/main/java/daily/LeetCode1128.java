package daily;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/1/26
 **/
public class LeetCode1128 {
    public static void main(String[] args) {
        int i = new LeetCode1128().numEquivDominoPairs(new int[][]{{1, 2}, {2, 1}, {2, 1}, {1, 2}});
        int i2 = new LeetCode1128().numEquivDominoPairs(new int[][]{{1, 3}, {4, 1}, {2, 8}, {9, 2}});
    }

    public int numEquivDominoPairs(int[][] dominoes) {
        //两个数字相同即是等价多米诺骨牌
        int[][] count = new int[10][10];
        int small,big;
        for (int i = 0; i < dominoes.length; i++) {
            small = Math.min(dominoes[i][0],dominoes[i][1]);
            big = Math.max(dominoes[i][0],dominoes[i][1]);
            count[small][big]++;
        }
        int result = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (count[i][j] > 1) {
                    result += count[i][j] * (count[i][j] - 1) / 2;
                }
            }
        }
        return result;
    }
}
