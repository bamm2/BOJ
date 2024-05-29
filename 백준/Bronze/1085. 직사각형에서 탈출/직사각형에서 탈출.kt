fun main() {
    val (x, y, w, h) = readln().split(" ")
    println(x.toInt().coerceAtMost(y.toInt()).coerceAtMost(w.toInt() - x.toInt()).coerceAtMost(h.toInt() - y.toInt()))
}