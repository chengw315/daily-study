package daily;

/**
 * description 最长公共前缀
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/6/15
 **/
public class LeetCode14 {

    public static void main(String[] args) {
        String s = new LeetCode14().longestCommonPrefix(new String[]{"flower", "flow", "flight"});
        String s1 = new LeetCode14().longestCommonPrefix(new String[]{"dog","racecar","car"});
        String s11 = new LeetCode14().longestCommonPrefixLeastTime(new String[]{"flower", "flow", "flight"});
        String s12 = new LeetCode14().longestCommonPrefixLeastTime(new String[]{"dog","racecar","car"});
    }

    /**
     * 横向搜索(时间100%)
     * @param strs
     * @return
     */
    public String longestCommonPrefixLeastTime(String[] strs) {
        if(strs == null || strs.length == 0) {
            return "";
        }
        if(strs.length == 1) {
            return strs[0];
        }
        String result = strs[0];
        for(int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(result) != 0) {
                if(result.length() == 1) {
                    return "";
                }
                result = result.substring(0,result.length()-1);
            }
        }
        return result;
    }

    /**
     * 纵向搜索（时间87%）
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) {
            return "";
        }
        if(strs.length == 1) {
            return strs[0];
        }
        StringBuilder result = new StringBuilder();

        outer:
        for(int i = 0; i < strs[0].length(); i++) {
            char cur = strs[0].charAt(i);
            for(int j = 0; j < strs.length; j++) {
                if(strs[j].length() < i + 1 || strs[j].charAt(i) != cur) {
                    break outer;
                }
            }
            result.append(cur);
        }

        return result.toString();
    }

}
