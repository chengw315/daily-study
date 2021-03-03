//package letcode1000;
//
//import java.util.HashMap;
//
///**
// * description 最小不兼容性，把数组平分成k份，每一份最大元素-最小元素之和最小情况
// *
// * @author chengwj
// * @version 1.0
// * @date 2020/12/7
// **/
//public class LeetCode5619 {
//
//    public int minimumIncompatibility(int[] nums, int k) {
//        //先考虑不能分的情况
//        if (nums == null || nums.length == 0 || nums.length % k != 0) {
//            return -1;
//        }
//        //其中一个数出现次数 > k
//        HashMap<Integer, Integer> map = new HashMap<>(32);
//        for (int i = 0; i < nums.length; i++) {
//            if (!map.containsKey(nums[i])) {
//                map.put(nums[1],1);
//                continue;
//            }
//            Integer num = map.get(nums[i]);
//            if (num >= k) {
//                return -1;
//            }
//            map.put(nums[i],num + 1);
//        }
//        //必然能分
//        return doMinimumIncompatibility(nums,k);
//    }
//
//    /**
//     * 把数组平分成k份，每一份最大元素-最小元素之和的最小值
//     * @param nums
//     * @param k
//     * @return
//     */
//    private int doMinimumIncompatibility(int[] nums, int k) {
//        /**
//         * 穷举
//         * 平分k份的方案有 k的n次方 最坏情况，长度16，k=4  4的16次方 不可取
//         *
//         * 贪心
//         * 从小到大排序，然后平分成k份
//         * 1 2 | 3 4 | 5 6 | 7 8 | 9 10  k=5 违反同一个子集中没有相同元素
//         *
//         * 找规律
//         * 1 1 2 2 3 3 6 8  分四份 12 | 23 | 13 | 68 而不是 12 12 36 38 贪心不可取
//         * 先分重复的  123 12368 然后均摊，思路无法继续
//         * 1 1 2 3 6 6 8 8  分四份 7
//         *
//         *必须穷举出所有情况 12 12 33 68
//         * 16 2 每组8个
//         * 最小的
//         * 最大的
//         * 16 8 每组2个 16 * 16的缓存
//         * 穷举所有组合
//         * 16 4 最坏情况，四组每组四个元素 16*16*16*16的缓存（2的16次方）
//         * 缓存使用16进制法 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
//         *
//         */
//        //两组的情况
//        if (k == 2) {
//
//        }
//        //8组的情况
//        //4组的情况
//    }
//}
