import java.util.Arrays;

class Solution {
    
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        int max = 0;
        for(int i=0;i<tangerine.length;i++){
            int num = tangerine[i];
            max=max>num?max:num;
        }
        
        int[] counting =new int[max+1];
        
        for(int i=0;i<tangerine.length;i++){
            int num = tangerine[i];
            counting[num]++;
        }
        
        Arrays.sort(counting);
        
        for(int i=max;i>0;i--){
            int cnt = counting[i];
            k-=cnt;
            answer++;
            if(k<=0) break;
        }
        
        return answer;
    }
}