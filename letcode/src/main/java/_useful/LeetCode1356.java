package _useful;

/**
 * description 数组中每个元素有多少个比它小的元素
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/10/26
 **/
public class LeetCode1356 {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        //方案1：copy数组并排序，然后遍历一次，使用HashMap记录每个元素有多少个比它小的元素
        //      然后遍历原数组，依次从map中取出对应数据进行填充
        //     空间O(n) 时间O(n*lgn)
        //方案2：从优化空间的角度考虑，空间O(1)
        //     a. 对于每个元素，遍历整个数组，统计比它小的元素，时间O(n2)
        //     b. 能否保持时间O(n*lgn)?
        //方案3：从优化时间的角度考虑，时间O(n)?
        //     a. 时间O(n) 计数排序,由于0 <= nums[i] <= 100，可行，空间O(100)≈O(1)   ———— 最终答案

        //用于计数排序
        int[] count = new int[101];
        //用于缓存 此下标数据有多少个比它小的元素
        int[] cache = new int[101];
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
        }
        cache[0] = 0;
        for (int i = 1; i < count.length; i++) {
            cache[i] = cache[i - 1] + count[i - 1];
        }
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = cache[nums[i]];
        }
        return result;
    }
}
