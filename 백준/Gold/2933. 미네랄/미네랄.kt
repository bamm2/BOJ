import java.util.*

lateinit var map: Array<CharArray>
var R: Int = 0
var C: Int = 0
val dir = arrayOf(
    arrayOf(1, 0),
    arrayOf(0, 1),
    arrayOf(-1, 0),
    arrayOf(0, -1)
)

fun main() {
    var st = StringTokenizer(readln(), " ")
    R = st.nextToken().toInt()
    C = st.nextToken().toInt()

    map = Array(R, { CharArray(C) })

    for (i in 0 until R) {
        map[i] = readln().toCharArray()
    }

    val T = readln().toInt()

    st = StringTokenizer(readln(), " ")
    var floor: Int
    for (i in 0 until T) {
        floor = R - st.nextToken().toInt()
        if (i % 2 == 0) { // 왼쪽에서 던지기
            shoot(floor, true)
        } else { // 오른쪽에서 던지기
            shoot(floor, false)
        }
    }

    result()
}

fun shoot(floor: Int, isLeftStart: Boolean) {
    var c: Int
    if (isLeftStart) c = 0
    else c = C - 1

    var cur: Char
    while (true) {
        if (isOut(c)) break
        cur = map[floor][c]
        if (meetMineral(cur)) { // 미네랄 만난 경우
            map[floor][c] = '.'
            searchCluster(floor, c)
            break
        }

        if (isLeftStart) ++c
        else --c
    }
}

fun meetMineral(status: Char): Boolean {
    return status == 'x'
}

// 파괴된 미네랑과 연결되어 있던 클러스터들 찾기
fun searchCluster(r: Int, c: Int) {
    val stack = Stack<Pair<Int, Int>>()

    var nr: Int
    var nc: Int
    for (d in 0..3) {
        nr = r + dir[d][0]
        nc = c + dir[d][1]
        if (isOut(nr, nc)) continue
        if (map[nr][nc] == 'x') stack.push(Pair(nr, nc))
    }

    // 클러스터를 찾은 경우
    if (!stack.isEmpty()) {
        down(stack)
    }
}

fun down(stack: Stack<Pair<Int, Int>>) {

    // 후보군 중 아래로 내려갈 후보가 있는지 체크
    while (!stack.isEmpty()) {
        val (r, c) = stack.pop()

        // 현재 클러스터가 공중에 떠있는지 체크
        val candidates = findCandidate(r, c)
        if (candidates.isNotEmpty()) { // 공중에 떠있는 것을 찾았다면
            bfs(candidates)
            break
        }
    }

}


fun bfs(candidates: MutableList<Int>) {
    val visited = mutableSetOf<Int>()
    visited.addAll(candidates) // 후보군들 방문처리

    val q: Queue<Pair<Int, Int>> = LinkedList()
    for (candidate in candidates) { // 후보군들 내려갈 수 있는 자리 찾기 위해서 탐색 세팅
        q.offer(Pair(candidate / C, candidate % C))
    }

    val curCandidates: MutableList<Int> = mutableListOf() // 현재 최대로 내려갈 수 있는 위치
    val downCandidates: MutableList<Int> = mutableListOf() // 내려갈 수 있는지 위치 체크용


    loop@ while (!q.isEmpty()) {
        var size = q.size // 한칸씩 내려가기 위해 size 만큼만 순회
        downCandidates.clear() // 내려갈 수 있는 위치 체크를 초기화
        while (size-- > 0) {
            var (r, c) = q.poll()
            ++r // 한칸 아래로
            if (isOut(r, c)) break@loop
            if (!visited.contains(r * C + c) && map[r][c] == 'x') break@loop // 방문하지 않았는데 'x' 라면 처음 마주한 미네럴
            visited.add(r * C + c)
            downCandidates.add(r * C + c)
            q.offer(Pair(r, c))
        }

        // 기존에 있던거 지우고 최대로 내려갈 수 있는 후보군들 넣어주기
        curCandidates.clear()
        curCandidates.addAll(downCandidates)
    }

    for (i in 0 until candidates.size) {
        val pos = candidates[i]
        val r = pos / C
        val c = pos % C
        map[r][c] = '.'
    }

    for (i in 0 until curCandidates.size) {
        val pos = curCandidates[i]
        val r = pos / C
        val c = pos % C
        map[r][c] = 'x'
    }
}

fun findCandidate(r: Int, c: Int): MutableList<Int> {
    val candidates = mutableListOf<Int>() // 후보군
    val visited = mutableSetOf<Int>() // 방문처리
    val q: Queue<Pair<Int, Int>> = LinkedList() // 비교
    candidates.add(r * C + c)
    visited.add(r * C + c)
    q.offer(Pair(r, c))

    while (!q.isEmpty()) {
        val (r, c) = q.poll()
        var nr: Int
        var nc: Int
        for (d in 0..3) {
            nr = r + dir[d][0]
            nc = c + dir[d][1]
            if (isOut(nr, nc) || map[nr][nc] == '.') continue
            if (visited.add(nr * C + nc)) { // 'x' 인거 체크
                if (nr == R - 1) return mutableListOf() // 바닥에 닿아있다면 빈 리스트로 바닥임을 알림
                q.offer(Pair(nr, nc)) // 후보군들 탐색
                candidates.add(nr * C + nc)
            }
        }
    }

    return candidates
}

fun isOut(c: Int): Boolean {
    return c < 0 || c >= C
}

fun isOut(r: Int, c: Int): Boolean {
    return r < 0 || c < 0 || r >= R || c >= C
}

fun result() {
    val sb = StringBuilder()

    for (i in map.indices) {
        for (j in map[i].indices) {
            sb.append(map[i][j])
        }
        sb.append('\n')
    }

    println(sb.toString())
}