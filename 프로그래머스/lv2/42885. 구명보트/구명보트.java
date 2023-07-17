import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        int size = people.length;
            
        Arrays.sort(people);
        
        int cnt = 0 ;
        int left = 0 ;
        int right = size-1;
        while(left<=right){
            int leftNum= people[left];
            int rightNum= people[right];
            if(people[left]+people[right]>limit){
                answer++;
                right--;
            }else {
                answer++;
                left++;
                right--;
            }    
        }
        
        
        return answer;
    }
}