class Solution {
    public long[] solution(long[] numbers) {
        long[] ans =new long[numbers.length];
            for(int i=0;i< numbers.length;i++){
                long curNumber =numbers[i];

                ans[i]=searchNumber(curNumber);
            }
        return ans;
    }
      private long searchNumber(Long number) {

            String str = Long.toBinaryString(number);
            int len = str.length();

            int firstZeroIdx = 0 ;
            for(int i=len-1;i>=0;i--){
                if(str.charAt(i)=='0') {
                    firstZeroIdx=i;
                    break;
                }
            }


            if(firstZeroIdx == 0 ){
                number+= ((long)Math.pow(2,len)-(long)Math.pow(2,len-1));
            }else {
                if(firstZeroIdx+1<str.length()){
                    if(str.charAt(firstZeroIdx+1)=='1'){
                        number+=((long)Math.pow(2,(len-1-firstZeroIdx))-(long)Math.pow(2,(len-1-firstZeroIdx-1)));
                    }
                }else{
                    number+=1;
                }
            }

            return number;
        }
}