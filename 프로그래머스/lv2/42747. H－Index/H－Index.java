class Solution {
    public int solution(int[] citations) {
        int[] count =new int[10_001];
        
        int max = Integer.MIN_VALUE;
        int size = citations.length;
        for(int i=0;i<size;i++){
            count[citations[i]]++;
            if(max<citations[i]) max = citations[i];
        }
        
        int ans = 0;
        int cnt = 0;
        
        for(int i=max ;i>=0 ;i--){
            if(cnt>=i){
                ans=i;
                break;
            }
            if(count[i]==0) continue;
            cnt += count[i];
            if(cnt>=i) {
                ans = i;
                break;
            }
        }
        
        return ans;
    }
}