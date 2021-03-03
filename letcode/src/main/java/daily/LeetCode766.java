package daily;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/2/22
 **/
public class LeetCode766 {

    public static void main(String[] args) {
        boolean toeplitzMatrix2 = new LeetCode766().isToeplitzMatrix(new int[][]{{1, 2, 3, 4}, {5, 1, 2, 3}, {9, 5, 1, 2}});
        boolean toeplitzMatrix = new LeetCode766().isToeplitzMatrix(new int[][]{{1, 2, 3, 4}, {5, 1, 2, 3}, {9, 5, 1, 2}});
        boolean toeplitzMatrix1 = new LeetCode766().isToeplitzMatrix(new int[][]{{1, 2, 2, 4}, {5, 1, 2, 3}, {9, 5, 1, 2}});
    }

    public boolean isToeplitzMatrix(int[][] matrix) {
        int w = matrix[0].length;
        int h = matrix.length;
        if (h <= 1 || w <= 1) {
            return true;
        }
        //第一行
        for (int i = 0; i < w; i++) {
            int temp = matrix[0][i];
            for (int j = 1; j < h; j++) {
                if (i + j < w && temp != matrix[j][i + j]) {
                    return false;
                }
            }
        }
        //第二行到第倒数第二行
        for (int i = 1; i < h; i++) {
            int min = Math.min(i, w);
            for (int j = 0; j < min; j++) {
                int temp = matrix[i][j];
                for (int k = i + 1; k < h; k++) {
                    if (j + k - i < w && temp != matrix[k][j + k - i]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
