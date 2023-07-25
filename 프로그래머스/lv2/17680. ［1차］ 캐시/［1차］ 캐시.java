import java.util.Queue;
import java.util.ArrayDeque;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        Queue<String> q =new ArrayDeque<>();
        
        if(cacheSize !=0){
            for(int i=0;i<cities.length;i++){
                String str = cities[i].toLowerCase();
                if(q.size()<cacheSize){
                    if(q.contains(str)) {
                        q.remove(str);
                        q.offer(str);
                        answer++;
                    }else{
                      answer+=5;
                      q.offer(str);               
                    }
                 }else{
                    if(q.contains(str)){
                        q.remove(str);
                        q.offer(str);
                        answer++;
                     }else{
                        q.poll();
                        q.offer(str);   
                        answer+=5;
                     } 
                }
            }
        }else{
            answer=5*cities.length;
        }
            
        return answer;
    }
}