package Algorithm.BinarySearch.BOJ2295

class BOJ2295 {
    private val existCheck = mutableSetOf<Int>()
    fun run() {
        val N = readln().toInt()
        val arr = IntArray(N)
        for (i in 0..<N) {
            arr[i] = readln().toInt()
        }
        for (i in 0..<N) {
            for (j in 0..<N) {
                existCheck.add(arr[i] + arr[j])
            }
        }

        arr.sort()

        var result = 0
        loop@ for (i in N - 1 downTo 0) {
            for (j in 0..i) {
                if (existCheck.contains(arr[i] - arr[j])) {
                    result = arr[i]
                    break@loop
                }
            }
        }
        println(result)
    }
}

fun main() {
    BOJ2295().run()
}