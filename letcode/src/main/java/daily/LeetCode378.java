package daily;

/**
 * description 矩阵中第k小的元素
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/7/2
 **/
public class LeetCode378 {

    public static void main(String[] args) {
        //13
        int i = new LeetCode378().kthSmallest(new int[][]{{1, 5, 9}, {10, 11, 12}, {12, 13, 15}}, 8);
        int i1 = new LeetCode378().kthSmallest(new int[][]{{1, 2}, {1,3}}, 4);
    }

    /**
     * 归并法
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int[] ints = new int[n];
        for(int i = 0; i < n; i++) {
            ints[i] = 0;
        }
        int result = 0;
        int first = 0;
        while (k-- > 0) {
            while (ints[first] >= n) {
                first++;
            }
            int min = matrix[ints[first]][first];
            int index = first;
            for(int i = first + 1; i < n; i++) {
                if(matrix[ints[i]][i] < min) {
                    index = i;
                    min = matrix[ints[i]][i];
                }
            }
            ints[index]++;
            result = min;
        }
        return result;
    }

}
