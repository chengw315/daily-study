package daily

fun main() {
    val solution207 = Solution207()
    val canFinish3 = solution207.canFinish(4, arrayOf(intArrayOf(0, 1),intArrayOf(1, 3),intArrayOf(3, 1),intArrayOf(3, 2)))
    val canFinish = solution207.canFinish(2, arrayOf(intArrayOf(1, 0)))
    val canFinish1 = solution207.canFinish(4, arrayOf(intArrayOf(1, 0),intArrayOf(2, 0),intArrayOf(3, 1),intArrayOf(3, 2)))
    val canFinish2 = solution207.canFinish(4, arrayOf(intArrayOf(1, 0),intArrayOf(2, 0),intArrayOf(3, 1),intArrayOf(0, 3)))
}

class Solution207 {

    private lateinit var array:Array<ArrayList<Int>>
    private lateinit var isReached:BooleanArray
    private lateinit var color:BooleanArray

    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        array = Array<ArrayList<Int>>(numCourses){ ArrayList()}
        color = BooleanArray(numCourses) {false}
        isReached = BooleanArray(numCourses) {false}
        for (i in prerequisites.indices) {
            array[prerequisites[i][1]].add(prerequisites[i][0])
        }
        for (i in 0 until numCourses) {
            if (!isReached[i] && !dfs(i)) return false
        }
        return true
    }

    fun dfs(s:Int):Boolean {
        isReached[s] = true
        color[s] = true
        for (i in array[s].indices) {
            if(color[array[s][i]]) return false
            if(!dfs(array[s][i])) return false
        }
        color[s] = false
        return true
    }
}