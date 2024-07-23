import kotlin.math.sqrt

fun main() {
    val T = readln().toInt()
    val sb = StringBuilder()
    val primeList = getPrimes()
    repeat(T) {
        val num = readln().toInt()
        var flag = false
        loop@
        for (i in 0..primeList.size - 1) {
            for (j in 0..primeList.size - 1) {
                for (k in 0..primeList.size - 1) {
                    if (primeList[i] + primeList[j] + primeList[k] == num) {
                        sb.appendLine("${primeList[i]} ${primeList[j]} ${primeList[k]}")
                        flag = true
                        break@loop
                    }
                }
            }
        }
        if (!flag) sb.appendLine(0)
    }
    println(sb.toString())
}

fun getPrimes(): ArrayList<Int> {
    val result = arrayListOf<Int>()

    for (i in 2..1000) {
        if (isPrime(i)) result.add(i)
    }

    return result
}


fun isPrime(num: Int): Boolean {
    val sqrt = sqrt(num.toDouble())
    for (i in 2..sqrt.toInt()) {
        if (num % i == 0) return false
    }
    return true
}