import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class Solution {
    
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        Map<Integer,Integer> map =new HashMap<>();
        
        for(int num : tangerine){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        
        List<Integer> list =new ArrayList<>(map.keySet());        
        list.sort((a,b)->map.get(b)-map.get(a));
        
        for(Integer num : list){
            answer++;
            k-=map.get(num);
            if(k<=0) break;
        }
        
        return answer;
    }
}