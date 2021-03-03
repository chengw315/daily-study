package daily;

/**
 * description 判断数字是否回文数（正向反向大小一样），前提：不将数字转换为字符串
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/6/10
 **/
public class LeetCode9 {

    public static void main(String[] args) {
        assert new LeetCode9().isPalindrome(121);
        assert !new LeetCode9().isPalindrome(-121);
        assert !new LeetCode9().isPalindrome(10);
    }

    public boolean isPalindrome(int x) {
        //负数肯定不是回文数
        if(x < 0) {
            return false;
        }
        //个位数肯定是回文数
        if(x < 10) {
            return true;
        }
        //最高位 最低位
        int high = 1000000000,low = 1;
        while(x / high == 0) {
            high /= 10;
        }

        while (high >= low) {
            if((x / high) % 10 != (x / low) % 10) {
                return false;
            }
            high /= 10;
            low *= 10;
        }
        return true;
    }
}
