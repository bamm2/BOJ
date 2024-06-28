import java.util.*

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }

    val list = mutableListOf<Int>()
    val st = StringTokenizer(readln(), " ")
    for (i in 0 until n) {
        list.add(st.nextToken().toInt())
    }

    Collections.sort(list, Comparator.reverseOrder())

    println(list[k-1])
}