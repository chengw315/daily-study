package daily;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/2/25
 **/
public class LeetCode867 {

    public static void main(String[] args) {
        int[][] transpose = new LeetCode867().transpose(new int[][]{});
        int[][] transpose1 = new LeetCode867().transpose(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
        int[][] transpose2 = new LeetCode867().transpose(new int[][]{{1,2},{4,5},{7,8}});
    }

    public int[][] transpose(int[][] matrix) {
        if (matrix.length < 1) {
            return new int[][]{};
        }
        int[][] result = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = matrix[j][i];
            }
        }
        return result;
    }
}
