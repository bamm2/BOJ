import java.util.*

data class Point(val h: Int, var cnt: Int)

fun main() {
    val N = readln().toInt()
    val stack = Stack<Point>()
    var result = 0L

    repeat(N) {
        val h = readln().toInt()
        val curr = Point(h, 1)
        if (stack.isEmpty()) {
            stack.push(curr)
        } else {
            while (stack.isNotEmpty()) {
                val top = stack.peek()
                if (top.h > h) {
                    break
                }
                result += top.cnt
                if (top.h == h) {
                    curr.cnt += top.cnt
                }
                stack.pop()
            }

            if(stack.isNotEmpty()) result++
            stack.push(curr)
        }
    }
    println(result)
}