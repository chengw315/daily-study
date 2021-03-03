package weeks;

import java.util.Arrays;
import java.util.List;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/2/28
 **/
public class Week230 {

    public static void main(String[] args) {
        int i2 = new Week230().closestCost(new int[]{3, 10}, new int[]{2, 5}, 9);
        int i1 = new Week230().closestCost(new int[]{2, 3}, new int[]{4, 5, 100}, 18);
        int i = new Week230().closestCost(new int[]{1, 7}, new int[]{3, 4}, 10);
    }

//    public int minOperations(int[] nums1, int[] nums2) {
//        int sum1 = 0;
//        int[] count1 = new int[7];
//        int sum2 = 0;
//        int[] count2 = new int[7];
//        for (int i = 0; i < nums1.length; i++) {
//            sum1 += nums1.length;
//            count1[nums1[i]]++;
//        }
//        for (int i = 0; i < nums2.length; i++) {
//            sum2 += nums2.length;
//            count2[nums2[i]]++;
//        }
//        if (sum1 == sum2) {
//            return 0;
//        }
//        //可加数、可减数
//        int add1 = 0, sub1 = 0;
//        int add2 = 0, sub2 = 0;
//        for (int i = 1; i <= 6; i++) {
//            add1 += (6 - i) * count1[i];
//            sub1 += (i - 1) * count1[i];
//            add2 += (6 - i) * count2[i];
//            sub2 += (i - 1) * count2[i];
//        }
//        if ((sum1 < sum2 && sum1 + add1 < sum2 - sub2)
//                || (sum2 < sum1 && sum2 + add2 < sum1 - sub1)) {
//            return -1;
//        }
//        int[] dp = new int[Math.abs(sum2 - sum1)];
//        if (sum1 > sum2) {
//
//        }
//    }

    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        Arrays.sort(toppingCosts);
        int best = 0;
        for (int i = 0; i < baseCosts.length; i++) {
            int curTarget = target - baseCosts[i];
            if (curTarget < toppingCosts[0]) {
                best = best(baseCosts[i] + toppingCosts[0], best, target);
                best = best(baseCosts[i], best, target);
                continue;
            }
            boolean[] dp = new boolean[2 * curTarget + 1];
            int[] rest = new int[toppingCosts.length];
            for (int j = 0; j < rest.length; j++) {
                rest[j] = 2;
            }
            int length = toppingCosts.length;
            for (int j = 0; j < length; j++) {
                int toppingCost = toppingCosts[j];
                if (toppingCost > curTarget) {
                    length = j;
                    break;
                }
                dp[toppingCost] = true;
            }
            outer:
            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < length; k++) {
                    int i1 = j - toppingCosts[k];
                    if (i1 < 0) {
                        break;
                    }
                    if (dp[i1]) {
                        dp[j] = true;
                        if (j == curTarget) {
                            return target;
                        }
                        if (j > curTarget) {
                            break outer;
                        }
                        break;
                    }
                }
            }
            for (int j = 0; j <= curTarget; j++) {
                if (dp[curTarget - j]) {
                    best = best(best, curTarget - j + toppingCosts[0], target);
                    break;
                }
                if (dp[curTarget + j]) {
                    best = best(best, curTarget + j + toppingCosts[0], target);
                    break;
                }
            }
        }
        return best;
    }

    /**
     * a、b中最接近target的数
     *
     * @param a
     * @param b
     * @param target
     * @return
     */
    private int best(int a, int b, int target) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        int a1 = target - a;
        int b1 = target - b;
        if (Math.abs(a1) == Math.abs(b1)) {
            if (a1 > 0) {
                return a;
            }
            return b;
        }
        if (Math.abs(a1) > Math.abs(b1)) {
            return b;
        }
        return a;
    }

    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int num = 0;
        if (ruleKey.equals("type")) {
            num += items.stream().filter(item -> ruleValue.equals(item.get(0))).count();
        } else if (ruleKey.equals("color")) {
            num += items.stream().filter(item -> ruleValue.equals(item.get(1))).count();
        } else if (ruleKey.equals("name")) {
            num += items.stream().filter(item -> ruleValue.equals(item.get(2))).count();
        }
        return num;
    }
}
