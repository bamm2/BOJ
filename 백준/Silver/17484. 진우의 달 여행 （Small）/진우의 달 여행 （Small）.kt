fun main() {
    val (r, c) = readln().split(" ").map { it.toInt() }

    val map = Array(r) { readln().split(" ").map { it.toInt() }.toIntArray() }
    val dp = Array(3) { Array(r) { IntArray(c) { 10_000_000 } }  }


    for (i in 0..<c) {
        dp[0][0][i] = map[0][i]
        dp[1][0][i] = map[0][i]
        dp[2][0][i] = map[0][i]
    }

    for (i in 1..<r) {
        for (j in 0..<c) {
            when (j) {
                0 -> {
                    dp[0][i][j] = Math.min(dp[1][i - 1][j + 1], dp[2][i - 1][j + 1]) + map[i][j]
                    dp[1][i][j] = dp[0][i - 1][j] + map[i][j]
                }
                c - 1 -> {
                    dp[2][i][j] = Math.min(dp[1][i - 1][j - 1], dp[0][i - 1][j - 1]) + map[i][j]
                    dp[1][i][j] = dp[2][i - 1][j] + map[i][j]
                }
                else -> {
                    dp[0][i][j] = Math.min(dp[1][i - 1][j+1], dp[2][i - 1][j + 1]) + map[i][j]
                    dp[1][i][j] = Math.min(dp[0][i - 1][j], dp[2][i - 1][j]) + map[i][j]
                    dp[2][i][j] = Math.min(dp[1][i - 1][j - 1], dp[0][i - 1][j - 1]) + map[i][j]
                }
            }
        }
    }
    var result = Integer.MAX_VALUE
    for(col in 0 ..< c){
        for(i in 0 ..< 3){
            result = Math.min(result,dp[i][r-1][col])
        }
    }
    println(result)

}