package daily;

/**
 * description 有序数组转二叉搜索树
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/7/3
 **/
public class LeetCode108 {

    public static void main(String[] args) {
        TreeNode treeNode = new LeetCode108().sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        TreeNode treeNode2 = new LeetCode108().sortedArrayToBST(new int[]{});
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums,0,nums.length - 1);
    }

    public TreeNode sortedArrayToBST(int[] nums, int begin, int end) {
        if(begin > end) {
            return null;
        }
        int mid = begin + (end - begin + 1) / 2;
        TreeNode result = new TreeNode(nums[mid]);
        result.left = sortedArrayToBST(nums,begin,mid - 1);
        result.right = sortedArrayToBST(nums,mid + 1,end);
        return result;
    }
}
