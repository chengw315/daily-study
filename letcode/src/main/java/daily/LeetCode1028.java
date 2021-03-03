package daily;

/**
 * description 根据 先序遍历序列 还原 二叉树，（子节点先左后右）
 * 输入："1-2--3--4-5--6--7"  （其中-代表深度）
 * 输出：[1,2,5,3,4,6,7]
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/6/18
 **/
public class LeetCode1028 {

    public static void main(String[] args) {
        TreeNode treeNode1 = new LeetCode1028().recoverFromPreorder("1-2--3--4-5--6--7");
        TreeNode treeNode2 = new LeetCode1028().recoverFromPreorder("1-2--3---4-5--6---7");
        TreeNode treeNode3 = new LeetCode1028().recoverFromPreorder("1-401--349---90--88");
        TreeNode treeNode4 = new LeetCode1028().recoverFromPreorder("10-7--8");
    }


    public TreeNode recoverFromPreorder(String S) {

        if(S == null || S.length() == 0) {
            return null;
        }
        char[] chars = S.toCharArray();

        int buffer = 0;
        int i = 0;
        while (chars[i] != '-') {
            buffer = buffer * 10 + chars[i++] - '0';
            if(i == chars.length) {
                return new TreeNode(buffer);
            }
        }
        TreeNode root = new TreeNode(buffer);

        //每个深度存一个TreeNode
        TreeNode[] nodes = new TreeNode[1000];

        nodes[0] = root;

        //遍历字符串
        int deep = 0;
        buffer = 0;
        for (; i < chars.length; i++) {
            // 数字，加入buffer
            if(chars[i] != '-') {
                buffer = buffer * 10 + chars[i] - '0';
                continue;
            }

            //- 清缓存&&更新深度
            if(buffer > 0) {
                TreeNode cur = new TreeNode(buffer);
                nodes[deep] = cur;
                //使cur成为上一级深度的节点的子节点
                if(nodes[deep - 1].left == null) {
                    nodes[deep - 1].left = cur;
                } else {
                    nodes[deep - 1].right = cur;
                }
                buffer = 0;
                deep = 0;
            }
            deep++;

        }
        //清最后的缓存
        if(buffer > 0) {
            TreeNode cur = new TreeNode(buffer);
            nodes[deep] = cur;
            //使cur成为上一级深度的节点的子节点
            if(nodes[deep - 1].left == null) {
                nodes[deep - 1].left = cur;
            } else {
                nodes[deep - 1].right = cur;
            }
        }
        return root;
    }
}
