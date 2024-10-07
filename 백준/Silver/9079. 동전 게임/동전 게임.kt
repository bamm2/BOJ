package Algorithm.BruteForce.BOJ9079

import java.io.StreamTokenizer

class BOJ9079 {

    private val result = StringBuilder()
    private lateinit var checker: MutableMap<String, Int>
    private val dir = arrayOf(
        arrayOf(0, 1, 2), arrayOf(3, 4, 5), arrayOf(6, 7, 8), arrayOf(0, 3, 6),
        arrayOf(1, 4, 7), arrayOf(2, 5, 8), arrayOf(0, 4, 8), arrayOf(2, 4, 6)
    )
    private var min = 0

    fun run() = StreamTokenizer(System.`in`.bufferedReader()).run {
        val numInput = { nextToken(); nval.toInt() }
        val charInput = { nextToken(); sval[0] }

        repeat(numInput()) {
            val arr = CharArray(9) { charInput() }
            min = 987_654_321
            checker = mutableMapOf()
            search(0, arr)
            result.appendLine(if (min == 987_654_321) -1 else min)
        }
        println(result.toString())
    }

    private fun search(cnt: Int, arr: CharArray) {
        if (min <= cnt) return

        val key = arr.concatToString()
        if (checker[key] == null) {
            checker[key] = cnt
        } else {
            if (checker[key]!! <= cnt) return
            checker[key] = cnt
        }

        if (isSatisfied(arr)) {
            min = min.coerceAtMost(cnt)
            return
        }

        for (d in 0..7) {
            search(cnt + 1, reverseStatus(d, arr))
        }
    }

    private fun reverseStatus(d: Int, map: CharArray): CharArray {
        val copy = map.map { it }.toCharArray()
        for (pos in 0..<3) {
            copy[dir[d][pos]] = reverse(map[dir[d][pos]])
        }
        return copy
    }

    private fun reverse(c: Char): Char {
        if (c == 'H') return 'T'
        return 'H'
    }

    private fun isSatisfied(arr: CharArray): Boolean {
        return arr.none { it != arr[0] }
    }
}

fun main() {
    BOJ9079().run()
}