import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R,C,map[][];
    static int[][] dir={ {-1,0}, {1,0}, {0,1}, {0,-1}};
    static boolean[][] visited;
    static class Point {
        int r,c;
        Point(int r,int c){
            this.r=r;
            this.c=c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());

        map=new int[R][C];

        for(int i=0;i<R;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<C;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        int ans=0;
        while(true) {
            int cnt = 0;
            visited = new boolean[R][C];
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] != 0 && !visited[i][j]) {
                        bfs(i, j);
                        cnt++;
                    }
                }
            }

            if (cnt >= 2) break;
            if (cnt == 0) {
                ans = 0;
                break;
            }

            melt();
            ans++;
        }
        System.out.println(ans);
    }

    private static void melt(){
        visited=new boolean[R][C];

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j]!=0) {
                    visited[i][j]=true;
                    for(int d=0;d<4;d++){
                        int nr=i+dir[d][0];
                        int nc=j+dir[d][1];

                        if(isOut(nr,nc)) continue;
                        if(visited[nr][nc]) continue;
                        int cnt=0;
                        if(!visited[nr][nc] && map[nr][nc]==0) cnt++;
                        if(map[i][j]-cnt>0) map[i][j]-=cnt;
                        else map[i][j]=0;
                    }
                }
            }
        }
    }

    private static void bfs(int r, int c){
        Queue<Point> q =new ArrayDeque<>();
        q.offer(new Point(r,c));
        visited[r][c]=true;

        while(!q.isEmpty()){
            Point curr=q.poll();
            for(int d=0;d<4;d++) {
                int nr = curr.r + dir[d][0];
                int nc = curr.c + dir[d][1];
                if (isOut(nr, nc)) continue;
                if (map[nr][nc]!=0 && !visited[nr][nc]){
                    visited[nr][nc]=true;
                    q.offer(new Point(nr,nc));
                }
            }
        }
    }

    private static boolean isOut(int r, int c){
        return (r<0 || c<0 || r>=R || c>=C );
    }
}