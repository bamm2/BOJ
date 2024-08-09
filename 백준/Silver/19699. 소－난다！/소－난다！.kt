import kotlin.math.sqrt

private lateinit var result: MutableList<Int>
private lateinit var arr: IntArray
private lateinit var selected: IntArray
private lateinit var primes: HashSet<Int>

private var n = 0
private var m = 0
fun main() {

    val (_n, _m) = readln().split(" ").map { it.toInt() }
    n = _n
    m = _m

    arr = readln().split(" ").map { it.toInt() }.toIntArray()

    setPrime()

    selected = IntArray(m)

    result = mutableListOf()

    comb(0, 0)

    if (result.isEmpty()) println(-1)
    else {
        result.sort()
        val builder = StringBuilder()
        result.forEach { builder.append("${it} ") }
        println(builder.toString())
    }
}

fun setPrime() {
    primes = hashSetOf()

    for (i in 2..10_000) {
        if (isPrime(i)) {
            primes.add(i)
        }
    }
}

fun isPrime(num: Int): Boolean {

    for (i in 2..<sqrt(num.toDouble()).toInt() + 1) {
        if (num % i == 0) return false
    }
    return true
}


private fun comb(idx: Int, start: Int) {
    if (idx == m) {
        val sum = selected.sum()
        if (primes.contains(sum) && !result.contains(sum)) {
            result.add(sum)
        }
        return
    }

    for (i in start..<n) {
        selected[idx] = arr[i]
        comb(idx + 1, i + 1)
    }
}