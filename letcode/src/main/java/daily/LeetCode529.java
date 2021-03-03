package daily;

import java.util.LinkedList;
import java.util.Queue;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/8/20
 **/
public class LeetCode529 {

    public static void main(String[] args) {
        char[][] chars = new LeetCode529().updateBoard(new char[][]{{'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}}, new int[]{3, 0});

    }

    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0 || board[0].length == 0) return board;
        //地雷
        if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        int [][]offset = new int[][]{{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
        //BFS更新
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(click);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                //只有E才会揭开
                if(board[poll[0]][poll[1]] != 'E') continue;
                int num = 0;
                for (int j = 0; j < offset.length; j++) {
                    int x = poll[0] + offset[j][0];
                    int y = poll[1] + offset[j][1];
                    if(x<0||y<0||x>board.length-1||y>board[0].length-1) continue;
                    if(board[x][y] == 'M') num++;
                }
                if(num == 0) board[poll[0]][poll[1]] = 'B';
                else board[poll[0]][poll[1]] = (char) ('0'+num);
                //揭开后只有B才会更新附近的方块
                if(board[poll[0]][poll[1]] != 'B') continue;
                for (int j = 0; j < offset.length; j++) {
                    int x = poll[0] + offset[j][0];
                    int y = poll[1] + offset[j][1];
                    if(x<0||y<0||x>board.length-1||y>board[0].length-1) continue;
                    queue.offer(new int[]{x, y});
                }
            }
        }

        return board;
    }
}
