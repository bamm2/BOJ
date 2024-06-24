import java.util.*

fun main() {

    val T = readln().toInt()
    val sb = StringBuilder()

    var st: StringTokenizer
    var N: String
    var M: String
    var zeroCnt: Int
    var oneCnt: Int
    var maxCnt : Int
    var minCnt: Int
    var result: Int
    repeat(T) {
        st = StringTokenizer(readln(), " ")
        N = st.nextToken()
        M = st.nextToken()
        zeroCnt = 0
        oneCnt = 0
        for (i in N.indices) {
            if (N[i] != M[i]) {
                if (N[i] == '0') ++zeroCnt
                else ++oneCnt
            }
        }
        minCnt = Math.min(zeroCnt, oneCnt)
        maxCnt = Math.max(zeroCnt,oneCnt)
        result = minCnt + maxCnt-minCnt
        sb.append(result).append('\n')
    }

    println(sb.toString())
}