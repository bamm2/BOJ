data class Point(val pos: Int, val cnt: Int)

fun main() {
    val N = readln().toInt()
    val points = arrayListOf<Point>()
    var sum = 0L
    repeat(N) {
        val (pos, cnt) = readln().split(" ").map { it.toInt() }
        points.add(Point(pos, cnt))
        sum += cnt
    }

    points.sortWith { o1, o2 -> o1.pos.compareTo(o2.pos) }

    val goal = if (sum % 2 == 0.toLong()) sum / 2 else sum / 2 + 1
    var result = 0
    var comp = 0L
    for (i in 0 until N) {
        comp += points[i].cnt
        if (comp >= goal) {
            result = points[i].pos
            break
        }
    }

    println(result)
}