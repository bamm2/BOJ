class Solution {

    private data class GiftInfo(
        var give: Int = 0,
        var take: Int = 0,
        val info: HashMap<String, Int> = hashMapOf()
    )

    private val infos = hashMapOf<String, GiftInfo>()
    private val counter = hashMapOf<String, Int>()

    fun solution(friends: Array<String>, gifts: Array<String>): Int {
        friends.forEach {
            infos[it] = GiftInfo()
        }

        gifts.forEach {
            val (from, to) = it.split(" ")
            infos[to]!!.take++
            val giveUserInfo = infos[from]!!
            giveUserInfo.give++
            val giveUserInfoMap = giveUserInfo.info
            giveUserInfoMap.customAdd(to)
        }

        for (i in 0..friends.size - 1) {
            val curFriends = friends[i]
            for (j in i + 1..friends.size - 1) {
                val compFriends = friends[j]
                if (infos[curFriends]!!.info[compFriends] != null ||
                    infos[compFriends]!!.info[curFriends] != null
                ) {
                    val cur = infos[curFriends]!!.info[compFriends] ?: 0
                    val comp = infos[compFriends]!!.info[curFriends] ?: 0
                    if (cur > comp) {
                        counter.customAdd(curFriends)
                    } else if (cur < comp) {
                        counter.customAdd(compFriends)
                    } else {
                        findBiggerIncrease(curFriends, compFriends)
                    }
                } else {
                    findBiggerIncrease(curFriends, compFriends)
                }
            }
        }
       
        var result = 0
        for(user in counter){
            result = Math.max(result,user.value)
        }
        return result
    }

    private fun HashMap<String, Int>.customAdd(name: String) {
        this.put(name, this.getOrDefault(name, 0) + 1)
    }

    private fun findBiggerIncrease(curFriends: String, compFriends: String) {
        val cur = infos[curFriends]!!.give - infos[curFriends]!!.take
        val comp = infos[compFriends]!!.give - infos[compFriends]!!.take
        if (cur > comp) {
            counter.customAdd(curFriends)
        } else if (cur < comp) {
            counter.customAdd(compFriends)
        }
    }
}