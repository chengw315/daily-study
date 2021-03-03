package daily;

/**
 * description 顺时针打印矩阵
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/6/5
 **/
public class LeetCode29 {

    public static void main(String[] args) {
        for (int i:
        new LeetCode29().spiralOrder(new int[][]{{2,5},{8,4},{0,-1}})) {
            System.out.println(i);
        }
    }

    public int[] spiralOrder(int[][] matrix) {
        if(matrix.length == 0) {
            return new int[0];
        }
        boolean[][] hasGone = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < hasGone.length; i++) {
            for (int j = 0; j < hasGone[i].length; j++) {
                hasGone[i][j] = false;
            }
        }
        int[] result = new int[matrix.length * matrix[0].length];
        //起点坐标
        int x = 0;
        int y = 0;
        //结果偏移量
        int offset = 0;
        //当前走向，1右，2下，3左，0上
        int direction = 1;
        int[][] advanceOffest = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};

        while (true) {
            //先打印
            result[offset++] = matrix[x][y];
            hasGone[x][y] = true;

            //向前走
            int xNext = x + advanceOffest[direction][0];
            int yNext = y + advanceOffest[direction][1];
            if(canAdvance(hasGone, xNext, yNext)) {
                x = xNext;
                y = yNext;
                continue;
            }
            //不能走，转向再向前走
            direction = (direction + 1) % 4;
            xNext = x + advanceOffest[direction][0];
            yNext = y + advanceOffest[direction][1];
            if(canAdvance(hasGone, xNext, yNext)) {
                x = xNext;
                y = yNext;
                continue;
            }
            //也不能转向，结束
            break;
        }
        return result;
    }

    private boolean canAdvance(boolean[][] hasGone, int xNext, int yNext) {
        return xNext >= 0 && xNext < hasGone.length && yNext >= 0 && yNext < hasGone[0].length && !hasGone[xNext][yNext];
    }
}
