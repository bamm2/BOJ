fun main() {

    val sb = StringBuilder()

    while (true){
        val num = readln().toInt()
        if(num==-1) break

        var sum  = 0
        var list = mutableListOf<Int>()
        for(i in 1 until num){
            if(num%i==0) {
                sum+=i
                list.add(i)
            }
            if(sum>num) break
        }
        if(num==sum) {
            sb.append("$num = ")
            for(i in 0 until list.size){
                if(i==list.size-1) sb.append(list.get(i))
                else sb.append("${list.get(i)} + ")
            }

        }else{
            sb.append("$num is NOT perfect.")
        }
        sb.append('\n')
    }

    println(sb.toString())

}