package _useful;

import java.util.Arrays;

/**
 * description 二分查询指定数 在升序数组中的区间
 *
 * 问题点：
 *      1.二分查询需要注意的特殊情况 —— right-left == 1
 *      2.简单复制粘贴容易出错 ，[left,mid] 复制过来却只将left改成right 导致[right,mid]左右边界互换
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/12/1
 **/
public class LeetCode34 {
    public static void main(String[] args) {
        int[] ints5 = new LeetCode34().searchRange(new int[]{1, 1, 10}, 1);
        int[] ints = new LeetCode34().searchRange(new int[]{5, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 10}, 8);
        int[] ints1 = new LeetCode34().searchRange(new int[]{5, 7, 7, 8, 10}, 8);
        int[] ints2 = new LeetCode34().searchRange(new int[]{5, 7, 7, 8, 10}, 6);
        int[] ints3 = new LeetCode34().searchRange(new int[]{1, 10}, 1);
        int[] ints4 = new LeetCode34().searchRange(new int[]{1, 10}, 10);
    }

    public int[] searchRange(int[] nums, int target) {
        Arrays.binarySearch(nums,target);
        //二分查找法
        //______________----_______________
        //不断找中点，直到中点==target，找不到返回(-1,-1)
        if (nums.length == 0 || nums[0] > target || nums[nums.length - 1] < target) {
            return new int[]{-1,-1};
        }
        int left = 0;
        int right = nums.length - 1;
        int mid = (left + right) >>> 1;
        while (nums[mid] != target) {
            /**
             * 错误点：忽略了二分查询的特殊情况 right - left == 1
             */
            if (right - left <= 1) {
                if (nums[right] != target) {
                    return new int[]{-1,-1};
                }
                mid = right;
                break;
            }
            if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
            mid = (left + right) >>> 1;
        }
        //中点与左右两点对比，都不相同，则返回(中点,中点)
        boolean flagLeft = mid == 0 || nums[mid] != nums[mid - 1];
        boolean flagRight = mid == nums.length - 1 || nums[mid] != nums[mid + 1];
        if (flagLeft && flagRight) {
            return new int[] {mid,mid};
        }
        //否则，三种情况：
        //1.中点在最左侧 _------------ 确定左边界，找右边界
        if (flagLeft) {
            return new int[] {mid,findRight(nums,mid,right,target)};
        }
        //2.中点在最右侧 ------------_ 确定右边界，找左边界
        /**
         * 错误点，简单复制粘贴 导致的左右边界互换问题
         */
        if (flagRight) {
            return new int[] {findLeft(nums,left,mid,target),mid};
        }
        //3.中点在中间   --------_---- 左边找左边界，右边找有边界
        return new int[] {findLeft(nums,left,mid,target),findRight(nums,mid,right,target)};

    }

    /**
     * 二分法查找数组指定区间 [left,right] 内等于target的元素的最左侧下标
     * @param nums
     * @param left
     * @param right
     * @param target
     * @return
     */
    private int findLeft(int[] nums, int left, int right, int target) {
        if (nums[left] == target) {
            return left;
        }
        /**
         * 错误点：忽略了二分查询的特殊情况 right - left == 1
         */
        if (right - left <= 1) {
            return right;
        }
        int mid = (left + right) >>> 1;
        if (nums[mid] >= target) {
            return findLeft(nums,left,mid,target);
        }
        return findLeft(nums,mid,right,target);
    }

    /**
     * 二分法查找数组指定区间 [left,right] 内等于target的元素的最右侧下标
     * @param nums
     * @param left
     * @param right
     * @param target
     * @return
     */
    private int findRight(int[] nums, int left, int right, int target) {
        if (nums[right] == target) {
            return right;
        }
        if (right - left <= 1) {
            return left;
        }
        int mid = (left + right) >>> 1;
        if (nums[mid] <= target) {
            return findRight(nums,mid,right,target);
        }
        return findRight(nums,left,mid,target);
    }
}
