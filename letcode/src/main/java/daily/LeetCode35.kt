package daily

fun main() {
    val solution = Solution35()
    //2
    val searchInsert = solution.searchInsert(intArrayOf(1, 3, 5, 6), 5)
    //1
    val searchInsert1 = solution.searchInsert(intArrayOf(1, 3, 5, 6), 2)
    //4
    val searchInsert2 = solution.searchInsert(intArrayOf(1, 3, 5, 6), 7)
    //0
    val searchInsert3 = solution.searchInsert(intArrayOf(1, 3, 5, 6), 0)
}

/**
 * 给定一个值，找到它在有序数组的插入点下标（如果数组含此值，返回小标）
 */
class Solution35 {
    fun searchInsert(nums: IntArray, target: Int): Int {
        return binarySearch(nums,0, nums.size, target)
    }

    private fun binarySearch(nums: IntArray,begin:Int,end:Int, target: Int): Int {
        if(begin >= end) return begin
        val mid = (begin + end) /2
        if(nums[mid] == target) return mid
        else if(nums[mid] > target) return binarySearch(nums,begin,mid,target)
        else return binarySearch(nums,mid + 1,end,target)
    }
}