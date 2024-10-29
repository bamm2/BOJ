package Algorithm.BruteForce.BOJ21315

import java.io.StreamTokenizer
import kotlin.math.pow

class BOJ21315 {

    private var N = 0
    private val status = IntArray(2)
    private var result: IntArray? = null
    private lateinit var arr: IntArray

    fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val input = { nextToken();nval.toInt() }
        N = input()
        arr = IntArray(N) { input() }

        search(0)
        println("${result!![0]} ${result!![1]}")
    }

    private fun search(idx: Int) {
        result ?: run {
            if (idx == 2) {
                if (status.all { it.pow() <= N } && isSatisfied(status[0], status[1])) {
                    result = status.copyOf()
                }
                return
            }

            for (i in 1..9) {
                status[idx] = i
                search(idx+1)
            }
        }
    }

    private fun isSatisfied(first: Int, second: Int): Boolean {
        var copyStatus = (1..N).toList()
        copyStatus = firstStep(copyStatus, first)
        copyStatus = secondStep(copyStatus, first)
        copyStatus = firstStep(copyStatus, second)
        copyStatus = secondStep(copyStatus, second)
        return copyStatus.toIntArray().contentEquals(arr)
    }

    private fun secondStep(copyStatus: List<Int>, first: Int): List<Int> {
        var copy = copyStatus.toList()
        var retryCnt = first - 1
        while (retryCnt >= 0) {
            copy = swap(retryCnt.pow(), copy)
            retryCnt--
        }
        return copy
    }

    private fun firstStep(copyStatus: List<Int>, k: Int): List<Int> {
        var bound = k.pow()
        if (bound == 0) bound = 1
        val standard = copyStatus.size - bound
        val bottom = copyStatus.subList(0, standard)
        val top = copyStatus.subList(standard, copyStatus.size).toMutableList()
        top.addAll(bottom)
        return top
    }

    private fun swap(_bound: Int, copyStatus: List<Int>): List<Int> {
        val standard = if (_bound == 0) 1 else _bound
        val first = copyStatus.subList(standard, standard * 2)
        val second = copyStatus.subList(0, standard)
        val third = copyStatus.subList(standard * 2, copyStatus.size)
        val swapped = mutableListOf<Int>()
        swapped.addAll(first)
        swapped.addAll(second)
        swapped.addAll(third)
        return swapped
    }

    private fun Int.pow(): Int {
        return 2.0.pow(this).toInt()
    }

}

fun main() {
    BOJ21315().solve()
}