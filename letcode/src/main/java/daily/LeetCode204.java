package daily;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * description (0,n)区间内的质数数量
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/12/3
 **/
public class LeetCode204 {
    public static void main(String[] args) {
        int i = new LeetCode204().countPrimes(10);
        int i1 = new LeetCode204().countPrimes(1);
        int i2 = new LeetCode204().countPrimes(0);
    }
    public int countPrimes(int n) {
        boolean[] flags = new boolean[n];
        int result = 0;
        for (int i = 2; i < n; i++) {
            //质数，更新自己的倍数为所有非质数
            //非质数无需更新，因为
            if(!flags[i]) {
                result++;
                for (int j = 2 * i; j < n; j += i) {
                    flags[j] = true;
                }
            }
        }
        return result;
    }

    /**
     * description 将0元素移动到数组的末尾
     *
     * @author chengwj
     * @version 1.0
     * @date 2020/11/19
     **/
    public static class LeetCode283 {

        public void moveZeroes(int[] nums) {
            //双指针，除0之外的所有的元素全部前移，左指针代表当前移动至的位置，右指针代表当前要移动的位置
            int left = 0;
            for (int right = 0; right < nums.length; right++) {
                if (nums[right] != 0) {
                    nums[left++] = nums[right];
                }
            }
            for (; left < nums.length; left++) {
                nums[left] = 0;
            }
            //不要小看了简单题
    //        Queue<Integer> queue = new LinkedList<>();
    //        int zeroNum = 0;
    //        for (int i = 0; i < nums.length; i++) {
    //            if (nums[i] == 0) {
    //                zeroNum++;
    //                queue.offer(i);
    //            } else if(!queue.isEmpty()) {
    //                nums[queue.poll()] = nums[i];
    //                queue.offer(i);
    //            }
    //        }
    //        for (int i = nums.length - zeroNum; i < nums.length; i++) {
    //            nums[i] = 0;
    //        }
        }
    }

    /**
     * description 基本计算器
     *
     * @author chengwj
     * @version 1.0
     * @date 2020/8/20
     **/
    public static class LeetCode224 {

        public static void main(String[] args) {
            //63
            int calculate = new LeetCode224().calculate("(1+(44+5+ 2)- 3)-(6+8)");
        }

        public int calculate(String s) {
            int result = 0;
            char opt = '+';
            boolean buff = false;
            Stack<Boolean> temp = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == ' ') continue;
                if(s.charAt(i) == '(') {
                    temp.push(buff);
                    buff = opt == '-';
                } else if(s.charAt(i) == ')') buff = temp.pop();
                else if(s.charAt(i) == '+') opt = buff? '-':'+';
                else if(s.charAt(i) == '-') opt = buff? '+':'-';
                else {
                    int b = 0;
                    for (int j = i; j < s.length(); j++) {
                        if (s.charAt(j) < '0' || s.charAt(j) > '9') break;
                        b = b * 10 + s.charAt(j) - '0';
                        i = j;
                    }
                    if (opt == '+') {
                        result = result + b;
                    } else {
                        result = result - b;
                    }
                }
            }
            return result;
        }
    }

    /**
     * description 序列化和反序列化二叉树
     *
     * @author chengwj
     * @version 1.0
     * @date 2020/6/16
     **/
    public static class LeetCode297 {

        public static void main(String[] args) {
            TreeNode node1 = new TreeNode(1);
            TreeNode node2 = new TreeNode(2);
            TreeNode node3 = new TreeNode(3);
            node1.left = node2;
            node1.right = node3;
            TreeNode node4 = new TreeNode(4);
            TreeNode node5 = new TreeNode(5);
            node3.left = node4;
            node3.right = node5;
            String s = new LeetCode297().serialize(node1);
            TreeNode treeNode = new LeetCode297().deserialize("[1,2,3,null,null,4,5]");
        }

        public String serialize(TreeNode root) {
            ArrayList<Integer> result = new ArrayList<>();
            if(root == null) {
                result.add(null);
                return result.toString();
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            //层次遍历
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node == null) {
                    result.add(null);
                    continue;
                }
                result.add(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            }
            for(int i = result.size() - 1; i >= 0; i--) {
                if(result.get(i) != null) {
                    break;
                }
                result.remove(i);
            }
            return result.toString();
        }

        public TreeNode deserialize(String data) {
            if(data == null || data.length() <= 2) {
                return null;
            }
            String[] split = data.substring(1, data.length() - 1).split(",");
            if(split.length == 0 || split[0].indexOf("null") >= 0) {
                return null;
            }
            TreeNode root = new TreeNode(Integer.valueOf(split[0].trim()));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            for(int i = 1; i < split.length; i++) {
                TreeNode cur = queue.poll();
                if(split[i].indexOf("null") >= 0) {
                    cur.left = null;
                } else {
                    cur.left = new TreeNode(Integer.valueOf(split[i].trim()));
                    queue.offer(cur.left);
                }
                if(++i >= split.length || split[i].indexOf("null") >= 0) {
                    cur.right = null;
                } else {
                    cur.right = new TreeNode(Integer.valueOf(split[i].trim()));
                    queue.offer(cur.right);
                }
            }
            return root;
        }

    }
}
