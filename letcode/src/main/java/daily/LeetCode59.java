package daily;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/3/16
 **/
public class LeetCode59 {

    public static void main(String[] args) {
        int[][] ints = new LeetCode59().generateMatrix(2);
    }

    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int x = 0,y = 0;
        int curDir = 0;
        int[] xOff = new int[]{0, 1, 0, -1};
        int[] yOff = new int[]{1, 0, -1, 0};
        for (int i = 1; i <= n * n; i++) {
            //到头，转向
            if (x < 0 || x >= n ||
                    y < 0 || y >= n ||
                    result[x][y] != 0) {
                x-= xOff[curDir];
                y-= yOff[curDir];
                curDir = (curDir + 1) % 4;
                x += xOff[curDir];
                y += yOff[curDir];
            }
            result[x][y] = i;
            x += xOff[curDir];
            y += yOff[curDir];
        }
        return result;
    }
}
