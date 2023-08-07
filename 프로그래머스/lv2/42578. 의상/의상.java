import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String[][] clothes) {
        
         Map<String,Integer> hm =new HashMap<>();    
        
        for(int i=0;i<clothes.length;i++){        
            hm.put(clothes[i][1],hm.getOrDefault(clothes[i][1],0)+1);
        }
        
       int sum= 1;
        for(Map.Entry<String,Integer> entry : hm.entrySet() ){
                sum*=(entry.getValue()+1);
        }
        
        sum--;
        
        return sum;
    }
}