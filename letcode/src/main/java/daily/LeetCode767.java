package daily;

import java.util.Arrays;
import java.util.Comparator;

/**
 * description 重排字符串，让相邻的字符不相同，做不到返回空串
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/11/30
 **/
public class LeetCode767 {

    public static void main(String[] args) {
        String aab = new LeetCode767().reorganizeString("aab");
        String aaab = new LeetCode767().reorganizeString("aaab");
        String z = new LeetCode767().reorganizeString("aaaaaaaaaaazzzzzzzzzzzzssssssssddddddd");
    }

    public String reorganizeString(String S) {
        //先考虑做不到的情况，出现最多的字符的出现次数 > 整个字符串长度的一半+1（奇数长度）
        //                                         >=整个字符串长度的一半+1（偶数长度）
        Count[] count = new Count[26];
        for (int i = 0; i < 26; i++) {
            count[i] = new Count();
            count[i].c = (char) (i + 'a');
        }
        for (int i = 0; i < S.length(); i++) {
            count[S.charAt(i) - 'a'].count++;
        }
        int max = 0;
        for (int i = 0; i < 26; i++) {
            max = Math.max(count[i].count,max);
        }
        if (max >= (S.length()+3) >>> 1) {
            return "";
        }
        //然后按位插就ok了
        Arrays.sort(count, Comparator.comparingInt(o->o.count));
        char[] chars = new char[S.length()];
        int index = 0;
        for (int i = 25; i >= 0; i--) {
            while (count[i].count > 0) {
                chars[index] = count[i].c;
                index += 2;
                if (index >= S.length()) {
                    index = 1;
                }
                count[i].count--;
            }
        }
        return String.valueOf(chars);
    }

    class Count{
        int count;
        char c;
    }

//    private Boolean cannotReorganizeString(String s) {
////        int[]
//        //字符统计信息后续有用，代码内联
//    }
}
