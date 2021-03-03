package daily

fun main() {
    val solution = Solution167()
    //4,5
    val twoSum4 = solution.twoSum(intArrayOf(1,2,3,4,4,8,9,75), 8)
    //2,3
    val twoSum3 = solution.twoSum(intArrayOf(5,25,75), 100)
    //1，2
    val twoSum = solution.twoSum(intArrayOf(2, 7, 11, 15), 9)
    //1,5
    val twoSum2 = solution.twoSum(intArrayOf(-995, -856, 456, 7856, 65454), 64459)
}

/**
 * 两数之和——找到数组中两数之和等于目标的索引（索引以1开始）,输出一个答案
 */
class Solution167 {
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        var a = 0
        var b = 0
        for (i in numbers.indices) {
            b = numbers.binarySearch(target - numbers[i])
            if(b == i) b = numbers.binarySearch(target - numbers[i], b + 1)
            if(b > 0) {
                b++
                a = i + 1
                break
            }
        }
        return intArrayOf(a,b)
    }
}