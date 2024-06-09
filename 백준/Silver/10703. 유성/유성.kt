import java.util.*

var map = emptyArray<CharArray>()
var list = arrayListOf<Int>()
var R = 0
var C = 0

fun main() = with(System.`in`.bufferedReader()) {
    val st = StringTokenizer(readLine(), " ")
    R = st.nextToken().toInt()
    C = st.nextToken().toInt()

    map = Array(R) { CharArray(C) { ' ' } }


    for (i in 0 until R) {
        val str = readLine()
        for (j in 0 until C) {
            map[i][j] = str[j]
            if (map[i][j] == 'X') {
                list.add(i * C + j)
            }
        }
    }

    val cnt = getDownCnt()

    down(cnt)

    val sb = StringBuilder()
    for (i in map.indices) {
        for (j in map[i].indices) {
            sb.append(map[i][j])
        }
        sb.append('\n')
    }

    println(sb.toString())
}

fun getAvailMoveCnt(r: Int, c: Int): Int {
    var cnt = 0
    while (true) {
        if (map[r + 1 + cnt][c] == 'X') return 3_000
        else if (map[r + 1 + cnt][c] == '#') break
        ++cnt
    }
    return cnt
}

fun getDownCnt(): Int {
    var minCnt = 3_000
    for (element in list) {
        val curR = element / C
        val curC = element % C
        minCnt = Math.min(minCnt, getAvailMoveCnt(curR, curC))
    }

    return minCnt
}

fun down(cnt: Int) {
    for(element in list.reversed()){
        val curR = element / C
        val curC = element % C
        map[curR][curC] = '.'
        map[curR+cnt][curC]= 'X'
    }
}
