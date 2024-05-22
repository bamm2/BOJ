fun main() {
    val arr = readln().split(" ")
    var sb =StringBuilder()
    for(i in 0 until arr.size){
        val num = arr[i].toInt()
        when (i) {
           0,1 -> sb.append(1-num)
           2,3,4 -> sb.append(2-num)
            else -> sb.append(8-num)
        }
        sb.append(" ")
    }
    println(sb)
}