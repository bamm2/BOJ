class Solution {
    public int[] solution(int n) {
                 int goal = 0;
            for (int i = 1; i <= n; i++) {
                goal += i;
            }
            int[] ans =new int[goal];

            int[][] map = new int[n][n];
            boolean[][] visited = new boolean[n][n];
            int idx = 1, r = 0, c = 0;
            while (true) {
                while(true){
                    if(idx>goal) break;
                    if(visited[r][c]) break;
                    visited[r][c]=true;
                    map[r++][c]=idx++;
                    if(r>=n) break;
                }
                r--;
                c++;
                while(true){
                    if(idx>goal) break;
                    if(visited[r][c]) break;
                    visited[r][c]=true;
                    map[r][c++]=idx++;
                    if(c>=n) break;
                }
                c--;
                r--;
                c--;
                while(true){
                    if(idx>goal) break;
                    if(visited[r][c]) break;
                    visited[r][c]=true;
                    map[r--][c--]=idx++;
                }
                r++;
                c++;
                r++;
                if(idx>goal) break;
            }

            int index=0 ;
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(map[i][j]!=0) ans[index++]=map[i][j];
                }
            }
        
            return ans;
    }
}