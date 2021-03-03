package daily;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/2/24
 **/
public class LeetCode832 {

    public static void main(String[] args) {
        int[][] ints = new LeetCode832().flipAndInvertImage(new int[][]{{1, 1, 0}, {1, 0, 1}, {0, 0, 0}});
    }

    public int[][] flipAndInvertImage(int[][] A) {
        int rows = A.length;
        int columns = A[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns / 2; j++) {
                int temp = A[i][j];
                A[i][j] = A[i][columns - 1 - j] == 0 ? 1 : 0;
                A[i][columns - 1 - j] = temp == 0 ? 1 : 0;
            }
            if (columns % 2 == 1) {
                A[i][columns / 2] = A[i][columns / 2] == 0 ? 1 : 0;
            }
        }
        return A;
    }
}
