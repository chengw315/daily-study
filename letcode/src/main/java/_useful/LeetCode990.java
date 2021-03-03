package _useful;


/**
 * description 等式方程组的可满足性，输入等式方程（String，类似"a==b"固定四位，第一位和第四位固定为变量-小写字母，二三位为==或!=）数组，判断是否满足（存在解）
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/6/8
 **/
public class LeetCode990 {

    public static void main(String[] args) {
        String err = "错误";
//        Assert.isTrue(!new LeetCode990().equationsPossible(new String[]{"a==b", "b!=c", "c==a"}),err);
//        Assert.isTrue(new LeetCode990().equationsPossible(new String[]{"c==c","b==d","x!=z"}),err);
//        Assert.isTrue(!new LeetCode990().equationsPossible(new String[]{"c!=c"}),err);
//        Assert.isTrue(new LeetCode990().equationsPossible(new String[]{"c==c","f!=a","f==b","b==c"}),err);
//        Assert.isTrue(!new LeetCode990().equationsPossible(new String[]{"a==b","e==c","b==c","a!=e"}),err);
//        Assert.isTrue(!new LeetCode990().equationsPossible(new String[]{"f==a","a==b","f!=e","a==c","b==e","c==f"}),err);
    }


    /**
     * 并查集算法（时间战胜100%）
     * @param equations
     * @return
     */
    public boolean equationsPossible(String[] equations) {
        byte[] vars = new byte[27];
        for (int i = 0; i < vars.length; i++) {
            vars[i] = (byte) i;
        }

        for (String equation :
                equations) {
            //== 并
            if(equation.charAt(1) == '=') {
                int index1 = equation.charAt(0) - 'a';
                int index2 = equation.charAt(3) - 'a';
                union(vars,index1,index2);
            }
        }

        for (String equation :
                equations) {
            int index1 = equation.charAt(0) - 'a';
            int index2 = equation.charAt(3) - 'a';

            //!= 查
            if(equation.charAt(1) == '!') {
                if(find(vars,index1) == find(vars,index2)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 查——路径压缩，查到根节点后直接并入根节点
     * @param parent
     * @param index
     * @return
     */
    private byte find(byte[] parent, int index) {
        if(parent[index] != index) {
            parent[index] = find(parent,parent[index]);
        }
        return parent[index];
    }

    private void union(byte[] parent, int index1, int index2) {
        parent[find(parent,index1)] = find(parent,parent[index2]);
    }
}
