package _useful;

import java.util.*;

/**
 * description 最接近（0，0）的K个点
 *   反思： 错误原因：
 *              1. 考虑不当   —— 取K大应使用容量K+1的优先队列，否则每次都需要取出最大的做比较再插入
 *              2. 前后不一致 —— 前面考虑了x、y轴为0的“特殊情况”，使用x+y作为距离（实际距离），后面却使用了x方+y方（非实际距离），两者根本没有可比性
 *              3. 缺乏思考   —— 仔细思考就知道 x、y轴为0根本就不算“特殊情况”
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/11/9
 **/
public class LeetCode973 {
    public static void main(String[] args) {
        int[][] kClosest = new LeetCode973().kClosest(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2);
    }
    class Point {
        int x;
        int y;
        int index;
        int distance;

        public Point(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
            /**
             * ——————————————————————问题点——————————————————————
             */
            this.distance = x * y == 0 ? x + y : x * x + y * y;
        }
    }

    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<Point> queue = new PriorityQueue<>(K + 2,((o1, o2) -> o2.distance-o1.distance));
        for (int i = 0; i < points.length; i++) {
            queue.offer(new Point(points[i][0],points[i][1],i));
            if (queue.size() == K + 1) {
                queue.poll();
            }
        }
        int[][] result = new int[K][2];
        int i = 0;
        Iterator<Point> iterator = queue.iterator();
        while (iterator.hasNext()) {
            Point next = iterator.next();
            result[i][0] = next.x;
            result[i++][1] = next.y;
        }
        return result;
        //X取前k，y取前k 算法错误，反例：K个（0,10000），K个（10000，0），与K个（1，1）
//        Point[] points1 = new Point[points.length];
//        for (int i = 0; i < points.length; i++) {
//            points1[i] = new Point(points[i][0], points[i][1], i);
//        }
//        HashSet<Point> set = new HashSet<>();
//        //x轴取前K
//        Arrays.sort(points1, (o1, o2) -> {
//            if (o1.x==o2.x) {
//                return o1.y - o2.y;
//            }
//            return o1.x-o2.x;
//        });
//        for (int i = 0; i < K; i++) {
//            set.add(points1[i]);
//        }
//        //第K大相同的
//        while ()
//        //y轴取前K
//        Arrays.sort(points1, ((o1, o2) -> {
//            if (o1.y==o2.y) {
//                return o1.x - o2.x;
//            }
//            return o1.y-o2.y;
//        }));
//        for (int i = 0; i < K; i++) {
//            set.add(points1[i]);
//        }
//        //去重，去重后 K<= 长度 <=2K
//
//        //计算距离，排序取前K
//
//        //over
    }
}
