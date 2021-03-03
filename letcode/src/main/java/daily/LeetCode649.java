package daily;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * description dota2参议院，每一轮每一个参议员可行使一次权力
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/12/11
 **/
public class LeetCode649 {

    public static void main(String[] args) {
        //todo 未通过，预期Radiant
        String drrdrdrdrddrdrdr = new LeetCode649().predictPartyVictory("DRRDRDRDRDDRDRDR");

        String rd = new LeetCode649().predictPartyVictory("RD");
        String rdd = new LeetCode649().predictPartyVictory("RDD");
        //假设先行的是x个，后行的是x+y个     x——x+y数量优势 x-y —— y数量优势
        //                                 1——2  2——4  3——5 4——7  反正绝对两倍以内
        String ddrrr = new LeetCode649().predictPartyVictory("DDRRR");
        String rdrdrdrdr = new LeetCode649().predictPartyVictory("RDRDRDRDR");
    }

    public String predictPartyVictory(String senate) {
        boolean[] isBanned = new boolean[senate.length()];
        //1. 先超过半数者胜，反例DDRRR，理解错误，每一轮所有参议员都会行使权力
        Queue<Integer> queueR = new LinkedBlockingQueue<>();
        Queue<Integer> queueD = new LinkedBlockingQueue<>();
        for (int i = 0; i < senate.length(); i++) {
            if (senate.charAt(i) == 'R') {
                queueR.add(i);
            } else if (senate.charAt(i) == 'D') {
                queueD.add(i);
            } else {
//                throw new Exception("参数异常");
            }
        }
        //开始角逐（R视角）
        //必胜态 RD RRDDD RRRDDDD
        //先爆破，爆破不行再说
        while (queueR.size() * queueD.size() != 0) {
            //绝对数量优势
            int sizeR = queueR.size();
            int sizeD = queueD.size();
            if (sizeR >= 2 * sizeD) {
                return "Radiant";
            } else if (sizeD >= 2 * sizeR) {
                return "Dire";
            }

            Iterator<Integer> iteratorD = queueD.iterator();
            Iterator<Integer> iteratorR = queueR.iterator();
            while (iteratorR.hasNext() || iteratorD.hasNext()) {
                //各取一个，小的先行使权力（还有权力的话）
                int d = -1, r = -1;
                if (iteratorD.hasNext()) {
                    d = iteratorD.next();
                }
                if (iteratorR.hasNext()) {
                    r = iteratorR.next();
                }
                if (d < r) {
                    if (d != -1 && !isBanned[d] && !queueR.isEmpty()) {
                        isBanned[queueR.poll()] = true;
                    }
                    if (r != -1 && !isBanned[r] && !queueD.isEmpty()) {
                        isBanned[queueD.poll()] = true;
                    }
                } else if (r < d) {
                    if (r != -1 && !isBanned[r] && !queueD.isEmpty()) {
                        isBanned[queueD.poll()] = true;
                    }
                    if (d != -1 && !isBanned[d] && !queueR.isEmpty()) {
                        isBanned[queueR.poll()] = true;
                    }
                } else if (d == -1 && r == -1){
                    break;
                }
            }
        }
        if (queueD.size() == 0) {
            return "Radiant";
        }
        return "Dire";
    }
}
