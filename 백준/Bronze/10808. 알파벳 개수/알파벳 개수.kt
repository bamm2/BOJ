fun main() {
    val str = readln()
    val counter = IntArray(26)

    str.forEach { counter[it - 'a']++ }

    val result = StringBuilder()
    counter.forEach {
        result.append(it).append(" ")
    }
    println(result.trim().toString())
}