package daily

fun main() {
    val solution = Solution154()
    //1
    val array = solution.minArray(intArrayOf(3, 4, 5, 1, 2))
    //1
    val array1 = solution.minArray(intArrayOf(1, 2, 3, 4, 5))
    //5
    val array2 = solution.minArray(intArrayOf(5, 15, 5, 5, 5))
    //4
    val array4 = solution.minArray(intArrayOf(5, 15, 4, 5, 5))
    //1
    val array3 = solution.minArray(intArrayOf(5, 5, 5, 1, 2))
}

class Solution154 {
    fun minArray(numbers: IntArray): Int {
        //未旋转
        if (numbers[0] < numbers[numbers.size-1]) return numbers[0]
        return lbs(numbers,0,numbers.size-1)
    }

    /**
     * 向左二分查找 (left,right]
     */
    fun lbs(numbers: IntArray,left:Int,right:Int):Int {
        if(left>=right-1) return numbers[right]
        val mid = (left+right)/2
        return if(numbers[right] > numbers[mid]) lbs(numbers,left,mid)
        else if(numbers[right] < numbers[mid]) lbs(numbers,mid,right)
        else Math.min(lbs(numbers, left, mid),lbs(numbers, mid, right))
    }
}