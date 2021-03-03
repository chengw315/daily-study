package daily;

import java.util.Objects;

/**
 * description n个房间，每个房间拥有一定的金额，不能进入相邻房间的前提下能够获取的最大金额是多少
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/5/29
 **/
public class LeetCode198 {

    public static void main(String[] args) {
        int[] a =  new int[]{55,72,209,143,216,220,122,115,87,227,220,161,79,230,55,56,56,178,124,88,202,65,102};
        System.out.println(rob(a));
        System.out.println(rob_dp(a));
    }

    public static int rob(int[] nums) {
        if(Objects.isNull(nums) || nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            return nums[0];
        }
        //先筛出必选和必不选
        //-1必选，0必不选,1未确定
        int[] isChosen = new int[nums.length + 1];
        for(int i = 0; i < nums.length; i++)
            isChosen[i] = 1;
        boolean changeFlag;
        do {
            changeFlag = false;
            for(int i = 0; i < nums.length; i++) {
                //跳过已确定的
                if(isChosen[i] <= 0) {
                    continue;
                }

                //特殊情况：第一个
                if(i == 0 && nums[0] >= nums[1] * isChosen[1]) {
                    isChosen[0] = -1;
                    isChosen[1] = 0;
                    changeFlag = true;
                    continue;
                }

                //特殊情况：最后一个
                if(i == nums.length - 1 && nums[nums.length - 1] >= nums[nums.length - 2] * isChosen[nums.length - 2]) {
                    isChosen[nums.length - 1] = -1;
                    isChosen[nums.length - 2] = 0;
                    changeFlag = true;
                    continue;
                }

                //如果一个数大于等于两边的数之和，必选，同时两边必不选
                if(i > 0 && i < nums.length - 1 && nums[i] >= nums[i - 1] * isChosen[i - 1] + nums[i + 1] * isChosen[i + 1]) {
                    isChosen[i] = -1;
                    isChosen[i - 1] = 0;
                    isChosen[i + 1] = 0;
                    changeFlag = true;
                }
            }

        }while (changeFlag);

        int result = 0;
        //必选之和并入结果
        for(int i = 0; i < nums.length; i++) {
            if(isChosen[i] < 0) {
                result += nums[i];
            }
        }

        //对于剩下的，每个区间错位比较获取最大
        //找到一个区间
        int left = -1;
        int right;
        while (true) {
            if(left >= nums.length - 1)
                break;
            //区间左——第一个未选择的
            while (isChosen[++left] <= 0 && left < nums.length) ;
            if(left == nums.length)
                break;
            right = left;
            //区间右——第一个已选择的
            while (isChosen[++right] > 0 && right < nums.length) ;

            int odd = 0;
            int even = 0;
            boolean isOdd = false;
            for (int i = left; i < right; i++) {
                if (isOdd) {
                    isOdd = false;
                    odd += nums[i];
                }
                else {
                    isOdd=true;
                    even += nums[i];
                }
            }
            left = right;
            result += Math.max(odd, even);
        }
        return result;
    }

    /**
     * 动态规划：n个房间的最大💴 = max（前n-2个房间 + 最后一个房间，前n-1个房间）
     */
    public static int rob_dp(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            return nums[0];
        }
        boolean change = false;
        int max1 = nums[0];
        int max2 = Math.max(nums[0],nums[1]);
        for(int i = 2; i < nums.length; i++) {
            if(change) {
                max2 = Math.max(max2 + nums[i], max1);
                change = false;
                continue;
            }
            max1 = Math.max(max1 + nums[i], max2);
            change = true;
        }
        return Math.max(max1, max2);
    }
}
