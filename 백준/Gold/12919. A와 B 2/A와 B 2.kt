import java.util.*

fun main() {
    val target = readln()
    val origin = readln()

    val size = target.length

    val q: Queue<String> = LinkedList()
    q.offer(origin)

    var flag = false
    while (!q.isEmpty()) {
        val str = q.poll()
        if (str.length < size) continue
        if (str.equals(target)) {
            flag = true
            break
        }
        if (isAvailCommandOne(str)) {
            q.offer(commandOne(str))
        }
        if (isAvailCommandTwo(str)) {
            q.offer(commandTwo(str))
        }
    }

    if (flag) println(1) else println(0)
}

fun isAvailCommandOne(str: String): Boolean {
    return str[str.length - 1] == 'A'
}

fun commandOne(str: String): String {
    return str.substring(0, str.length - 1)
}

fun isAvailCommandTwo(str: String): Boolean {
    return str[0] == 'B'
}

fun commandTwo(str: String): String {
    val sb = StringBuilder()
    for (i in str.length - 1 downTo 1) {
        sb.append(str[i])
    }

    return sb.toString()
}