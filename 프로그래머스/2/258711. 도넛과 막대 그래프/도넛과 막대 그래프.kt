class Solution {
    private var MAX = 1_000_001L
    private lateinit var connects: MutableMap<Int, MutableList<Int>>

    fun solution(edges: Array<IntArray>): IntArray {
        connects = mutableMapOf()
        val counter = mutableMapOf<Int, Int>()
        edges.forEach {
            counter[it[0]] = counter.getOrDefault(it[0], 0) + 1
            counter[it[1]] = counter.getOrDefault(it[1], 0) - 1

            val value = connects[it[0]] ?: mutableListOf()
            value.add(it[1])
            connects[it[0]] = value
        }

        var root = 0
        for (element in counter) {
            if (element.value >= 2) {
                root = element.key
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
        val dummy = MAX
        val nodeCounter = hashSetOf(v)
        val edgeCounter = hashSetOf<Long>()
        q.addLast(v)
        while (q.isNotEmpty()) {
            val from = q.removeFirst()
            if (connects[from] == null) continue
            for (to in connects[from]!!) {
                if (edgeCounter.add(from * dummy + to)) {
                    q.addLast(to)
                    nodeCounter.add(to)
                }
            }
        }

        return if (nodeCounter.size == edgeCounter.size) 1
        else if (nodeCounter.size + 1 == edgeCounter.size) 3
        else 2
    }

}