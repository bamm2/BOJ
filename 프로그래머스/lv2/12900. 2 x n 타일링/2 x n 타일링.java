class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int mod = 1_000_000_007;
        
        int[] dp =new int[60_001];
        dp[1]=1;
        dp[2]=2;
        for(int i=3;i<=n;i++){
            dp[i]= ((dp[i-2])%mod + dp[i-1]%mod)%mod ;
        }
        
        return dp[n];
    }
}