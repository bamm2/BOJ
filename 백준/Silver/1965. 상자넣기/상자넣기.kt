fun main() {
    val n = readln().toInt()
    val arr = readln().split(" ").map { it.toInt() }.toIntArray()
    val dp = IntArray(n)

    var max = -1
    dp[0] = 1
    for (i in 1..<n) {
        val cur = arr[i]
        for (j in 0..<i) {
            if (arr[j] < cur && dp[i] < dp[j]) {
                dp[i] = dp[j]
            }
        }
        dp[i]++
        max = max.coerceAtLeast(dp[i])
    }
    println(max)
}