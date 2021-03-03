package _useful;

/**
 * description 对于数组中的每个数 获取除了自身以外所有其他数的乘积 不能使用除法，时间O(n),空间O(1，不包含输出空间)
 *
 * 知识点：
 *      后缀积 —— 使用输出空间存储后缀积
 *      前缀积 —— 使用单个变量存储，一次性前缀积
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/6/4
 **/
public class LeetCode238 {

    public static void main(String[] args) {
        System.out.println(new LeetCode238().productExceptSelf(new int[]{1,2,3,4}));
    }

    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        result[nums.length - 1] = nums[nums.length - 1];
        for(int i = nums.length - 2; i > 0; i--) {
            result[i] = result[i + 1] * nums[i];
        }
        int prefix = nums[0];
        result[0] = result[1];
        for(int i = 1; i < nums.length - 1; i++) {
            result[i] = result[i + 1] * prefix;
            prefix *= nums[i];
        }
        result[nums.length - 1] = prefix;
        return result;
    }
}
