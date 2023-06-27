class Solution {
    
    static int size,answer,nums[],chk[];
    
    public int solution(int[] numbers, int target) {
        
        size=numbers.length;
        nums=numbers;
        chk=new int[size];
        
        solve(0,target);
        
        return answer;
    }
    
    private static void solve(int idx,int target){
        if(idx==size){
            int sum=0;
            
            for(int i=0;i<size;i++){
                sum+=chk[i];
            }
            
            if(sum==target){
                answer++;
            }
            
            return;
        }
        
            chk[idx]= -1*nums[idx];
            solve(idx+1,target);      
            chk[idx]= -1*chk[idx];
            solve(idx+1,target);
        
    }
    
}