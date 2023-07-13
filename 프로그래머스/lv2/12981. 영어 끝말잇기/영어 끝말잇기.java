import java.util.HashSet;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];

        int userNum = 0;
        int turnCnt = 0;
        boolean flag = false;
        
        HashSet<String> hs =new HashSet<>();
        
        char prev = words[0].charAt(words[0].length()-1);
        hs.add(words[0]);
        for(int i=1;i<words.length;i++){
            userNum=i%n;
            
            char currFirst = words[i].charAt(0);
            char currLast = words[i].charAt(words[i].length()-1);
            
            if(hs.contains(words[i]) || prev!=currFirst){
                flag=true;
                if((i+1)%n==0){
                    turnCnt=(i+1)/n;
                }else{
                    turnCnt=(i+1)/n;
                    turnCnt++;
                }
                break;
            }
            hs.add(words[i]);
            prev = currLast;
        }
        
        if(!flag){
            answer[0]=0;
            answer[1]=0;
        }else{
            answer[0]=++userNum;
            answer[1]=turnCnt;
        }
        
        return answer;
    }
}