class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int sum = 0;
        for(int i=1;i<=n;i++){
            int startNum = i; 
            while(sum<n){
                sum+=startNum++;
                if(sum==n){
                    answer++;
                    break;
                }
            }
            sum=0;
        }
        
        return answer;
    }
}