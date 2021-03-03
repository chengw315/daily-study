package daily;

import java.util.Arrays;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/11/10
 **/
public class LeetCode31 {
    public static void main(String[] args) {
        new LeetCode31().nextPermutation(new int[]{2,3,1});
        new LeetCode31().nextPermutation(new int[]{3,2,1});
        new LeetCode31().nextPermutation(new int[]{1,3,2});
    }
    public void nextPermutation(int[] nums) {
        // 132->213
        //非最大序列-> 例3112->3121，31122->31212 倒序查找，找到第一个 后面元素>前面元素的换掉即可
        if (nums[nums.length-1] > nums[nums.length-2]) {
            int temp = nums[nums.length-1];
            nums[nums.length-1] = nums[nums.length-2];
            nums[nums.length-2] = temp;
            return;
        }
        for (int i = nums.length-1; i > 0 ; i--) {
            if (nums[i] > nums[i-1]) {
                for (int j = nums.length-1; j >= i; j--) {
                    //找到一个比i-1小的元素，互换，剩下的从小到大排序
                    if (nums[j] > nums[i-1]) {
                        int temp = nums[j];
                        nums[j] = nums[i-1];
                        nums[i-1] = temp;
                        Arrays.sort(nums,i,nums.length);
                        return;
                    }
                }
            }
        }
        //最大序列->倒序即可
        for (int i = 0; i < nums.length / 2; i++) {
            int temp = nums[i];
            nums[i] = nums[nums.length - 1 - i];
            nums[nums.length - 1 - i] = temp;
        }
        return;
    }
}
