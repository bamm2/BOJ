import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int ans = Integer.MAX_VALUE;
    static int R,C,K,map[][];
    static int[][] dir ={ {1,0},{-1,0},{0,1},{0,-1} };
    static boolean[][][] visited;
    static class Point{
        int r,c,broken,cnt;
        Point(int r,int c,int broken,int cnt){
            this.r=r;
            this.c=c;
            this.broken=broken;
            this.cnt=cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        map=new int[R][C];
        visited=new boolean[K+1][R][C];

        for(int i=0;i<R;i++){
            String s= br.readLine();
            for(int j=0;j<C;j++){
                map[i][j]=s.charAt(j)-'0';
            }
        }

        solve();

        if(ans==Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);
    }

    private static void solve(){
        Queue<Point> q= new ArrayDeque<>();
        q.offer(new Point(0,0,0,1));
        visited[0][0][0]=true;

        while(!q.isEmpty()){
            Point curr =q.poll();

            if(curr.r==R-1 && curr.c==C-1){
                ans= Math.min(ans,curr.cnt);
            }

            boolean stayPos =false; // 사방탐색 중 밤에 벽이 있으면 제자리에 1회 큐에 삽입
            for(int d=0;d<4;d++){
                int nr =curr.r+dir[d][0];
                int nc =curr.c+dir[d][1];

                if(isOut(nr,nc)) continue;
                if(visited[curr.broken][nr][nc]) continue;
                if(map[nr][nc]==1){ // 벽일 경우
                    if(curr.cnt%2==0){ // 밤일 경우
                        if(!stayPos) { // 제자리 q에 삽입
                            q.offer(new Point(curr.r, curr.c, curr.broken, curr.cnt + 1));
                            stayPos = true;
                        }
                    }else{ // 낮일 경우
                        if(curr.broken+1<=K){
                            if(!visited[curr.broken+1][nr][nc]){
                                visited[curr.broken+1][nr][nc]=true;
                                q.offer(new Point(nr,nc,curr.broken+1,curr.cnt+1));
                            }
                        }
                    }
                }else{ // 벽이 아닐 경우
                    visited[curr.broken][nr][nc]=true;
                    q.offer(new Point(nr,nc,curr.broken,curr.cnt+1));
                }
            }
        }
    }

    private static boolean isOut(int r, int c){
        return r<0 || c<0 || r>=R || c>=C;
    }
}