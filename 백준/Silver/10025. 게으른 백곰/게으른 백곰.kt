fun main() {
    val (N, K) = readln().split(" ").map { it.toInt() }

    val arr = IntArray(1_000_001)

    repeat(N) {
        val (g, pos) = readln().split(" ").map { it.toInt() }
        arr[pos] = g
    }

    for (i in 1..1_000_000) {
        arr[i] += arr[i - 1]
    }

    var max = 0
    for (i in 0..1_000_000) {
        val left = i - K - 1
        val right = i + K
        var leftCnt: Int
        var rightCnt: Int
        if (left < 0) leftCnt = 0 else leftCnt = arr[left]
        if (right > 1_000_000) rightCnt = arr[1_000_000] else rightCnt = arr[right]

        max = (rightCnt - leftCnt).coerceAtLeast(max)
    }

    println(max)
}