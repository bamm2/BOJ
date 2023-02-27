class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int num=n;
        int div=6;
        int multiple=1;
        while(true){
            boolean sign=false;
            for(int i=2;i<=6;i++){
                if(num%i==0 && div%i==0){
                    multiple*=i;
                    num/=i;
                    div/=i;
                    sign=true;
                    break;
                }
            }
            if(!sign) break;
        }
        
        answer=(num*div*multiple)/6;
        
        return answer;
    }
}