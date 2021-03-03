package daily

import java.util.*

fun main(args: Array<String>) {
    val solution = Solution()
    //[3 4 5 6]
    val divingBoard = solution.divingBoard(1, 2, 3)
    //[]
    val divingBoard2 = solution.divingBoard(1, 2, 0)
    val divingBoard3 = solution.divingBoard(2,1118596,979)
}

/**
 * 跳水板
 *
 */
class Solution {
    fun divingBoard(shorter: Int, longer: Int, k: Int): IntArray {
        if(k == 0) {
            return IntArray(0);
        }
        val min = if(shorter > longer) longer else shorter
        val max = if(shorter > longer) shorter else longer
        val hashSet = HashSet<Int>()
        for(i:Int in 0..k) {
            hashSet.add(i*(max-min)+min*k)
        }
        val result = IntArray(hashSet.size)
        var offset = 0
        for (i in hashSet) {
            result[offset++]=i;
        }
        result.sort();
        return result
    }
}