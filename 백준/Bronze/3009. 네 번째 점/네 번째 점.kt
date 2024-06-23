import java.util.*

fun main() {

    val xArr = IntArray(1_001)
    val yArr = IntArray(1_001)
    for (i in 0..2) {
        val st = StringTokenizer(readln(), " ")
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        xArr[a]++
        yArr[b]++
    }
    var x: Int = 0
    var y: Int = 0
    for (i in 1 .. 1000) {
        if (xArr[i] == 1) x = i
        if (yArr[i] == 1) y = i
    }
    println("$x $y")
}