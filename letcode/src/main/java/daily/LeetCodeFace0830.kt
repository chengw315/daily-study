package daily

fun main() {
    val solution0830 = Solution0830()
    solution0830
}

class Solution0830 {
    fun findMagicIndex(nums: IntArray): Int {
        for (i in nums.indices) if(nums[i]==i) return i
        return -1
    }
}