//package letcode300;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Set;
//
///**
// * description 按要求补齐数组（已排序的正整数数组），
// * 一个正整数 n 。从 [1, n] 区间内选取任意个数字 补充 到 nums 中，
// * 使得 [1, n] 区间内的任何数字都可以用 nums 中某几个数字的和来表示
// *
// * @author chengwj
// * @version 1.0
// * @date 2020/12/29
// **/
//public class LeetCode330 {
//    public int minPatches(int[] nums, int n) {
//        //穷举nums在补充前，子集和的可能情况（集合子集数 = 2^集合长度） 否决
//        //对于1~n，每个数字，子集能否满足 n *
//        boolean[] flags = new boolean[n + 1];
//        int notNum = 0;
//        //预处理：保存Map<每个数字，List<补充它能额外表示哪个数字>>
//        HashMap<Integer, Set<Integer>> cache = new HashMap<>(n);
//        //然后n轮循环，每次补充一个数字（贪心，这个数字能让最多的数字能够被表示）
//        while (notNum > 0) {
//            int max = 0;
//            for (int i = 0; i < n; i++) {
//                Set<Integer> integers = cache.get(i);
//                if (max < integers.size()) {
////                    max
//                }
//            }
//        }
//        //得到结果
//    }
//}
