import java.util.*

fun main() {
    val sb = StringBuilder()
    var tc = 1
    while (true) {
        val input = readln()
        if (input.contains('-')) break
        val stack = Stack<Char>()

        input.forEach {
            if (stack.isEmpty()) {
                stack.push(it)
            } else {
                if (stack.peek() == '{' && it == '}') {
                    stack.pop()
                } else {
                    stack.push(it)
                }
            }
        }
        var cnt = 0
        var isClosed = false
        while (stack.isNotEmpty()) {
            if (stack.pop() == '}') {
                if (isClosed) {
                    isClosed = false
                    cnt++
                } else {
                    isClosed = true
                }
            } else { // '{'
                if (isClosed) {
                    isClosed = false
                } else {
                    isClosed = true
                    cnt++
                }
            }
        }
        sb.appendLine("${tc++}. ${cnt}")
    }
    println(sb)

}