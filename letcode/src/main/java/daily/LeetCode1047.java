package daily;

/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/3/9
 **/
public class LeetCode1047 {
    public static void main(String[] args) {
        String s1 = new LeetCode1047().removeDuplicates("aaa");
        String s = new LeetCode1047().removeDuplicates("");
        String acdbbccaadca = new LeetCode1047().removeDuplicates("acdbbccaadca");
    }

    public String removeDuplicates(String S) {
        //爆破，N方
        boolean[] deleteFlags = new boolean[S.length()];
        while (true) {
            char last = '1';
            char lastLast = '1';
            int lastIndex = -1;
            int lastLastIndex = -1;
            boolean endFlag = true;
            for (int i = 0; i < S.length(); i++) {
                if (deleteFlags[i]) {
                    continue;
                }
                if (S.charAt(i) == last) {
                    deleteFlags[lastIndex] = true;
                    deleteFlags[i] = true;
                    last = lastLast;
                    lastIndex = lastLastIndex;
                    endFlag = false;
                } else {
                    last = S.charAt(i);
                    lastIndex = i;
                }
            }
            if (endFlag) {
                break;
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < deleteFlags.length; i++) {
            if(!deleteFlags[i]) {
                result.append(S.charAt(i));
            }
        }
        return result.toString();


//        StringBuilder result = new StringBuilder();
//        int left = 0,right = 0;
//        char last = S.charAt(0);
//        for (int i = 1; i < S.length(); i++) {
//            if (S.charAt(i) == last) {
//                //向右边扩展，合并所有重复字符串
//                while (S.charAt(++right) == last) {
//                }
//                //向两边扩展，合并新的重复字符串
//            } else {
//                last = S.charAt(i);
//                left = i;
//                right = i;
//            }
//        }
    }
}
