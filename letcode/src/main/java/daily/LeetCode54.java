package daily;

import java.util.ArrayList;
import java.util.List;

/**
 * description 螺旋矩阵，四个方向就应该考虑四个break点
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/3/15
 **/
public class LeetCode54 {

    public static void main(String[] args) {
        List<Integer> list2 = new LeetCode54().spiralOrder(new int[][]{{1,2,3,4},{10,11,12,5}});
        List<Integer> list1 = new LeetCode54().spiralOrder(new int[][]{{1,2,3,4},{10,11,12,5},{9,8,7,6}});
        List<Integer> list = new LeetCode54().spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }
        int top = 0, bottom = matrix.length;
        int left = 0,right = matrix[0].length;
        List<Integer> result = new ArrayList<>();

        while (true) {
            boolean time2Break = true;
            //右
            for (int i = left; i < right; i++) {
                time2Break = false;
                result.add(matrix[top][i]);
            }
            if (time2Break) {
                break;
            }
            time2Break = true;
            top++;
            //下
            for (int i = top; i < bottom; i++) {
                time2Break = false;
                result.add(matrix[i][right - 1]);
            }
            if (time2Break) {
                break;
            }
            time2Break = true;
            right--;
            //左
            for (int i = right - 1; i >= left; i--) {
                time2Break = false;
                result.add(matrix[bottom - 1][i]);
            }
            if (time2Break) {
                break;
            }
            time2Break = true;
            bottom--;
            //上
            for (int i = bottom - 1; i >= top; i--) {
                time2Break = false;
                result.add(matrix[i][left]);
            }
            if (time2Break) {
                break;
            }
            left++;
        }
        return result;
    }
}
