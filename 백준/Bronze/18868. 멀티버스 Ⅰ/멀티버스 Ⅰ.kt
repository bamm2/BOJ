import java.util.*

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }

    val map = Array(n) { IntArray(m) }

    for (i in 0..<n) {
        val st = StringTokenizer(readln(), " ")
        for (j in 0..<m) {
            map[i][j] = st.nextToken().toInt()
        }
    }

    var cnt = 0
    for (i in 0..<n) {
        for (j in i + 1..<n) {
            var sign = false
            loop@for (a in 0..<m) {
                for (b in a + 1..<m) {
                    if (!((map[i][a] < map[i][b] && map[j][a] < map[j][b])
                        || (map[i][a] > map[i][b] && map[j][a] > map[j][b])
                        || (map[i][a] == map[i][b] && map[j][a] == map[j][b])
                    )){
                        sign = true
                        break@loop
                    }
                }
            }
            if(!sign) cnt++
        }
    }
    println(cnt)
}