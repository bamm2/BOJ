class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int R = arr1.length;
        int C = arr2[0].length;
        int[][] answer =new int[R][C];
        
        int loopSize = arr1[0].length;
        
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                int sum = 0;
                int idx = 0 ;
                while(idx<loopSize){
                    sum+= arr1[i][idx]*arr2[idx][j];
                    idx++;
                }
                answer[i][j]=sum;
            }
        }
        
        return answer;
    }
}