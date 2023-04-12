class Solution {
    public int solution(int left, int right) {
        int answer = 0;
        
        int sum=0;
        for(int i=left;i<=right; i++){
            boolean sign=false;
            for(int j=1;j<=Math.sqrt(i);j++){
                if(j*j==i){
                    sign=true;
                    break;
                }
            }
            if(sign){
                sum-=i;
            }else{
                sum+=i;
            }
        }
        
        answer=sum;
        return answer;
    }
}