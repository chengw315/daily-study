package daily

fun main() {
    val solution96 = Solution96()
    var i = 0
    //2
    i = solution96.numTrees(2)
    //5
    i = solution96.numTrees(3)
    //
    i = solution96.numTrees(4)
}

/**
 * 1，2，3..n 能构成的二叉搜索树有多少种
 */
class Solution96 {

    lateinit var cache:IntArray

    fun numTrees(n: Int): Int {
        cache = IntArray(n+1) {-1}
        cache[0] = 1
        return  numTreesCache(n)
    }

    fun numTreesCache(n: Int): Int {
        if(cache[n] >= 0) return cache[n]
        var result = 0
        for (i in 1 until n + 1) {
            result += numTreesCache(i - 1) * numTreesCache(n - i)
        }
        cache[n] = result
        return result
    }
}