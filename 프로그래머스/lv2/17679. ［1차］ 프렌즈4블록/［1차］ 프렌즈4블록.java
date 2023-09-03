import java.util.Stack;

class Solution {
    
     static int R,C,ans;
        static char[][] map;
        static int[][] dir = { {1,0},{0,1},{1,1}};
        static boolean[][] visited;

    
    public int solution(int m, int n, String[] board) {
             R=m; C=n;
            map =new char[R][C];
            visited=new boolean[R][C];

            for(int i=0;i<m;i++){
                String str =board[i];
                for(int j=0;j<n;j++){
                    map[i][j]=str.charAt(j);
                }
            }

            while(findPop()){
                Down();
                visited =new boolean[R][C];
            }
            
            return ans;
    }
     private boolean findPop() {
            boolean flag =false;
            for(int i=0;i<R;i++){
                for(int j=0;j<C;j++){
                    char comp = map[i][j];
                    if(comp=='X') continue;
                    int cnt = 1 ;
                    for(int d=0;d<3;d++){
                       int nr =i+dir[d][0];
                       int nc= j+dir[d][1];
                       if(isOut(nr,nc)) break;
                       if(comp!=map[nr][nc]) break;
                       cnt++;
                    }
                    if(cnt==4) {
                        setVisited(i,j);
                        flag=true;
                    }
                }
            }
            return flag;
        }

        private void Down() {
            Stack<Character> stack =new Stack<>();
            for(int i=0;i<C;i++){
                for(int j=0;j<R;j++){
                    if(!visited[j][i]) stack.push(map[j][i]);
                    if(visited[j][i]) ans++;
                }
                for(int j=R-1;j>=0;j--){
                    if(!stack.isEmpty()) map[j][i]=stack.pop();
                    else map[j][i]='X';
                }
            }
        }

        private void setVisited(int r,int c){
            visited[r][c]=true;
            visited[r][c+1]=true;
            visited[r+1][c]=true;
            visited[r+1][c+1]=true;
        }

        private static boolean isOut(int r,int c){
            return r<0 || c<0|| r>=R || c>=C;
        }
}