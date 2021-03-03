package daily;

import java.util.Arrays;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/11/6
 **/
public class LeetCode1365 {

    public static void main(String[] args) {
        new LeetCode1365().sortByBits(new int[]{0,1,2,3,4,5,6,7,8});
    }

    public int[] sortByBits(int[] arr) {
        Data[] datas = new Data[arr.length];
        for (int i = 0; i < arr.length; i++) {
            datas[i] = new Data(arr[i]);
        }
        Arrays.sort(datas, (o1, o2) -> {
            if(o1.num1 == o2.num1) {
                return o1.val - o2.val;
            }
            return o1.num1 - o2.num1;
        });
        int[] result = new int[arr.length];
        for (int i = 0; i < datas.length; i++) {
            result[i] = datas[i].val;
        }
        return result;
    }

    class Data {
        int val;
        int num1;

        public Data(int val) {
            this.val = val;
            this.num1 = getNum1(val);
        }

        private int getNum1(int val) {
            int result = val & 1;
            for (int i = 0; i < 16; i++) {
                val >>>= 1;
                result += val & 1;
            }
            return result;
        }
    }
}
