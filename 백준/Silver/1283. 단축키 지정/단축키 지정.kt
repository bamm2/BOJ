const val NOPE = ' '
val set = hashSetOf(NOPE)

fun main() {
    val n = readln().toInt()
    val sb = StringBuilder()

    for (i in 0 until n) {
        val str = readln().trim()
        val idx = getIndexIsFirstInWord(str)
        if(idx!=-1){
            for(i in 0 until str.length){
                if(i!=idx) sb.append(str[i]);
                else sb.append('[').append(str[i]).append(']')
            }
        }else{
            var flag = false
            for(i in 0 until str.length){
                if(set.contains(convertToLower(str[i])) || str[i].equals(" ") || flag){
                    sb.append(str[i])
                }else{
                    flag=true
                    set.add(convertToLower(str[i]))
                    sb.append('[').append(str[i]).append(']')
                }
            }
        }
        sb.append('\n')
    }
    println(sb.toString())
}

fun getIndexIsFirstInWord(str: String): Int {
    val arr = str.split(" ")
    var idx = 0
    for(i in 0 until arr.size){
        if(arr[i].length != 0 && set.add(convertToLower(arr[i][0]))) return idx
        idx+=arr[i].length+1
    }
    return -1
}

fun convertToLower(c : Char ): Char{
    if(c in 'a'..'z') return c
    else if (c in 'A'..'Z') return c+32
    else return NOPE
}