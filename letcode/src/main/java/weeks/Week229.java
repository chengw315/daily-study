package weeks;

/**
 * description 第229场周赛
 *
 * @author chengwj
 * @version 1.0
 * @date 2021/2/21
 **/
public class Week229 {
    public static void main(String[] args) {
        int i1 = new Week229().longestPalindrome("cacb", "cbba");
        int i2 = new Week229().longestPalindrome("ab", "ab");
        int i3 = new Week229().longestPalindrome("aa", "bb");
        int i = new Week229().maximumScore(new int[]{-5, -3, -3, -2, 7, 1}, new int[]{-10, -5, 3, 4, 6});
//        new Week229().
        String s = new Week229().mergeAlternately("", "");
        String s2 = new Week229().mergeAlternately("abc", "abcdedef");
    }

    /**
     * todo
     * @param word1
     * @param word2
     * @return
     */
    public int longestPalindrome(String word1, String word2) {
        String word2Reverse = new StringBuilder(word2).reverse().toString();
        int max = 0;
        for (int i = 0; i < word1.length(); i++) {
            for (int j = word1.length(); j > i; j--) {
                if (word2Reverse.contains(word1.substring(i, j))) {
                    max = Math.max(max, j - i);
                    break;
                }
            }
        }
        if (max == 0) {
            return 0;
        }
        return max * 2 + 1;
    }

    public int maximumScore(int[] nums, int[] multipliers) {
        int n = nums.length;
        int[][] dp = new int[n][2];
        int cur = 0;
        int replace = 1;
        int m = multipliers.length;
        for (int i = 0; i < m; i++) {
            dp[i][cur] = Math.max(nums[i] * multipliers[m - 1], nums[i + n - m] * multipliers[m - 1]);
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < m - i; j++) {
                dp[j][replace] = Math.max(dp[j][cur] + nums[j + n - m + i] * multipliers[m - i - 1], dp[j + 1][cur] + nums[j] * multipliers[m - i - 1]);
            }
            int temp = replace;
            replace = cur;
            cur = temp;
        }
        return dp[0][cur];
    }

    public int[] minOperations(String boxes) {
        int[] result = new int[boxes.length()];
        for (int i = 0; i < boxes.length(); i++) {
            for (int j = 0; j < boxes.length(); j++) {
                if (i != j && boxes.charAt(j) == '1') {
                    result[i] += Math.abs(i - j);
                }
            }
        }
        return result;
    }

    public String mergeAlternately(String word1, String word2) {
        StringBuilder stringBuilder = new StringBuilder();
        int length1 = word1.length();
        int length2 = word2.length();
        int min = Math.min(length1, length2);
        for (int i = 0; i < min; i++) {
            stringBuilder.append(word1.charAt(i));
            stringBuilder.append(word2.charAt(i));
        }
        for (int i = min; i < length1; i++) {
            stringBuilder.append(word1.charAt(i));
        }
        for (int i = min; i < length2; i++) {
            stringBuilder.append(word2.charAt(i));
        }
        return stringBuilder.toString();
    }
}
