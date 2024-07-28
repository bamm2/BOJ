fun main() {
    val (V, E) = readln().split(" ").map { it.toInt() }

    val distMap = Array(V) { IntArray(V) { 1_000_000 } }
    val result = Array(V) { Array(V) { i -> i + 1 } }

    repeat(E) {
        val (from, to, cost) = readln().split(" ").map { it.toInt() }
        distMap[from - 1][to - 1] = cost
        distMap[to - 1][from - 1] = cost
    }

    for (k in 0 until V) { // 경
        for (i in 0 until V) { // 출
            if (i == k) continue
            for (j in 0 until V) { // 도
                if (i == j || k == j) continue
                if (distMap[i][j] > distMap[i][k] + distMap[k][j]) {
                    distMap[i][j] = distMap[i][k] + distMap[k][j]
                    result[i][j] = result[i][k]
                }
            }
        }
    }

    val sb = StringBuilder()
    for (i in 0 until V) { 
        for (j in 0 until V) {
            sb.append(if (i == j) "-" else result[i][j])
            if (j != V - 1) sb.append(" ")
        }
        sb.appendLine()
    }
    println(sb.toString())
}