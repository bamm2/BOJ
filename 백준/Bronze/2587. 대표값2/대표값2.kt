fun main() {
    var sum = 0
    val arr = Array(5,{0})
    for(i in 0 until 5){
        val toInt = readln().toInt()
        arr[i] = toInt
        sum+=toInt
    }

    arr.sort()

    println(sum/5)
    println(arr[2])

}