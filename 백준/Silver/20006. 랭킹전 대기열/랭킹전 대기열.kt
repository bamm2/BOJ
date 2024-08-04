private data class Member(val level: Int, val id: String)

fun main() {
    val (n, maxCount) = readln().split(" ").map { it.toInt() }

    val rooms = mutableListOf<String>()
    val roomStatus = hashMapOf<String, ArrayList<Member>>()

    repeat(n) {
        val (l, name) = readln().split(" ")
        val level = l.toInt()

        val roomName = "${level}${name}"
        if (rooms.isEmpty()) {
            rooms.add(roomName)
            roomStatus[roomName] = arrayListOf(Member(level, name))
        } else {
            var roomNumber = -1
            for (room in rooms) {
                val standardLevel = getRoomLevel(room)
                if (level - 10 <= standardLevel && standardLevel <= level + 10) {
                    if (roomStatus[room]!!.size != maxCount) {
                        val value = roomStatus[room]!!
                        value.add(Member(level, name))
                        roomStatus[room] = value
                        roomNumber = standardLevel
                        break
                    }
                }
            }
            if (roomNumber == -1) {
                rooms.add(roomName)
                roomStatus[roomName] = arrayListOf(Member(level, name))
            }
        }
    }

    val sb = StringBuilder()
    for (room in rooms) {
        val value = roomStatus[room]!!
        if (value.size == maxCount) {
            sb.appendLine("Started!")
        } else {
            sb.appendLine("Waiting!")
        }

        value.sortWith { o1, o2 -> o1.id.compareTo(o2.id) }
        for (member in value) {
            sb.appendLine("${member.level} ${member.id}")
        }
    }

    println(sb.toString())
}

fun getRoomLevel(room: String): Int {
    var number = room[0] - '0'
    for (i in 1..<room.length) {
        if (room[i] in '0'..'9') {
            number *= 10
            number += room[i] - '0'
        } else {
            break
        }
    }
    return number
}
