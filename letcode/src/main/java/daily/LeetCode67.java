package daily;

/**
 * description 二进制求和
 *
 * 字符串 a "111"
 * 字符串 b "101"
 * 结果 "1100"
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/6/23
 **/
public class LeetCode67 {

    public static void main(String[] args) {
        //1100
        String s = new LeetCode67().addBinary("111", "101");
        //0
        String s1 = new LeetCode67().addBinary("0", "0");

        String s2 = new LeetCode67().addBinary("1", "111");
    }

    public String addBinary(String a, String b) {
        //进位标志
        int CF = 0;
        String longer = a.length() >= b.length() ? a : b;
        String shorter = a.length() < b.length() ? a : b;

        StringBuilder result = new StringBuilder();

        int i = 1;

        //都有数据的低位相加
        for(; i <= shorter.length(); i++) {
            int x = longer.charAt(longer.length() - i) - '0';
            int y = shorter.charAt(shorter.length() - i) - '0';
            int sum = x + y + CF;
            CF = sum / 2;
            result.append(sum % 2);
        }

        //并入长数据的高位
        for(; i <= longer.length(); i++) {
            int x = longer.charAt(longer.length() - i) - '0';
            int sum = x + CF;
            CF = sum / 2;
            result.append(sum % 2);
        }

        //并入进位标志（如果有）
        if(CF != 0) {
            result.append('1');
        }

        //反转
        return result.reverse().toString();
    }
}
