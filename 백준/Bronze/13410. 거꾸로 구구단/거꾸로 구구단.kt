fun main() {
    val (N, K) = readln().split(" ").map { it.toInt() }

    var max = 0
    for (i in 1..K) {
        val num = i * N
        max = max.coerceAtLeast(num.toString().reversed().toInt())
    }
    println(max)
}