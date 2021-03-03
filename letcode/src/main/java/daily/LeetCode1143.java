package daily;

/**
 * description 最长公共子序列 todo 理解并掌握dp算法的思路
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/11/9
 **/
public class LeetCode1143 {

    public static void main(String[] args) {
        boolean b = new LeetCode1143().longestCommonSubsequence("abcde", "adce") == 3;
        boolean b1 = new LeetCode1143().longestCommonSubsequence("abcde", "ace") == 3;
    }

    /**
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int [][] dp =new int [text1.length()+1][text2.length()+1];
        for(int i=1;i<=text1.length();i++){
            for(int j=1;j<=text2.length();j++){
                if(text1.charAt(i-1)==text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                    dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }
//    public int longestCommonSubsequence(String text1, String text2) {
//        int[][] dp = new int[text1.length()][text1.length()];
//        for (int i = 0; i < text1.length(); i++) {
//            for (int j = 0; j < text1.length(); j++) {
//                dp[i][j] = -1;
//            }
//        }
//        int[] cache = new int[26];
//        for (int i = 0; i < cache.length; i++) {
//            cache[i] = -1;
//        }
//        for (int i = text2.length()-1; i >= 0 ; i--) {
//            cache[text2.charAt(i) - 'a'] = i;
//        }
//        for (int i = 0; i < text1.length(); i++) {
//            dp[i][i + 1] = cache[text1.charAt(i)];
//        }
//        int max = 0;
//        for (int i = 0; i < text1.length() - 3; i++) {
//            for (int j = i + 2; j < text1.length(); j++) {
//                if (dp[i + 1][j] == -1 && dp[i][j - 1] == -1) {
//                    dp[i][j] = -1;
//                } else if (dp[i][j - 1] != -1 && text1.charAt(j - 1) == text2.charAt(dp[i][j - 1] + j - i)) {
//                    dp[i][j] = dp[i][j - 1];
//                } else if (dp[i + 1][j] != -1 && text1.charAt(i) == text2.charAt(dp[i + 1][j] - 1)) {
//                    dp[i][j] = dp[i + 1][j] - 1;
//                } else {
//                    dp[i][j] = text2.indexOf(text1.substring(i, j));
//                }
//            }
//        }
//        return max;
//    }

    /**
     * 反思————————审题不清————————————————
     * 最长重复子序列，而非子串
     */
//    public int longestCommonSubsequence(String text1, String text2) {
//
//        //爆破 n的四次方
////        int max = 0;
////        for (int i = 0; i < text1.length(); i++) {
////            for (int j = i + 1; j < text1.length(); j++) {
////                if(text2.indexOf(text1.substring(i,j)) != -1) {
////                    max = Math.max(max,j - i);
////                }
////            }
////        }
////        return max;
//        //爆破优化：
//        //  1. 让i，j 利用 i，j-1的结果 缓存 i,j-1匹配位置 DP算法 外层的两层遍历 O（N方）-> O(N)
//        //  2. 使用字符串查找算法 KMP或DFA 查找由 O(MN) -> O(N+M)
//        //  3. 再度优化 DFA查找的同时记录最高状态，对text2进行text1（i=0，j=text1.length）的匹配，顺带把(i=0;j=1~text1.length)全部匹配了一遍
//        //          O((N)(M+N))
//
//        //DP算法  缓存 i,j的匹配位置
//        int[][] dp = new int[text1.length()][text1.length()];
//        for (int i = 0; i < text1.length(); i++) {
//            for (int j = 0; j < text1.length(); j++) {
//                dp[i][j] = -1;
//            }
//        }
//        int max = 0;
//        for (int i = 0; i < text1.length() - 3; i++) {
//            for (int j = i + 2; j < text1.length(); j++) {
//                if (dp[i + 1][j] == -1 && dp[i][j - 1] == -1) {
//                    dp[i][j] = -1;
//                } else if (dp[i][j - 1] != -1 && text1.charAt(j - 1) == text2.charAt(dp[i][j - 1] + j - i)) {
//                    dp[i][j] = dp[i][j - 1];
//                } else if (dp[i + 1][j] != -1 && text1.charAt(i) == text2.charAt(dp[i + 1][j] - 1)) {
//                    dp[i][j] = dp[i + 1][j] - 1;
//                } else {
//                    dp[i][j] = text2.indexOf(text1.substring(i, j));
//                }
//            }
//        }
//        return max;
//        //DFA算法
//    }


}
