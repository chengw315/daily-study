package daily;

import java.math.BigInteger;
import java.util.*;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/1/22
 **/
public class LeetCode989 {

    public static void main(String[] args) {
        int[] ints4 = {0};
        List<Integer> list4 = new LeetCode989().addToArrayForm(ints4, 0);
        int[] ints3 = {0};
        List<Integer> list3 = new LeetCode989().addToArrayForm(ints3, 10000);
        int[] ints2 = {0};
        List<Integer> list2 = new LeetCode989().addToArrayForm(ints2, 5000);
        int[] ints = {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9};
        List<Integer> list = new LeetCode989().addToArrayForm(ints, 5);
        int[] ints1 = {1,2,0,0};
        List<Integer> list1 = new LeetCode989().addToArrayForm(ints1, 5);
    }

//    public List<Integer> addToArrayForm(int[] A, int K) {
//        LinkedList<Integer> stack = new LinkedList();
//        //进位标志
//        int carry = 0;
//        int temp;
//        for (int i = 0; i < 5; i++) {
//            temp = K % 10;
//            K = K / 10;
//            temp += carry;
//            if (A.length > i) {
//                temp += A[A.length - 1 - i];
//            }
//            carry = temp / 10;
//            stack.push(temp % 10);
//        }
//
//        for (int i = A.length - 6; i >= 0; i--) {
//            temp = A[i]  + carry;
//            carry = temp / 10;
//            stack.push(temp % 10);
//        }
//        if (carry > 0) {
//            stack.push(carry);
//        }
//        ArrayList<Integer> result = new ArrayList<>(stack.size());
//        boolean isHead = true;
//        while (!stack.isEmpty()) {
//            Integer pop = stack.pop();
//            //去掉开头的0
//            if (isHead && pop == 0) {
//                continue;
//            }
//            isHead = false;
//            result.add(pop);
//        }
//        //结果为0的情况
//        if (result.isEmpty()) {
//            result.add(0);
//        }
//        return result;
//    }

    /**
     * BigInteger(LeetCode don't support)
     * @param A
     * @param K
     * @return
     */
    public List<Integer> addToArrayForm(int[] A, int K) {
        StringBuilder builder = new StringBuilder(A.length);
        for (int i = 0; i < A.length; i++) {
            builder.append(A[i]);
        }
        BigInteger bigInteger = new BigInteger(builder.toString()).add(new BigInteger(String.valueOf(K)));
        String s = bigInteger.toString();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            result.add((int) s.charAt(i));
        }
        return result;
    }
}
