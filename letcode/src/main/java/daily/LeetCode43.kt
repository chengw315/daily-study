package daily

import java.lang.StringBuilder

fun main() {
    val multiply1 = LeetCode43().multiply("9995623421", "0");
    //8.750278222753523e+21
    val multiply = LeetCode43().multiply("99956000421", "87541300031");
}
/**
 * description
 *
 * @author chengwj
 * @version 1.0
 * @date 2020/8/13
 **/
class LeetCode43 {

    fun multiply(num1:String, num2:String) : String {
        if (num1.equals("0") || num2.equals("0")) return "0"
        //长的乘数 兼 低位寄存器
        var a = intArrayOf(0)
        //短的乘数
        var b = intArrayOf(0)
        if(num1.length<=num2.length) {
            a = intArrayOf(num2)
            b = intArrayOf(num1)
        } else {
            a = intArrayOf(num1)
            b = intArrayOf(num2)
        }

        //高位寄存器
        var aux = IntArray(b.size){0}
        var carry = 0
        //a最低位*b
        for (i in a.size-1 downTo 0) {
            val x = a[a.size - 1]
            for (j in b.size-1 downTo 0) {
                val s = aux[j] + x * b[j] + carry
                aux[j] = s % 10
                carry = s / 10
            }
            //a左移一位，a头=aux尾，aux左移一位,aux头=进位标志
            leftShift(a)
            a[0] = aux[aux.size-1]
            leftShift(aux)
            aux[0] = carry
            carry=0
        }
        //高位拼低位
        val result = StringBuilder()
        if(aux[0] != 0) result.append(aux[0])
        for (i in 1 until aux.size)
            result.append(aux[i])
        for (i in 0 until a.size)
            result.append(a[i])
        return result.toString()
    }

    fun intArrayOf(string: String) : IntArray {
        val array = IntArray(string.length)
        for (i in string.indices) array[i] =  string[i] - '0'
        return array
    }
    fun leftShift(a:IntArray) {
        for (i in a.size-1 downTo 1) {
            a[i] = a[i-1]
        }
    }
}
