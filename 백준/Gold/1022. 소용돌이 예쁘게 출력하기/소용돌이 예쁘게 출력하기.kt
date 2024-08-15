import java.util.*
import kotlin.math.abs

private val dir = arrayOf(arrayOf(0, 1), arrayOf(-1, 0), arrayOf(0, -1), arrayOf(1, 0))
private var number = 1
private var loopCount = 1
private var N = 0
private var maxLen = 0
private var r = 0
private var c = 0
private var d = 0
private var r1 = 0
private var c1 = 0
private var r2 = 0
private var c2 = 0

private data class Element(val r: Int, val c: Int, val number: Int)

private var points = mutableListOf<Element>()
private var maxStrLen = 1

fun main() {
    val st = StringTokenizer(readln(), " ")
    r1 = st.nextToken().toInt()
    c1 = st.nextToken().toInt()
    r2 = st.nextToken().toInt()
    c2 = st.nextToken().toInt()
    maxLen = abs(r1).coerceAtLeast(abs(c1)).coerceAtLeast(abs(r2)).coerceAtLeast(abs(c2))
    N = maxLen * 2 + 1

    r = N - maxLen - 1
    c = N - maxLen - 1

    r1 += maxLen
    c1 += maxLen
    r2 += maxLen
    c2 += maxLen

    makeMap()

    points.sortWith { o1, o2 -> if (o1.r == o2.r) o1.c.compareTo(o2.c) else o1.r.compareTo(o2.r) }

    println(result())
}


private fun makeMap() {
    if (isResultRange(r, c)) {
        points.add(Element(r, c, number))
    }
    while (true) {

        repeat(loopCount) {
            r += dir[d][0]
            c += dir[d][1]
            ++number
            if (!isOut(r, c) && isResultRange(r, c)) {
                maxStrLen = maxStrLen.coerceAtLeast(number.getLen())
                points.add(Element(r, c, number))
            }
        }
        if (isOut(r, c)) break
        d = (d + 1) % 4
        repeat(loopCount) {
            r += dir[d][0]
            c += dir[d][1]
            ++number
            if (!isOut(r, c) && isResultRange(r, c)) {
                maxStrLen = maxStrLen.coerceAtLeast(number.getLen())
                points.add(Element(r, c, number))
            }
        }
        if (isOut(r, c)) break
        d = (d + 1) % 4
        ++loopCount
    }

}

private fun result(): String {
    val builder = StringBuilder()
    var r = points[0].r
    for (point in points) {
        val curLen = point.number.getLen()
        if (r == point.r) {
            if (maxStrLen != curLen) {
                repeat(maxStrLen - curLen) {
                    builder.append(" ")
                }
            }
            builder.append("${point.number} ")
        } else {
            builder.trimEnd()
            builder.appendLine()
            if (maxStrLen != curLen) {
                repeat(maxStrLen - curLen) {
                    builder.append(" ")
                }
            }
            builder.append("${point.number} ")
            r = point.r
        }
    }

    return builder.trimEnd().toString()
}


private fun isOut(r: Int, c: Int) = r < 0 || c < 0 || r >= N || c >= N

private fun Int.getLen(): Int {
    return when {
        this >= 100_000_000 -> 9
        this >= 10_000_000 -> 8
        this >= 1_000_000 -> 7
        this >= 100_000 -> 6
        this >= 10_000 -> 5
        this >= 1_000 -> 4
        this >= 100 -> 3
        this >= 10 -> 2
        else -> 1
    }
}

private fun isResultRange(r: Int, c: Int) = (r in r1..r2) && (c in c1..c2)