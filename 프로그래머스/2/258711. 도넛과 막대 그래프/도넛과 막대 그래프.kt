class Solution {
    private lateinit var connects: MutableMap<Int, MutableList<Int>>

    fun solution(edges: Array<IntArray>): IntArray {
        connects = mutableMapOf()
        val counter = IntArray(1_000_001)
        var root = -1
        edges.forEach {
            counter[it[0]]++
            counter[it[1]]--

            val value = connects[it[0]] ?: mutableListOf()
            value.add(it[1])
            connects[it[0]] = value
        }
        for (i in 0..1_000_000) {
            if (counter[i] >= 2) {
                root = i
                break
            }
        }

        val childNode = connects[root]!!
        val result = IntArray(4)
        result[0] = root

        childNode.forEach { v ->
            result[findShape(v)]++
        }
        return result
    }

    private fun findShape(v: Int): Int {
        val q = ArrayDeque<Int>()
        val dummy = 1_000_001L
        val nodeCounter = hashSetOf(v)
        val edgeCounter = hashSetOf<Long>()
        q.addLast(v)
        var flag = false
        while (q.isNotEmpty()) {
            val from = q.removeFirst()
            if (connects[from] == null) continue
            for (to in connects[from]!!) {
                if (to == v) flag = true
                if (edgeCounter.add(from * dummy + to)) {
                    q.addLast(to)
                    nodeCounter.add(to)
                }
            }
        }

        if (!flag) return 2

        return if (nodeCounter.size == edgeCounter.size) 1
        else 3
    }
}