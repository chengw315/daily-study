package daily

import java.util.*

fun main() {
    //16
    val i = SolutionLCP13().minimalSteps(arrayOf("S#O", "M..", "M.T"));
    //-1
    val i1 = SolutionLCP13().minimalSteps(arrayOf("S#O", "M.#", "M.T"));
    //17
    val i2 = SolutionLCP13().minimalSteps(arrayOf("S#O", "M.T", "M.."));
}

class SolutionLCP13 {

    data class Point(val x:Int,val y:Int)
    fun minimalSteps(maze: Array<String>): Int {
        var xS = 0
        var yS = 0
        var xT = 0
        var yT = 0
        var numofO = 0
        var numofM = 0
        val hashMapO = HashMap<Point, Int>()
        val hashMapM = HashMap<Point, Int>()
        for (i in maze.indices) {
            for (j in maze[i].indices) {
                when(maze[i][j]) {
                    'O'-> hashMapO.put(Point(i,j),numofO++)
                    'M'-> hashMapM.put(Point(i,j),numofM++)
                    'S'-> {
                        xS = i
                        yS = j
                    }
                    'T'-> {
                        xT = i
                        yT = j
                    }
                }
            }
        }
        //result = 所有最小(M->O)之和  +  最小(S->O->M0 + M1->T - M1->O)
        //需要统计的只有：
        //   1. S->所有O的最短距离；————>BFS
        val minS2Os = IntArray(numofO){Int.MAX_VALUE}
        //   2. 所有O->最近M的最短距离；————>BFS
        val minO2Ms = IntArray(numofO){Int.MAX_VALUE}
        val minPointO2Ms = Array<Point>(numofO) { Point(0,0) }
        //   2. 所有M->最近O的最短距离；————>BFS
        val minM2Os = IntArray(numofM){Int.MAX_VALUE}
        //   3. 所有M->T的最短距离；————BFS (2.和3.可以在同一轮BFS解决)
        val minM2Ts = IntArray(numofM){Int.MAX_VALUE}

        //BFS(S) S->所有O的最短距离
        val queue = LinkedList<Point>()
        var deep = 0
        queue.offer(Point(xS,yS))
        var colors = Array(maze.size) {BooleanArray(maze[0].length) {false} }
        while (!queue.isEmpty()) {
            val size = queue.size
            for (i in 0 until size) {
                val pop = queue.poll()
                if(colors[pop.x][pop.y]) continue
                colors[pop.x][pop.y] = true
                if(maze[pop.x][pop.y] == '#') continue
                if(maze[pop.x][pop.y] == 'O') {
                    minS2Os[hashMapO[pop]!!] = Math.min(deep, minS2Os[hashMapO[pop]!!])
                }
                if(pop.x > 0) queue.offer(Point(pop.x - 1,pop.y))
                if(pop.x < maze.size - 1) queue.offer(Point(pop.x + 1,pop.y))
                if(pop.y > 0) queue.offer(Point(pop.x,pop.y - 1))
                if(pop.y < maze[pop.x].length - 1) queue.offer(Point(pop.x,pop.y + 1))
                deep++
            }
        }

        //   2. 所有O->最近M的最短距离；————>BFS
        loop@ for (O in hashMapO.keys) {
            queue.clear()
            deep = 0
            queue.offer(O)
            colors = Array(maze.size) {BooleanArray(maze[0].length) {false} }
            while (!queue.isEmpty()) {
                val size = queue.size
                for (i in 0 until size) {
                    val pop = queue.pop()
                    if(colors[pop.x][pop.y]) continue
                    colors[pop.x][pop.y] = true
                    if(maze[pop.x][pop.y] == '#') continue
                    if(maze[pop.x][pop.y] == 'M') {
                        minO2Ms[hashMapO[O]!!] = deep
                        minPointO2Ms[hashMapO[O]!!] = pop
                        continue@loop
                    }
                    if(pop.x > 0) queue.offer(Point(pop.x - 1,pop.y))
                    if(pop.x < maze.size - 1) queue.offer(Point(pop.x + 1,pop.y))
                    if(pop.y > 0) queue.offer(Point(pop.x,pop.y - 1))
                    if(pop.y < maze[pop.x].length - 1) queue.offer(Point(pop.x,pop.y + 1))
                    deep++
                }
            }
        }

        loop2@for (M in hashMapM.keys) {
            queue.clear()
            deep = 0
            queue.offer(M)
            colors = Array(maze.size) {BooleanArray(maze[0].length) {false} }
            var findO = false
            var findT = false
            while (!queue.isEmpty()) {
                val size = queue.size
                for (i in 0 until size) {
                    val pop = queue.pop()
                    if(colors[pop.x][pop.y]) continue
                    colors[pop.x][pop.y] = true
                    if(maze[pop.x][pop.y] == '#') continue
                    if(!findO && maze[pop.x][pop.y] == 'O') {
                        minM2Os[hashMapM[M]!!] = deep
                        findO = true
                    }
                    if(!findT && maze[pop.x][pop.y] == 'T') {
                        minM2Ts[hashMapM[M]!!] = deep
                        findT = true
                    }
                    if(findO && findT) continue@loop2
                    if(pop.x > 0) queue.offer(Point(pop.x - 1,pop.y))
                    if(pop.x < maze.size - 1) queue.offer(Point(pop.x + 1,pop.y))
                    if(pop.y > 0) queue.offer(Point(pop.x,pop.y - 1))
                    if(pop.y < maze[pop.x].length - 1) queue.offer(Point(pop.x,pop.y + 1))
                    deep++
                }
            }
        }

        //最小(S->O->M0 + M1->T - M1->O)
        var minS2O2MAndM2TSubM2O = Int.MAX_VALUE
        for (i in minS2Os.indices) for (j in minO2Ms.indices) for (x in minM2Os.indices) for (y in minM2Ts.indices) minS2O2MAndM2TSubM2O = if(minPointO2Ms[j] == getKey(hashMapM,x)) minS2O2MAndM2TSubM2O else Math.min(minS2O2MAndM2TSubM2O, minS2Os[i] + minM2Os[j] - minM2Os[x] + minM2Ts[y])

        return minM2Os.sum() + minS2O2MAndM2TSubM2O
    }

    private fun getKey(hashMapM: HashMap<Point, Int>, x: Int): Point {
         for ((k,v) in hashMapM) {
             if (v == x) return k
         }
        return Point(1,1)
    }
}

