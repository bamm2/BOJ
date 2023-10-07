import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static boolean[][] visited;
    static int N,destR,destC;
    static int[][] dir = {{-2,-1},{-2,1},{0,-2},{0,2},{2,-1},{2,1}};
    static class Point{
        int r,c,cnt;
        Point(int r,int c,int cnt){
            this.r=r;
            this.c=c;
            this.cnt=cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N =Integer.parseInt(br.readLine());
        visited=new boolean[N][N];

        st=new StringTokenizer(br.readLine()," ");
        int sr =Integer.parseInt(st.nextToken());
        int sc= Integer.parseInt(st.nextToken());
        destR= Integer.parseInt(st.nextToken());
        destC= Integer.parseInt(st.nextToken());

        Queue<Point> q =new ArrayDeque<>();
        q.offer(new Point(sr,sc,0));
        visited[sr][sc]=true;

        int ans =Integer.MAX_VALUE;

        while (!q.isEmpty()){
            Point curr =q.poll();
            if(curr.r==destR && curr.c==destC){
                if(ans>curr.cnt) ans=curr.cnt;
            }
            if(curr.cnt>=ans) continue;
            for(int d=0;d<6;d++){
                int nr =curr.r+dir[d][0];
                int nc =curr.c+dir[d][1];
                if(isOut(nr,nc) || visited[nr][nc] || ans<curr.cnt) continue;
                visited[nr][nc]=true;
                q.offer(new Point(nr,nc,curr.cnt+1));
            }
        }

        if(ans==Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);

    }
    private static boolean isOut(int r,int c){
        return r<0 || c<0|| r>=N || c>=N;
    }
}
