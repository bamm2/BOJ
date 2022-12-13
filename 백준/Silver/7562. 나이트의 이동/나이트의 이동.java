import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] dir={{-2,1},{2,1},{-2,-1},{2,-1},{1,2},{-1,2},{1,-2},{-1,-2}};
    static boolean[][] visited;
    static int map[][],N,goalR,goalC,ans;
    static class Point{
        int r,c,cnt;
        Point(int r,int c, int cnt){
            this.r=r;
            this.c=c;
            this.cnt=cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T=Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for(int tc=1;tc<=T;tc++){
            N=Integer.parseInt(br.readLine());
            st=new StringTokenizer(br.readLine()," ");
            int startR=Integer.parseInt(st.nextToken());
            int startC=Integer.parseInt(st.nextToken());
            st=new StringTokenizer(br.readLine()," ");
            goalR=Integer.parseInt(st.nextToken());
            goalC=Integer.parseInt(st.nextToken());

            map=new int[N][N];
            visited=new boolean[N][N];

            ans=Integer.MAX_VALUE;
            solve(startR,startC);
            System.out.println(ans);
        }

    }

    private static void solve(int r,int c){
       Queue<Point> q=new ArrayDeque<>();
       visited[r][c]=true;
       q.offer(new Point(r,c,0));

       while(!q.isEmpty()){
           Point curr=q.poll();

           if(curr.r==goalR && curr.c==goalC)
               if (curr.cnt < ans) ans = curr.cnt;

           if(ans<curr.cnt) return;
           
           for(int d=0;d<8;d++){
               int nr=curr.r+dir[d][0];
               int nc=curr.c+dir[d][1];
               if(isOut(nr,nc) || visited[nr][nc]) continue;
               visited[nr][nc]=true;
               q.offer(new Point(nr,nc,curr.cnt+1));
           }
       }
    }

    private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=N || c>=N;
    }
}