import java.util.*

fun main() {
    val T = readln().toInt()
    val sb = StringBuilder()
    var st: StringTokenizer

    var list: MutableList<Int>
    repeat(T) {
        st = StringTokenizer(readln(), " ")
        var J = st.nextToken().toInt()
        val N = st.nextToken().toInt()
        list = mutableListOf()
        repeat(N) {
            st = StringTokenizer(readln(), " ")
            list.add(st.nextToken().toInt() * st.nextToken().toInt())
        }
        Collections.sort(list, Comparator.reverseOrder())
        for (i in list.indices) {
            J -= list[i]
            if (J <= 0) {
                sb.appendLine(i + 1)
                break;
            }
        }
    }
    println(sb.toString())
}