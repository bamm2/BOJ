class Solution {
    public int[] solution(int n) {
        int size=n%2==0? (n/2) : (n/2)+1;
        int[] answer =new int[size];
        int idx=0;
        int num=1;
        while(true){
            answer[idx]=num;
            idx++;
            num+=2;
            if(num>n) break;
        }
    
        return answer;
    }
}