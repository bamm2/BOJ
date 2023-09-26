import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int ans = Integer.MAX_VALUE;
    static int N, map[][];
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static int[][] visited;
    static class Point{
        int r,c,change;
        Point(int r,int c,int change ){
            this.r=r;
            this.c=c;
            this.change=change;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited=new int[N][N];

        for(int i=0;i<N;i++){
            String str= br.readLine();
            for(int j=0;j<N;j++){
                map[i][j]=str.charAt(j)-'0';
                visited[i][j]=Integer.MAX_VALUE;
            }
        }

        bfs();

        System.out.println(ans);
    }

    private static void bfs() {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(0,0,0));
        visited[0][0]=0;

        while(!q.isEmpty()){
            Point curr =q.poll();
            if(curr.r==N-1 && curr.c==N-1){
                ans=Math.min(ans,curr.change);
            }
            for(int d=0;d<4;d++){
                int nr =curr.r+dir[d][0];
                int nc =curr.c+dir[d][1];
                if(isOut(nr,nc)) continue;
                if(map[nr][nc]==0){
                    if(visited[nr][nc]<=curr.change+1) continue;
                    visited[nr][nc]=curr.change+1;
                    q.offer(new Point(nr,nc,curr.change+1));
                }else{
                    if(visited[nr][nc]<=curr.change) continue;
                    visited[nr][nc]=curr.change;
                    q.offer(new Point(nr,nc,curr.change));
                }
            }

        }

    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= N;
    }
}

