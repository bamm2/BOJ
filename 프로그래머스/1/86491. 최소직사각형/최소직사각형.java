class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int maxW = 0;
        int maxH = 0;
        for(int i=0;i<sizes.length;i++){
            if(sizes[i][0] > sizes[i][1]){
                int tmp =sizes[i][0];
                  sizes[i][0]=sizes[i][1];
                  sizes[i][1]=tmp;
            }
            maxW = maxW < sizes[i][0] ? sizes[i][0] : maxW;
            maxH = maxH < sizes[i][1] ? sizes[i][1] : maxH;
        }
        
        answer = maxW * maxH;
        
        return answer;
    }
}