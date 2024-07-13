import java.util.StringTokenizer

fun main() {
    val (N, A, D) = readln().split(" ").map { it.toInt() }
    var findNumber = A
    val st = StringTokenizer(readln()," ")
    repeat(N) {
        val number = st.nextToken().toInt()
        if (number == findNumber) {
            findNumber += D
        }
    }
    println((findNumber - D - A) / D + 1)
}