class Solution {
    static int ans[],map[][],n;
    
    public int[] solution(int[][] arr) {
        ans = new int[2];
            map=arr;
            n = arr.length;

            search(0,0,n);
            return ans;
    }
     private void search(int r,int c ,int range) {

            if(range==1) {
                ans[map[r][c]]++;
                return;
            }

            if(isOk(r,c,range)){
                ans[map[r][c]]++;
                return;
            }

            search(r,c,range/2);
            search(r+range/2,c,range/2);
            search(r,c+range/2,+range/2);
            search(r+range/2,c+range/2,range/2);

        }
        private static boolean isOk(int r,int c,int range){
            int standard = map[r][c];
            for(int i=r;i<r+range;i++){
                for(int j=c;j<c+range;j++){
                    if(map[i][j]!=standard) return false;
                }
            }
            return true;
        }
}