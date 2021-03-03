package daily

import java.util.*

fun main() {
    val solution = Solution785()
    //true
    val b = solution.isBipartite(arrayOf(intArrayOf(1, 3), intArrayOf(0, 2), intArrayOf(1, 3), intArrayOf(0, 2)))
    //false
    val b2 = solution.isBipartite(arrayOf(intArrayOf(1,2, 3), intArrayOf(0, 2), intArrayOf(0,1, 3), intArrayOf(0, 2)))
    //true
    val b3 = solution.isBipartite(arrayOf(intArrayOf(1), intArrayOf(0, 3), intArrayOf(3), intArrayOf(1, 2)))
    //true
    val b4 = solution.isBipartite(arrayOf(intArrayOf(3), intArrayOf(2, 4), intArrayOf(1), intArrayOf(0, 4),intArrayOf(1, 3)))
}

/**
 * 二分图判定
 */
class Solution785 {

    fun isBipartite(graph: Array<IntArray>): Boolean {

        val color = IntArray(graph.size) { -1 }
        val stack = Stack<Int>()
        for(i in graph.indices) {
            if(color[i] >= 0) continue
            stack.push(i)
            while (!stack.empty()) {
                val pop = stack.pop()
                if(color[pop] < 0) {
                    color[pop] = 0
                }
                for(j in graph[pop].indices) {
                    if(color[pop] == color[graph[pop][j]])
                        return false
                    if(color[graph[pop][j]] < 0) {
                        color[graph[pop][j]] = (color[pop]+1)%2
                        stack.push(graph[pop][j])
                    }
                }
            }
        }
        return true
    }

}