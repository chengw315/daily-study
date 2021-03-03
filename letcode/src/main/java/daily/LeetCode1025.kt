package daily

fun main() {
    val solution1025 = Solution1025()
    val b = solution1025.divisorGame(1)
    val b1 = solution1025.divisorGame(2)
    val b2 = solution1025.divisorGame(3)
    val b3 = solution1025.divisorGame(4)
    val b4 = solution1025.divisorGame(5)
}

class Solution1025 {
    fun divisorGame(N: Int): Boolean {
        val array = BooleanArray(N+1)
        array[1] = false
        for (i in 2 until array.size) {
            array[i] = false
            if(!array[i-1]) {
                array[i] = true
                continue
            }
            for (j in 2 until Math.sqrt(i.toDouble()).toInt() + 1) {
                if(i % j != 0) continue
                if(!array[i-j] || !array[i-i/j]) {
                    array[i] = true
                    break
                }
            }
        }
        return array[N];
    }
}