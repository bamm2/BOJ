class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;
        
        int[] arr =new int[number+1];
        for(int i=1;i<=number;i++){
            int num=i;
            for(int j=1;j<=Math.sqrt(num);j++){
                if(num%j==0){
                    if(j*j==num){
                        arr[num]++;
                    }else{
                    arr[num]+=2;
                    }
                }
            }
        }
        for(int i=1;i<=number;i++){
            if(arr[i]>limit){
                arr[i]=power;
            }
        }
        
        for(int i=1;i<=number;i++){
            answer+=arr[i];    
        }
        
        return answer;
    }
}