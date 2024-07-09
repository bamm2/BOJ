lateinit var str: String
lateinit var hs: HashSet<String>

fun main() {
    str = readln()
    hs = HashSet()

    for (i in 0 until str.length) {
        search(i, i, str[i].toString(), str[i].toString())
    }

    println(hs.size)
}

fun search(left: Int, right: Int, s: String, comp: String) {
    if (s.length == str.length) {
        if (s == str) hs.add(comp)
        return
    }

    var newStr: String
    if (left - 1 >= 0) {
        newStr = str[left - 1] + s
        search(left - 1, right, newStr, comp + newStr)
    }
    if (right + 1 < str.length) {
        newStr = s + str[right + 1]
        search(left, right + 1, newStr, comp + newStr)
    }

}
