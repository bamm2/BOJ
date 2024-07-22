var pattern = ""
fun main() {
    val n = readln().toInt()
    pattern = readln()

    val list = mutableListOf<String>()
    repeat(n) {
        list.add(readln())
    }

    val result = StringBuilder()
    list.forEach {
        if (isSatisfied(it)) result.appendLine("DA")
        else result.appendLine("NE")
    }

    println(result.toString())
}


fun isSatisfied(str: String): Boolean {
    var patternIdx = 0
    var strLeftIdx = 0
    var strRightIdx = str.length - 1
    var isLeft = true
    while (true) {
        if (strRightIdx < strLeftIdx) break
        if (isLeft) {
            if (pattern[patternIdx] == '*') {
                isLeft = false
                patternIdx = pattern.length - 1
                strLeftIdx--
                continue
            }
            if (pattern[patternIdx] != str[strLeftIdx]) return false
            patternIdx++
            strLeftIdx++
        } else {
            if (pattern[patternIdx] == '*') {
                return true
            }
            if (pattern[patternIdx] != str[strRightIdx]) return false
            patternIdx--
            strRightIdx--
        }
    }

    return false
}
