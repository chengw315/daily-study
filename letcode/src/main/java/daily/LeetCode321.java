package daily;

/**
 * description 从两个数组取数，所能拼成的最大数（要求只能顺序取数，数组1，2，3，只能取12，13，23，123，而不能取321）
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/12/2
 **/
public class LeetCode321 {

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        //贪心+回溯，贪心（优先取最大的数），同样大的优先取最前面的，取不够k个时进行回溯
        //最坏情况 两个递增数组，k=两个数组之和 时间O(k2)
        //todo
        return null;
    }
}
