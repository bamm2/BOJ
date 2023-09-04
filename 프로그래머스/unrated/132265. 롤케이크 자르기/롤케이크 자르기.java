class Solution {
    public int solution(int[] topping) {
        int size =topping.length-1;

            int[] left =new int[size+1];
            int[] right =new int[size+1];
            boolean[] leftVisited = new boolean[size+2];
            boolean[] rightVisited = new boolean[size+2];
            int leftCnt=0;
            int rightCnt=0;

            for(int i=0;i<topping.length;i++){
                int leftNumber = topping[i];
                int rightNumber = topping[size-i];
                if(!leftVisited[leftNumber]){
                    leftVisited[leftNumber]=true;
                    leftCnt++;
                }
                left[i]=leftCnt;

                if(!rightVisited[rightNumber]){
                    rightVisited[rightNumber]=true;
                    rightCnt++;
                }
                right[size-i]=rightCnt;
            }

            int ans =0;

            for(int i=0;i<topping.length-1;i++){
                if(left[i]==right[i+1]) ans++;
            }

            return ans;
    }
}