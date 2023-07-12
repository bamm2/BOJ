class Solution {
    public int solution(int n) {
        int answer = 0;
        int[] dp =new int[3];
        dp[0]=0;
        dp[1]=1;
        for(int i=2;i<=n;i++){
            dp[i%3]=(dp[(i-1)%3]%1234567+dp[(i-2)%3]%1234567)%1234567;
            if(i==n) answer=dp[i%3];
        }
        return answer;
    }
}