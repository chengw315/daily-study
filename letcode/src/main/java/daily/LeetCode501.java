package daily;


import java.util.ArrayList;
import java.util.List;

/**
 * description 二叉搜索树的所有众数（左右子节点均可能等于父节点）
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/9/24
 **/
public class LeetCode501 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(-1);
        TreeNode node6 = new TreeNode(-1);
        TreeNode node7 = new TreeNode(2);
        root.setLeft(node1).setRight(node2);
        node2.setLeft(node3).setRight(node4);
        node3.setRight(node5);
        node5.setLeft(node6).setRight(node7);
        int[] mode = new LeetCode501().findMode(root);
    }

    public int[] findMode(TreeNode root) {
        Temp temp = findModes(root);
        if (temp.selfNum > temp.num) {
            return new int[]{temp.self};
        }
        if(temp.selfNum == temp.num  && temp.selfNum != 0 ) {
            temp.modes.add(temp.self);
        }
        List<Integer> modes = temp.modes;
        int[] result = new int[modes.size()];
        for (int i = 0; i < modes.size(); i++) {
            result[i] = modes.get(i);
        }
        return result;
    }

    public Temp findModes(TreeNode node) {
        if(node == null) {
            return new Temp(0,0,0,new ArrayList<>());
        }
        return compainte(findModes(node.left),findModes(node.right),node.val);
    }

    private Temp compainte(Temp left, Temp right, int val) {
        int selfNum = 1;

        selfNum += handleChildSelfNum(left, val);
        selfNum += handleChildSelfNum(right, val);

        //自身数成为新的众数？抛弃子树其他众数数据
        if (selfNum > left.num && selfNum > right.num) {
            return new Temp(0,val,selfNum,new ArrayList<>());
        }
        //众数一致，合并
        if(left.num == right.num) {
            List list = left.modes;
            list.addAll(right.modes);
            return new Temp(left.num,val,selfNum,list);
        }
        //抛弃众数数据少的子树
        Temp result = left.num > right.num ? left : right;
        result.selfNum = selfNum;
        result.self = val;
        return result;
    }

    private int handleChildSelfNum(Temp node, int val) {
        if (val == node.self) {
            //融合子树自身数
            return node.selfNum;

        } else if (node.selfNum == node.num && node.selfNum != 0) {
            //子树合并自身数
            node.modes.add(node.self);
        } else if (node.selfNum > node.num) {
            node.modes.clear();
            node.modes.add(node.self);
            node.num = node.selfNum;
        }
        //舍弃子树自身数
        return 0;
    }

    /**
     * 数据团（众数集、每个数的数量）
     */
    private class Temp{

        public Temp(int num, int self, int selfNum, List<Integer> modes) {
            this.num = num;
            this.self = self;
            this.selfNum = selfNum;
            this.modes = modes;
        }

        public int num;
        /**
         * 存储节点自身，selfNum为当前数量
         */
        public int self;
        public int selfNum;
        public List<Integer> modes;
    }
}
