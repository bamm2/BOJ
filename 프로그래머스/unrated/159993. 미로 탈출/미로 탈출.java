import java.util.*;

class Solution {
       static class Point{
            int r,c,cnt;
            Point(int r,int c,int cnt){
                this.r=r;
                this.c=c;
                this.cnt=cnt;
            }
        }
        static boolean[][] visited;
        static int lr,lc,er,ec,R,C,ans;
        static char[][] map;
        static Queue<Point > q;
        static int[][] dir = {{-1,0},{1,0},{0,1},{0,-1}};

        public int solution(String[] maps) {
            int answer = 0;
            R=maps.length;
            C=maps[0].length();
            map=new char[R][C];
            visited=new boolean[R][C];
            q=new ArrayDeque<>();

            for(int i=0;i<R;i++){
                String s = maps[i];
                for(int j=0;j<C;j++){
                    map[i][j]=s.charAt(j);
                    if(map[i][j]=='S'){
                        q.offer(new Point(i,j,0));
                        visited[i][j]=true;
                    }
                    else if(map[i][j]=='L'){
                        lr=i; lc=j;
                    }else if(map[i][j]=='E'){
                        er=i; ec=j;
                    }
                }
            }

            if(!bfs()) ans = -1;

            return ans;
        }

            private boolean bfs() {

            while (!q.isEmpty()){
                Point curr =q.poll();
                if(curr.r==lr && curr.c==lc){
                    ans+=curr.cnt;
                    break;
                }
                for(int d=0;d<4;d++){
                    int nr =curr.r+dir[d][0];
                    int nc =curr.c+dir[d][1];
                    if(isOut(nr,nc) || visited[nr][nc] || map[nr][nc]=='X') continue;
                    visited[nr][nc]=true;
                    q.offer(new Point(nr,nc,curr.cnt+1));
                }
            }

            visited = new boolean[R][C];
            if(ans!=0) {
                q = new ArrayDeque<>();
                q.offer(new Point(lr, lc, 0));
                visited[lr][lc] = true;

                while (!q.isEmpty()) {
                    Point curr = q.poll();
                    if (curr.r == er && curr.c == ec) {
                        ans += curr.cnt;
                        break;
                    }
                    for (int d = 0; d < 4; d++) {
                        int nr = curr.r + dir[d][0];
                        int nc = curr.c + dir[d][1];
                        if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc] == 'X') continue;
                        visited[nr][nc] = true;
                        q.offer(new Point(nr, nc, curr.cnt + 1));
                    }
                }
            }

            if(!visited[er][ec]) return false;
            else return true;
        }

        private static boolean isOut(int r,int c){
            return r<0|| c<0 || r>=R || c>=C;
        }
}