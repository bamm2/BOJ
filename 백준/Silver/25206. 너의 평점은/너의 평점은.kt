fun main() {
    var sum = 0.0
    var divide =0.0
    for (i in 0..19) {
        var arr = readln().split(" ")
        var multiple = arr[1].toDouble()
        var score = calculate(arr[2])
        if (score != -1.0) {
            divide+=multiple
            sum+=multiple*score
        }
    }

    println(String.format("%.6f", sum / divide))
}

fun calculate(score: String): Double {
    when (score) {
        "A+" -> return 4.5
        "A0" -> return 4.0
        "B+" -> return 3.5
        "B0" -> return 3.0
        "C+" -> return 2.5
        "C0" -> return 2.0
        "D+" -> return 1.5
        "D0" -> return 1.0
        "F" -> return 0.0
        else -> return -1.0
    }
}