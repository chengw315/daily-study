package daily;

/**
 * description 新21点，但是扑克范围值从1到W，上限从21点变为N点，手上的牌值>=K点就放弃抽牌，放弃抽牌后不超过上限N点的概率为多少？
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/6/3
 **/
public class LeetCode837 {

    public double new21Game(int N, int K, int W) {
        if(N >= K + W - 1) {
            return 1;
        }

        return 0;
    }
}
