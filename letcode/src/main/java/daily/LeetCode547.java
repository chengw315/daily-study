package daily;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/1/7
 **/
public class LeetCode547 {

    public static void main(String[] args) {
        int circleNum2 = new LeetCode547().findCircleNum(new int[][]{{1, 0, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}});
        int circleNum1 = new LeetCode547().findCircleNum(new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}});
        int circleNum = new LeetCode547().findCircleNum(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}});
    }

    int[] parent;

    public int findCircleNum(int[][] isConnected) {
        if (isConnected == null || isConnected.length == 0) {
            return 0;
        }
        int n = isConnected.length;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    union(i, j);
                }
            }
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (find(i) == i) {
                result++;
            }
        }
        return result;
    }

    public int find(int n) {
        if (parent[n] != n) {
            parent[n] = find(parent[n]);
        }
        return parent[n];
    }

    public void union(int a, int b) {
        parent[find(b)] = find(a);
    }
}
