import java.util.Arrays;

class Solution {
    public int solution(int[] arr) {
        int answer = 1;
        
        Arrays.sort(arr);
        
        int size =arr.length;
        
        if(size==1) answer=arr[0];
        else{
            int loop=size;
            int compIdx = 0;
            while(loop-->0){
                int div =gcd(answer,arr[compIdx]);                                             answer= answer*arr[compIdx++]/div;
            }
        }
        
        return answer;
    }
    
    private static int gcd(int a, int b){
        if(b==0) return a;
        return gcd(b , a%b);
        
    }
}