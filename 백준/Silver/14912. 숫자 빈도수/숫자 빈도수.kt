fun main() {
    val (n, d) = readln().split(" ").map { it.toInt() }
    var result = 0
    for (i in 1..n) {
        var num = i
        while (true) {
            if(num%10 == d) result++
            num /= 10
            if (num == 0) break
        }
    }
    println(result)
}