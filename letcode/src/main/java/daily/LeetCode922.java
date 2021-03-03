package daily;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/11/12
 **/
public class LeetCode922 {
    public int[] sortArrayByParityII(int[] A) {
        int odd = 1;
        int even = 0;
        while(odd < A.length && even < A.length) {
            //奇偶各找到第一个错位的
            while (odd < A.length && A[odd] % 2 != 0) {
                odd += 2;
            }
            while (even < A.length && A[even] % 2 == 0) {
                even += 2;
            }
            //到头了？
            if(odd >= A.length || even >= A.length) {
                return A;
            }
            //交换
            int temp = A[odd];
            A[odd] = A[even];
            A[even] = temp;
            //继续找，直到到头
        }
        return new int[]{-1};
    }
}
