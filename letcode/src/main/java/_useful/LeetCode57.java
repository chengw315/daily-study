package _useful;

/**
 * description 插入区间，合并区间
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]         1，3  2，5合并为1，5
 *
 * 反思：多可能性问题要弄清楚所有可能性，这些可能性是否可以用同一种方法解决，如果不能或着适配起来比较费劲，应毫不犹豫地放弃适配。
 *     有时候借助现成数据结构不失为一种妙计，这里使用变长数组List，就没有必要费心思考虑不同可能性下结果的长度了，可以大大提升效率
 *
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/11/4
 **/
public class LeetCode57 {

    public static void main(String[] args) {
        int[][] insert2 = new LeetCode57().insert(new int[][]{{1,5}}, new int[]{0,3});
        int[][] insert1 = new LeetCode57().insert(new int[][]{{1,5}}, new int[]{6,8});
        int[][] insert = new LeetCode57().insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5});
    }

    /**
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        //记录合并的 左右下标，以及新的左、右值
        int leftIndex = 0, rightIndex = 0;
        int left = newInterval[0], right = newInterval[1];
        boolean findLeftFlag = false, findRightFlag = false;
        //两种情况，有可合并的区间，无可合并的区间
        //   有可合并的区间，令leftIndex=最左侧被合并的区间下标，rightIndex=最右侧合并区间下标+1，left、right=合并后区间的左右阈
        //   无可合并的区间，令leftIndex=rightIndex=插入点的右侧区间下标
        for (int i = 0; i < intervals.length; i++) {
            //left、right落于两个区间之间
            if (!findLeftFlag && intervals[i][0] > right) {
                findLeftFlag = findRightFlag = true;
                leftIndex = rightIndex = i;
            }

            //left落入一个区间之前
            if (!findLeftFlag && intervals[i][0] > left) {
                findLeftFlag = true;
                leftIndex = i;
            }

            //left落入一个区间
            if (!findLeftFlag && intervals[i][0] <= left && intervals[i][1] >= left) {
                findLeftFlag = true;
                leftIndex = i;
                left = intervals[i][0];
                //新区间被合并
                if (intervals[i][1] >= right) {
                    findRightFlag = true;
                    rightIndex = i+1;
                    right = intervals[i][1];
                    break;
                }
            }

            //right落入一个区间之前
            if (!findRightFlag && intervals[i][0] > right) {
                findRightFlag = true;
                rightIndex = i;
                break;
            }

            //right落入一个区间
            if (!findRightFlag && intervals[i][0] <= right && intervals[i][1] >= right) {
                findRightFlag = true;
                rightIndex = i + 1;
                right = intervals[i][1];
                break;
            }
        }
        //左侧落在整个区间之后
        if(!findLeftFlag) {
            leftIndex = rightIndex = intervals.length;
        }
        //右侧落在整个区间之后
        if(findLeftFlag && !findRightFlag) {
            rightIndex = intervals.length;
        }

        int[][] result = new int[intervals.length - rightIndex + leftIndex + 1][2];
        int n = 0;
        //左
        for (int i = 0; i < leftIndex; i++) {
            result[n][0] = intervals[i][0];
            result[n][1] = intervals[i][1];
            n++;
        }
        //合并区间
        result[n][0] = left;
        result[n][1] = right;
        n++;
        //右
        for (int i = rightIndex; i < intervals.length; i++) {
            result[n][0] = intervals[i][0];
            result[n][1] = intervals[i][1];
            n++;
        }
        return result;
    }
}
