import java.util.*

var N = 0
var Q = 0
var st = StringTokenizer(readLine(), " ")

fun main() = with(System.`in`.bufferedReader()) {
    N = st.nextToken().toInt()
    Q = st.nextToken().toInt()

    val arr = IntArray(N)
    val calculateArr = IntArray(N)
    st = StringTokenizer(readLine(), " ")
    for (i in 0 until N) {
        arr[i] = st.nextToken().toInt()
    }

    var sum = 0
    for (i in 0 until N) {
        calculateArr[i] = arr[i] * arr[(i + 1) % N] * arr[(i + 2) % N] * arr[(i + 3) % N]
        sum += calculateArr[i]
    }

    val sb = StringBuilder()
    st = StringTokenizer(readLine(), " ")
    for (i in 0 until Q) {
        val idx = st.nextToken().toInt() - 1

        for (j in -3..0) {
            val candidateIdx = ((idx + j + N) % N)
            calculateArr[candidateIdx] *= -1
            sum += (calculateArr[candidateIdx] * 2)
        }
        sb.append(sum).append('\n')
    }
    println(sb.toString())
}