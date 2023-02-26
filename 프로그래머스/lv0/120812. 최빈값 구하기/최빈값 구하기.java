class Solution {
    public int solution(int[] array) {
        int answer = 0;
        int[] arr =new int[1000];
        for(int i=0,size=array.length;i<size;i++){
            arr[array[i]]++;
        }
        int max=0;
        for(int i=0;i<1000;i++){
            if(max<arr[i]){
                max=arr[i];
                answer=i;
            }
        }
        
        int cnt=0;
        for(int i=0;i<1000;i++){
            if(max==arr[i]) cnt++;
            if(cnt==2) break;
        }
        
        if(cnt==2)  answer=-1;
         
        
        return answer;
    }
}