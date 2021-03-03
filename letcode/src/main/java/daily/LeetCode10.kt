package letcode10

fun main() {
    val solution = Solution10()
    //false
    val match9 = solution.isMatch("aaa", "aaaa")
    //true
    val match8 = solution.isMatch("ab", ".*..")
    //true
    val match7 = solution.isMatch("aab", "c*a*b*")
    //false
    val match = solution.isMatch("aa", "a")
    //true
    val match1 = solution.isMatch("aa", "a*")
    //true
    val match4 = solution.isMatch("aaa", ".*")
    //true
    val match5 = solution.isMatch("aba", ".*")
    //true
    val match2 = solution.isMatch("aab", "c*a*b")
    //false
    val match3 = solution.isMatch("mississippi", "mis*is*p*.")
    //false
    val match6 = solution.isMatch("a", ".*..a*")
}

/**
 * 含元字符.和*（.前字符匹配1~n次；*前字符匹配0~n次）的正则表达式匹配
 */
class Solution10 {
    fun isMatch(s: String, p: String): Boolean {
        return isMatch(s, s.length - 1, p, p.length - 1)
    }

    fun isMatch(s: String, endS: Int, p: String, endP: Int): Boolean {
        if (endS < 0 && canBeEmpty(p, endP)) return true
        if (endS < 0 || endP < 0) return false
        return p[endP] != '*' && (p[endP] == '.' || s[endS] == p[endP]) && isMatch(s, endS - 1, p, endP - 1)
                || p[endP] == '*' && endP > 0 && isMatchStar(s, endS, p, endP)
    }

    fun isMatchStar(s: String, endS: Int, p: String, endP: Int): Boolean {
        if (endS < 0 && canBeEmpty(p, endP)) return true
        if (endS < 0 || endP <= 0) return false
        //匹配*前一个字符 或是匹配*前第两个字符
        return (p[endP - 1] == '.' || s[endS] == p[endP - 1]) && isMatchStar(s, endS - 1, p, endP)
                || endP > 1 && isMatch(s, endS, p, endP - 2)
    }

    /**
     * 可以是空串
     */
    private fun canBeEmpty(p: String, endP: Int): Boolean {
        var flag = false
        for (i in endP downTo 0) {
            if (flag && p[i] != '*')
                flag = false
            else if (!flag && p[i] == '*')
                flag = true
            else if (!flag && p[i] != '*')
                return false
        }
        return true
    }

}