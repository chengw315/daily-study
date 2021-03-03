package daily;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/11/17
 **/
public class LeetCode1030 {
    public static void main(String[] args) {
        int[][] ints = new LeetCode1030().allCellsDistOrder(2, 2, 0, 1);
    }
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] result = new int[R * C][2];
        int num = 0;
        Queue<int[]> queue= new LinkedList<>();
        queue.add(new int[]{r0,c0});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            result[num++] = poll;
            //上下左右
        }
        Arrays.sort(result, (o1, o2) -> Math.abs(o1[0] - r0) + Math.abs(o1[1] - c0)
                - Math.abs(o2[0] - r0) - Math.abs(o2[1] - c0));
        return result;
    }
}
