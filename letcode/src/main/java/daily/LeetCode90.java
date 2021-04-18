package daily;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/3/31
 **/
public class LeetCode90 {
    public static void main(String[] args) {

    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        //计数
        int[] count = new int[21];
        for (int i = 0; i < nums.length; i++) {
            count[nums[i] + 10]++;
        }
        List<List<Integer>>[] lists = new ArrayList[nums.length + 1];
        lists[0] = new ArrayList<>();
        lists[0].add(new ArrayList<>());
        for (int i = 1; i <= nums.length; i++) {
            List<List<Integer>> list = lists[i - 1];
            for (List l : list) {
                l.size();
//                for (int j = 0; j < ; j++) {
//
//                }
//                lists[i].add()
            }

        }
        return null;
    }
}
