class Solution {
    public String solution(int n) {
      StringBuilder sb =new StringBuilder();

            int div = 3 ;
            while (n!=0){
                int mod = n % 3; 
                if(mod == 0 ){ // 나머지가 0일 경우 4가 들어올 자리 
                    sb.append(4);
                    n = n / div - 1; //  0,1,2 가 아닌 1,2,4를 구해야하기에
                    // 나머지 1,2에 해당하는 것이 1,2  나머지 0 에 해당하는것이 4로 판단
                }else{
                    sb.append(mod);
                    n /= div;
                }
            }

            return sb.reverse().toString();
    }
}