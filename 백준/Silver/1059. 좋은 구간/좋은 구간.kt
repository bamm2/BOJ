class BOJ1059 {
    fun run() {
        readln().toInt()
        val arr = readln().split(" ").map { it.toInt() }.toMutableList()
        val num = readln().toInt()
        if (arr.contains(num) || arr.size == 1) {
            println(0)
            return
        }
        arr.addAll(listOf(0, 1001, num))
        arr.sort()

        var idx = -1
        for (i in arr.indices) {
            if (num == arr[i]) {
                idx = i
                break
            }
        }
        var left = arr[idx - 1] + 1
        var right = arr[idx + 1] - 1
        var sum = 0
        while (left <= num) {
            sum += right - left - (num - left) + 1
            left++
        }

        println(--sum)
    }
}

fun main() {
    BOJ1059().run()
}