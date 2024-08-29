class Solution {
    
    private data class GiftInfo(
        var give:Int = 0,
        var take:Int = 0,
        val info: HashMap<String,Int> = hashMapOf()
    )
    private val infos = hashMapOf<String,GiftInfo>()
    private val counter = hashMapOf<String,Int>()
    
    fun solution(friends: Array<String>, gifts: Array<String>): Int {
        friends.forEach{
            infos[it] = GiftInfo()
        }
        
        gifts.forEach{
            val(from,to) = it.split(" ")
            infos[to]!!.take++
            val giveUserInfo = infos[from]!!
            giveUserInfo.give++
            val giveUserInfoMap = giveUserInfo.info
            giveUserInfoMap.put(to,giveUserInfoMap.getOrDefault(to,0)+1)
        }
        
        for(i in 0.. friends.size-1){
            val curFriends = friends[i]
            for(j in i+1 .. friends.size-1){
                val compFriends = friends[j]
                if(infos[curFriends]!!.info[compFriends] != null ||
                        infos[compFriends]!!.info[curFriends] != null ){
                    var cur = infos[curFriends]!!.info[compFriends] ?: 0
                    var comp = infos[compFriends]!!.info[curFriends] ?: 0
                    if(cur > comp){
                        counter.put(curFriends,counter.getOrDefault(curFriends,0)+1)
                    }else if(cur < comp){
                        counter.put(compFriends,counter.getOrDefault(compFriends,0)+1)
                    }else{
                        cur = infos[curFriends]!!.give - infos[curFriends]!!.take
                        comp = infos[compFriends]!!.give - infos[compFriends]!!.take
                        if(cur > comp) {
                              counter.put(curFriends,counter.getOrDefault(curFriends,0)+1)
                        }else if(cur<comp){
                              counter.put(compFriends,counter.getOrDefault(compFriends,0)+1)
                        }
                    }
                }else {
                    val cur = infos[curFriends]!!.give - infos[curFriends]!!.take
                    val comp = infos[compFriends]!!.give - infos[compFriends]!!.take
                        if(cur > comp) {
                              counter.put(curFriends,counter.getOrDefault(curFriends,0)+1)
                        }else if(cur<comp){
                              counter.put(compFriends,counter.getOrDefault(compFriends,0)+1)
                        }
                }
            }
        }
        
        var answer = 0
        for(count in counter){
            answer = Math.max(answer,count.value)
        }
        
        return answer
    }
}