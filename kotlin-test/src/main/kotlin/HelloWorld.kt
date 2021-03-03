import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

fun foo() = flow<Int> { emit(1)
    emit(2)
    emit(3)}

fun main() = runBlocking {
    val job = GlobalScope.launch {
        delay(1000L)
        println("after 1 second")
    }
    val deferred = async { }
    deferred.await()
    withTimeoutOrNull(250) {
        println(foo()
                .buffer() //缓存发射出的元素（元素的发射不需要等待直到上一个发射的元素的处理结束）
                .conflate() //只处理最新发射的元素
                //.collectLatest {  } 处理最新发射的元素，同时会提前结束上一个元素的处理
                .map { it+1 } //对新发射的元素进行+1转换
                .filter { it>1 } //过滤小于1的元素
                .take(2) //只取前两个发射的元素
                .transform { emit(it.toString()) }
                .reduce{a, b -> a+b})
    }

    val channel = Channel<Int>(3) //阻塞队列（使用挂起代替阻塞）
    channel.send(1)
    val receive = channel.receive()

    job.join()
    job.cancel()
    println("hello world")
    val test = Test()
    test.test("s")
//    val node = BinarySearchTreeNode()
    Thread.sleep(2000L)
}

data class Node(var v:Int, var next: Node?)

//fun BinarySearchTreeNode.pub() {
//    this.left()
//    val node = Node(1, Node(2, null))
//    node.next = Node(1,null)
//    node.v = 2
//}
