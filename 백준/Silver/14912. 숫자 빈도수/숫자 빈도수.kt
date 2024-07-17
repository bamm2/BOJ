fun main() {
    val count = IntArray(10)
    val (n, d) = readln().split(" ").map { it.toInt() }
    for (i in 1..n) {
        var num = i
        while (true) {
            count[num % 10]++
            num /= 10
            if (num == 0) break
        }
    }
    println(count[d])
}