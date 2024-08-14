private lateinit var map: Array<CharArray>
private lateinit var top: CharArray
private lateinit var bottom: CharArray
private var R = 0
private var C = 0

fun main() {
    C = readln().toInt()
    R = readln().toInt()
    val goalElements = readln().toCharArray()
    map = Array(R) { readln().toCharArray() }
    top = CharArray(C)
    bottom = CharArray(C)

    for (i in 0..<C) {
        val index = topDownOrBottomUp(0, i, isTopDown = true)
        top[index] = 'A' + i
    }

    for (i in 0..<C) {
        val index = topDownOrBottomUp(R - 1, i, isTopDown = false)
        bottom[index] = goalElements[i]
    }

    var flag = true
    val visited = BooleanArray(C - 1)
    for (i in 0..<C - 1) {
        if (!flag) break

        if (top[i] == bottom[i + 1] && top[i + 1] == bottom[i]) visited[i] = true
        else {
            if (top[i] == bottom[i]) continue
            if (i != 0) {
                if ((top[i - 1] != bottom[i - 1]) && !visited[i - 1]) {
                    flag = false
                }
            }
        }
    }

    val result = StringBuilder()
    if (!flag) {
        repeat(C - 1) { result.append('x') }
    } else {
        visited.forEach {
            if (it) result.append("-") else result.append("*")
        }
    }
    println(result.toString())
}

private fun topDownOrBottomUp(_r: Int, _c: Int, isTopDown: Boolean): Int {
    var r = _r
    var c = _c
    while (true) {
        // c가 C-1일때 map[r][c] =='?' 비교할 경우 인덱스 에러 발생
        if ((c == C - 1 && map[r][c - 1] == '?') || (c != C - 1 && map[r][c] == '?')) break
        
        if (c == 0) {
            if (map[r][c] == '*') {
                if (isTopDown) ++r else --r
            } else {
                if (isTopDown) ++r else --r
                ++c
            }
        } else if (c == C - 1) {
            if (map[r][c - 1] == '*') {
                if (isTopDown) ++r else --r
            } else {
                if (isTopDown) ++r else --r
                --c
            }
        } else {
            if (map[r][c - 1] == '*' && map[r][c] == '*') {
                if (isTopDown) ++r else --r
            } else if (map[r][c] == '-') {
                if (isTopDown) ++r else --r
                ++c
            } else { // map[r][c-1] =='-'
                if (isTopDown) ++r else --r
                --c
            }
        }
    }

    return c
}