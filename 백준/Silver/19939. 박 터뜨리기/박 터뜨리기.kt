fun main() {
    var (n, k) = readln().split(" ").map { it.toInt() }
    val init = k * (1 + k) / 2
    var result: Int

    if (init > n) result = -1
    else {
        n -= init
        result = k - 1
        if (n % k != 0) ++result
    }

    println(result)
}