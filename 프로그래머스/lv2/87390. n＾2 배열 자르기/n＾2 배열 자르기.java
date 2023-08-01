
class Solution {

    public int[] solution(int n, long left, long right) {
        int len = (int)(right-left+1);
        int[] answer = new int[len];
        
        int idx =0 ;
        for(long i = left ; i<=right ;i++){
            long max = Math.max(i%n,i/n);
            answer[idx++]= (int)max + 1; 
        }
        // loop:
        // for(int i=0;i<n;i++){
        //     for(int j=0;j<n;j++){
        //         if(i*n+j<left) continue;
        //         if(i*n+j>right) break; 
        //         if(left<= i*n+j && i*n+j <= right){
        //             int max = Math.max(i,j);
        //              answer[idx++]=max+1;
        //          }
        //     }
        // }
  
        return answer;
    }
    
}