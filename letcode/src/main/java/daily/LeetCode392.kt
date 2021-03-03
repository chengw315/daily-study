package daily

fun main() {
    val solution392 = Solution392()
    val subsequence = solution392.isSubsequence("abc", "ahbgdc")
    //false
    val subsequence1 = solution392.isSubsequence("axc", "ahbgdc")
}

class Solution392 {
    fun isSubsequence(s: String, t: String): Boolean {
        if(s.isBlank()) return true
        var offset = 0
        for (i in t.indices)  if(t[i]==s[offset] && ++offset == s.length) return true
        return false
    }
}