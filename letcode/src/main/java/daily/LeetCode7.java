package daily;


/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/2/23
 **/
public class LeetCode7 {

    public static void main(String[] args) {
        int reverse4 = new LeetCode7().reverse(1534236469);
        int reverse = new LeetCode7().reverse(123);
        int reverse1 = new LeetCode7().reverse(-123);
        int reverse2 = new LeetCode7().reverse(-120);
        int reverse3 = new LeetCode7().reverse(120000);
    }

    public int reverse(int x) {
        String s = String.valueOf(x);
        int endNotZero = s.length();
        for (int i = s.length() - 1; i >= 0; i++) {
            if (s.charAt(i) != 0) {
                endNotZero = i + 1;
                break;
            }
        }
        try {
            if (s.charAt(0) == '-') {
                return Integer.valueOf('-' + new StringBuilder(s.substring(1, endNotZero)).reverse().toString());
            } else {
                return Integer.valueOf(new StringBuilder(s.substring(0, endNotZero)).reverse().toString());
            }
        }catch (Exception e) {
            return 0;
        }
    }
}
