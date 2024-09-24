package Algorithm.BinarySearch.BOJ1477

class BOJ1477 {
    fun run() {
        val (N, M, L) = readln().split(" ").map { it.toInt() }
        val arr = if (N != 0) readln().split(" ").map { it.toInt() }.toMutableList() else arrayListOf()
        arr.add(0, 0)
        arr.add(L)
        arr.sort()

        val diff = IntArray(N + 1)
        for (i in 1..N + 1) {
            diff[i - 1] = arr[i] - arr[i - 1] - 1
        }
        println(search(L - 1, M, diff))
    }

    private fun search(right: Int, M: Int, arr: IntArray): Int {
        var L = 1
        var R = right

        while (L <= R) {
            var cnt = 0
            val mid = (L + R) / 2
            for (i in arr.indices) {
                cnt += arr[i] / mid
            }
            if (cnt <= M) {
                R = mid - 1
            } else {
                L = mid + 1
            }
        }

        return L
    }

}

fun main() {
    BOJ1477().run()
}