package daily

import java.lang.StringBuilder

fun main() {
    val solution415 = Solution415()
    val addStrings = solution415.addStringsBeautiful("12456", "65421")
}

class Solution415 {
    fun addStrings(num1: String, num2: String): String {
        val result = StringBuilder()
        var carryFlag = 0
        val longNum = if (num1.length > num2.length) num1 else num2
        val shortNum = if (num1.length <= num2.length) num1 else num2
        val longL = Math.max(num1.length,num2.length)
        val shortL = Math.min(num1.length,num2.length)
        val offset = longL - shortL
        for (i in shortL-1 downTo 0) {
            val a = longNum[i + offset] - '0'
            val b = shortNum[i] - '0'
            result.append((a+b+carryFlag)%10)
            carryFlag = (a+b+carryFlag)/10
        }
        for (i in offset-1 downTo 0) {
            val a = longNum[i] - '0'
            result.append((a+carryFlag)%10)
            carryFlag = (a+carryFlag)/10
        }
        if(carryFlag>0) result.append(carryFlag)
        return result.reverse().toString()
    }

    fun addStringsBeautiful(num1: String, num2: String): String {
        val result = CharArray(maxOf(num1.length,num2.length)+1)
        var carryFlag = 0
        for (i in result.size-1 downTo 0) {
            var sum = carryFlag
            val offset1 = num1.length - result.size + i
            val offset2 = num2.length - result.size + i
            if(offset1 >= 0) sum+=num1[offset1]-'0'
            if(offset2 >= 0) sum+=num2[offset2]-'0'
            result[i] = '0' + sum % 10
            carryFlag = sum / 10
        }
        return if(result[0]=='0') String(result,1,result.size-1) else String(result)
    }
}