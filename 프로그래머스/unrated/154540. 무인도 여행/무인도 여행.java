import java.util.*;

class Solution {
      static class Point{
            int r,c;
            Point(int r,int c){
                this.r=r;
                this.c=c;
            }
        }
        static char[][] map;
        static int R,C;
        static boolean[][] visited;
        static int[][] dir = { {-1,0},{1,0},{0,-1},{0,1}};

        public int[] solution(String[] maps) {
            R = maps.length;
            C = maps[0].length();
            map = new char[R][C];
            visited=new boolean[R][C];

            for(int i=0;i<maps.length;i++){
                String str = maps[i];
                for(int j=0;j<str.length();j++){
                    map[i][j]=str.charAt(j);
                }
            }

            List<Integer> list =new ArrayList<>();

            for(int i=0;i<R;i++){
                for(int j=0;j<C;j++){
                    if(map[i][j]!='X' && !visited[i][j]){
                        int num = bfs(i,j);
                        if(num!=0) list.add(num);
                    }
                }
            }

            int[] ans;
            if(list.size()==0) ans=new int[]{-1};
            else{
                ans =new int[list.size()];
                for(int i=0;i< list.size();i++){
                    ans[i]=list.get(i);
                }
            }
            Arrays.sort(ans);
            return ans;
        }

        private int bfs(int r, int c) {
            int cnt =0;

            Queue<Point> q =new ArrayDeque<>();
            visited[r][c]=true;
            q.offer(new Point(r,c));

            while (!q.isEmpty()){
                Point curr = q.poll();
                cnt += map[curr.r][curr.c]-'0';
                for(int d=0;d<4;d++){
                    int nr =curr.r+dir[d][0];
                    int nc =curr.c+dir[d][1];
                    if(isOut(nr,nc) || visited[nr][nc] || map[nr][nc]=='X') continue;
                    visited[nr][nc]=true;
                    q.offer(new Point(nr,nc));
                }
            }

            return cnt;
        }

        private static boolean isOut(int r,int c){
            return r<0 || c<0 || r>=R || c>=C ;
        }
}