import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine(), " ")

    val R = st.nextToken().toInt()
    val C = st.nextToken().toInt()

    val map = Array(R) { CharArray(C) { ' ' } }

    for (i in 0 until R)
        map[i] = br.readLine().toCharArray()

    val list = mutableListOf<String>()

    var sb = StringBuilder()
    for (i in 0 until R) {
        for (j in 0 until C) {
            if (map[i][j] == '#') {
                if (sb.length > 1) list.add(sb.toString())
                sb.clear()
            } else {
                sb.append(map[i][j])
            }
        }
        if (sb.length >= 2) list.add(sb.toString())
        sb.clear()
    }

    for (i in 0 until C) {
        for (j in 0 until R) {
            if (map[j][i] == '#') {
                if (sb.length > 1) list.add(sb.toString())
                sb.clear()
            } else {
                sb.append(map[j][i])
            }
        }
        if (sb.length >= 2) list.add(sb.toString())
        sb.clear()
    }

    Collections.sort(list)

    println(list.get(0))
}