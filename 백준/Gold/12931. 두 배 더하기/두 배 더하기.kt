import java.util.*

private var multipleMaxCount = 0
private var plusCount = 0

fun main() {
    val n = readln().toInt()
    val arr = IntArray(n)

    val st = StringTokenizer(readln(), " ")
    var cnt = 0
    for (i in 0 until n) {
        arr[i] = st.nextToken().toInt()
        search(arr[i], i, 0, 0)
        cnt+=plusCount
    }
    cnt+=multipleMaxCount

    println(cnt)
}

fun search(num: Int, idx: Int, multipleCnt: Int, plusCnt: Int) {
    if (num == 0) {
        plusCount = plusCnt
        multipleMaxCount = Math.max(multipleMaxCount,multipleCnt)
        return
    }

    if (num % 2 == 0) search(num / 2, idx, multipleCnt + 1, plusCnt)
    else search(num - 1, idx, multipleCnt, plusCnt + 1)
}