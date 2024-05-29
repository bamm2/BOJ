fun main() {
    val arr = readln().split(" ").stream().mapToInt(String::toInt).toArray()
    println(arr[0].coerceAtMost(arr[1]).coerceAtMost(arr[2] - arr[0]).coerceAtMost(arr[3] - arr[1]))
}