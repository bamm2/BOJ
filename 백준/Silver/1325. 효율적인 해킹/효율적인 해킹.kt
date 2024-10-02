import java.util.*
import kotlin.collections.ArrayDeque

class BOJ1325 {
    fun run() = with(System.`in`.bufferedReader()) {
        var st = StringTokenizer(readLine(), " ")
        val N = st.nextToken().toInt()
        val map = Array(N + 1) { mutableListOf<Int>() }
        val counter = IntArray(N + 1)
        repeat(st.nextToken().toInt()) {
            st = StringTokenizer(readLine(), " ")
            val from = st.nextToken().toInt()
            val to = st.nextToken().toInt()
            map[to].add(from)
        }

        for (i in 1..N) {
            val q = ArrayDeque<Int>()
            val visited = BooleanArray(N + 1)
            q.addLast(i)
            visited[i] = true
            while (q.isNotEmpty()) {
                val curr = q.removeFirst()
                counter[i]++
                for (next in map[curr]) {
                    if(!visited[next]){
                        visited[next]=true
                        q.addLast(next)
                    }
                }
            }
        }

        val result = StringBuilder()
        val max = counter.max()
        for (i in 1..N) {
            if (max == counter[i]) result.append("$i ")
        }

        println(result.trim().toString())
    }
}

fun main() {
    BOJ1325().run()
}