package daily;

import java.util.HashMap;
import java.util.Map;

/**
 * description 根据先序遍历和中序遍历结果，逆推二叉树
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/6/18
 **/
public class LeetCode105 {


    public static void main(String[] args) {
        TreeNode node = new LeetCode105().buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
    }

    Map<Integer,Integer> map;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        map = new HashMap(inorder.length);
        for(int i = 0; i < inorder.length; i++) {
            map.put(inorder[i],i);
        }
        return buildTree(preorder,0,inorder,0,inorder.length);
    }

    public TreeNode buildTree(int[] preorder, int preIndex,int[] inorder,int inBegin,int inEnd) {
        if(inBegin >= inEnd) {
            return null;
        }
        int val = preorder[preIndex];
        int leftNum = map.get(val) - inBegin;

        TreeNode node = new TreeNode(val);
        node.left = buildTree(preorder,preIndex + 1,inorder, inBegin,inBegin + leftNum);
        node.right = buildTree(preorder,preIndex + 1 + leftNum, inorder,inBegin+leftNum + 1,inEnd);
        return node;
    }
}
