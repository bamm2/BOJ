import java.util.*

fun main() {
    val n = readln().toInt()
    val arr = IntArray(n)
    val st = StringTokenizer(readln(), " ")
    var sum = 0L
    for (i in 0 until n) {
        arr[i] = st.nextToken().toInt()
        sum += arr[i]
    }

    arr.sortDescending()

    var result = 0L
    for( i in 0 until n-1){
        val curr = arr[i]
        sum -= curr
        result+= sum*curr
    }

    println(result)
}