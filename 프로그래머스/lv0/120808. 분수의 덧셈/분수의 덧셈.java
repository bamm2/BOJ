import java.util.*;


class Solution {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
       int numer=numer1*denom2+numer2*denom1;
       int denom= denom1*denom2;
       int max=Math.max(numer,denom);
       while(true){
           boolean sign=false;
            for(int i=2;i<=max;i++){
               if(numer%i==0 && denom%i==0){
                   numer/=i;
                   denom/=i;
                   sign=true;
                   break;
               }
           }
           if(!sign) break;
       }
    
        int[] answer = {numer,denom}; 
        return answer;
    }
}