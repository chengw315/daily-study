import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    GlobalScope.launch {
        for (i in (1..100))
            println(i)
    }
    GlobalScope.launch {
        for (i in (100..200))
            println(i)
    }
    GlobalScope.launch {
        for (i in (200..300))
            println(i)
    }
    GlobalScope.launch {
        for (i in (300..400))
            println(i)
    }
    Thread.sleep(2000L)
}
main()