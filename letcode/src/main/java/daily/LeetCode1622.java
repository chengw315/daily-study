package daily;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/11/5
 **/
public class LeetCode1622 {
    public static void main(String[] args) {
        Fancy fancy = new Fancy();
        fancy.append(2);
        fancy.addAll(3);
        fancy.append(7);
        fancy.multAll(2);
        int index = fancy.getIndex(0);
        fancy.addAll(3);
        fancy.append(10);
        fancy.multAll(2);
        int index1 = fancy.getIndex(0);
        int index2 = fancy.getIndex(1);
        int index3 = fancy.getIndex(2);
    }
    static class Fancy {
        private List<Data> datas;
        private List<Operation> options;
        public Fancy() {
            datas = new ArrayList<>();
            options = new ArrayList<>();
        }

        public void append(int val) {
            datas.add(new Data(val,options.size()));
        }

        public void addAll(int inc) {
            options.add(new Operation(true,inc));
        }

        public void multAll(int m) {
            options.add(new Operation(false,m));
        }

        public int getIndex(int idx) {
            if (idx >= datas.size()) {
                return -1;
            }
            Data data = datas.get(idx);
            return cleanData(data);
        }

        private int cleanData(Data data) {
            int osn = data.osn;
            long val = data.val;
            for (;osn < options.size(); osn++) {
                Operation operation = options.get(osn);
                if (operation.add1Multiply0) {
                    val += operation.val;
                    val %= 1000000007;
                } else {
                    val *= operation.val;
                    val %= 1000000007;
                }
            }
            data.val = val;
            data.osn = osn;
            return (int) val;
        }

        class Data {
            long val;
            int osn;

            public Data(long val,int osn) {
                this.val = val;
                this.osn = osn;
            }
        }

        class Operation {
            boolean add1Multiply0;
            int val;

            private Operation(boolean add1Multiply0, int val) {
                this.add1Multiply0 = add1Multiply0;
                this.val = val;
            }
        }
    }
}
