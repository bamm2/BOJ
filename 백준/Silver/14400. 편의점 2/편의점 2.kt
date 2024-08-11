import java.util.*
import kotlin.math.abs

private lateinit var rPoints: MutableList<Int>
private lateinit var cPoints: MutableList<Int>
private var N = 0

fun main() {
    rPoints = mutableListOf()
    cPoints = mutableListOf()

    N = readln().trim().toInt()

    var tokens: StringTokenizer
    repeat(N) {
        tokens = StringTokenizer(readln(), " ")
        val c = tokens.nextToken().toInt()
        val r = tokens.nextToken().toInt()
        rPoints.add(r)
        cPoints.add(c)
    }

    rPoints.sort()
    cPoints.sort()

    var result = 0L
    if (N % 2 == 0) {
        val midOne = N / 2 - 1
        val midTwo = N / 2

        val midOneR = rPoints[midOne]
        val midOneC = cPoints[midOne]
        val midTwoR = rPoints[midTwo]
        val midTwoC = cPoints[midTwo]

        result = getResult(midOneR, midOneC).coerceAtMost(getResult(midTwoR, midTwoC))
    } else {
        val mid = N / 2
        val midR = rPoints[mid]
        val midC = cPoints[mid]

        result = getResult(midR, midC)
    }
    println(result)
}

fun getResult(midR: Int, midC: Int): Long {
    var result = 0L
    for (i in 0..<N) {
        result += abs(rPoints[i] - midR)
        result += abs(cPoints[i] - midC)
    }
    return result
}