import java.util.*

fun main() {

    val sb = StringBuilder()

    while (true) {
        val st = StringTokenizer(readln(), " ")
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        if (a == 0 && b == 0) break
        if (a > b) {
            sb.append("Yes").append('\n')
        } else {
            sb.append("No").append('\n')
        }
    }
    println(sb.toString())
}