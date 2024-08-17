fun main() {
    var (number, radix) = readln().split(" ").map { it.toInt() }
    val result = StringBuilder()
    while (number != 0) {
        val mod = number % radix
        var codeNumber = mod
        if (mod >= 10) {
            codeNumber += 55
            result.append(codeNumber.toChar())
        } else {
            result.append(codeNumber)
        }
        number /= radix
    }
    result.reverse()
    println(result.toString())
}