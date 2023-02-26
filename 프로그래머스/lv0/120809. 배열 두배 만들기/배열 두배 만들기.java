class Solution {
    public int[] solution(int[] numbers) {
        int[] answer=new int[numbers.length];
        for(int i=0,size=numbers.length;i<size;i++){
            answer[i]=numbers[i]*2;
        }
        
        return answer;
    }
}