package daily;

import java.util.*;

/**
 * description  从数组取三数，三数之和最接近目标的值
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/6/24
 **/
public class LeetCode16 {

    public static void main(String[] args) {
        //2
        int i = new LeetCode16().threeSumClosest(new int[]{-1, 2, 1, -4}, 1);
        //0
        int i2 = new LeetCode16().threeSumClosest(new int[]{0,0,0}, 1);
        int i3 = new LeetCode16().threeSumClosest(new int[]{0,1,2}, 0);
        int i4 = new LeetCode16().threeSumClosest(new int[]{1,1,1,0}, 100);
        int i5 = new LeetCode16().threeSumClosest(new int[]{1,6,9,14,16,70}, 81);
    }

    public int threeSumClosest(int[] nums, int target) {

        Sum[] sums = new Sum[((nums.length - 1) * nums.length) >> 1];
        int count = 0;

        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                sums[count++] = new Sum(nums[i] + nums[j], i, j);
            }
        }

        Arrays.sort(sums, Comparator.comparingInt(a -> a.v));

        int distance = 10000;

        for(int i = 0; i < nums.length; i++) {
            int index = binarySearch(sums, target-nums[i]);
            distance = updateDistance(nums, i, sums, index, distance, target);
        }

        return target - distance;
    }

    private int updateDistance(int[] nums, int i, Sum[] sums, int index, int distance, int target) {
        int result = distance;
        if(i != sums[index].i && i != sums[index].j && Math.abs(result) > Math.abs(target - sums[index].v - nums[i])) {
            result = target - sums[index].v - nums[i];
        }
        int left = index, right = index;
        while (--left >= 0 && (i == sums[left].i || i == sums[left].j));
        while (++right <= sums.length - 1 && (i == sums[right].i || i == sums[right].j));
        if(left > 0 && i != sums[left].i && i != sums[left].j && Math.abs(result) > Math.abs(target - sums[left].v - nums[i])) {
            result = target - sums[left].v - nums[i];
        }
        if(right < sums.length && i != sums[right].i && i != sums[right].j && Math.abs(result) > Math.abs(target - sums[right].v - nums[i])) {
            result = target - sums[right].v - nums[i];
        }
        return result;
    }

    private int binarySearch(Sum[] sums, int target) {
        return binarySearch(sums,0,sums.length - 1,target);
    }

    private int binarySearch(Sum[] sums, int left, int right, int target) {
        if(left > right) {
            return right;
        }
        if(sums[left].v >= target) {
            return left;
        }
        if(sums[right].v <= target) {
            return right;
        }
        int mid = (left + right) >> 1;
        if(target == sums[mid].v) {
            return mid;
        }
        if(target < sums[mid].v) {
            return binarySearch(sums,left,mid,target);
        }
        return binarySearch(sums,mid + 1, right, target);
    }

    public class Sum {
        int v;
        int i;
        int j;
        public Sum(int v, int i, int j) {
            this.v = v;
            this.i = i;
            this.j = j;
        }
    }
}
