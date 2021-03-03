package daily

fun main() {
    val solution = Solution309()
    //10
    val i8 = solution.maxProfit(intArrayOf(8,6,4,3,3,2,3,5,8,3,8,2,6))
    //13
    val i7 = solution.maxProfit(intArrayOf(1,7,2,4,11))
    //6
    val i4 = solution.maxProfit(intArrayOf(3, 3, 5, 0, 0, 3, 1, 4))
    //3
    val i = solution.maxProfit(intArrayOf(1, 2, 3, 0, 2))
    //3
    val i2 = solution.maxProfit(intArrayOf(1, 4, 2))
    //1
    val i3 = solution.maxProfit(intArrayOf(2, 1, 2, 0, 1))
    //6
    val i5 = solution.maxProfit(intArrayOf(1, 4, 2, 7))
    //10
    val i6 = solution.maxProfit(intArrayOf(1, 2, 7, 4, 11))
}

/**
 * 买卖股票的最佳时期（含冰冻期）
 */
class Solution309 {
    fun maxProfit(prices: IntArray): Int {
        if(prices.size < 2) {
            return 0
        }

        if(prices.size == 2) {
            return if(prices[1] - prices[0] > 0) prices[1] - prices[0] else 0
        }

        var result = 0
        var hold = false
        var freeze = false
        var buy = 0
        var i = 0
        while (i < prices.size - 3) {
            if(freeze) {
                freeze = false
                i++
                continue
            }
            //空仓，观望：明天跌 或 明天涨但后天有抄底的机会
            if(!hold && (downTomorrow(prices,i)
                    || dltTomorrow(prices, i) <= dltTomorrow(prices,i+2) && dltTomorrow(prices,i) + dltTomorrow(prices,i+1) <= 0)) {
                i++
                continue
            }

            //空仓，买
            if(!hold) {
                buy = prices[i++]
                hold = true
                continue
            }

            //持仓，卖：明天跌而且后天没涨回来 或 明天涨但后天有抄底的机会
            if(hold && (
                            downTomorrow(prices,i) && dltTomorrow(prices,i) + dltTomorrow(prices,i+1) <= 0
                                    || upTomorrow(prices,i) && dltTomorrow(prices, i) <= dltTomorrow(prices,i+2) && dltTomorrow(prices,i) + dltTomorrow(prices,i+1) <= 0
                            )) {
                hold = false
                freeze = true
                result += prices[i++] - buy
                continue
            }

            //观望
            i++
        }

        //最后三天的决策
        //持仓，只能卖一次，最高那天卖
        if(hold) {
            result += Math.max(prices[prices.size - 3], Math.max(prices[prices.size - 2],prices[prices.size - 1])) - buy
        } else if(freeze) {
            //空仓冰冻期 Math.max(第三天-第二天，0)
            result += Math.max(dltTomorrow(prices,prices.size-2),0)
        } else {
            //空仓，Math.max(第二天-第一天，第三天 - 前两天中低的一天，0）
            val best = prices[prices.size - 1] - Math.min(prices[prices.size - 2], prices[prices.size - 3])
            result += Math.max(prices[prices.size-2] - prices[prices.size - 3],Math.max(best,0))
        }
        return result
    }

    private fun dltTomorrow(prices: IntArray, i: Int) = prices[i + 1] - prices[i]

    private fun upTomorrow(prices: IntArray, i: Int): Boolean {
        return prices[i + 1] > prices[i]
    }

    private fun downTomorrow(prices: IntArray, i: Int): Boolean {
        return prices[i + 1] <= prices[i]
    }
}