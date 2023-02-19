import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point{
        int r,c,d;
        Point(int r,int c,int d){
            this.r=r;
            this.c=c;
            this.d=d;
        }
    }

    static int R,C,map[][];
    static int[][] dir={{-1,0},{0,1},{1,0},{0,-1}}; //북 , 동 , 남 , 서
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");
        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine()," ");
        int robotR=Integer.parseInt(st.nextToken());
        int robotC=Integer.parseInt(st.nextToken());
        int robotD=Integer.parseInt(st.nextToken());

        visited=new boolean[R][C];
        map=new int[R][C];
        for(int i=0;i<R;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<C;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        int[][] newmap=new int[R][C];
        Queue<Point> q =new ArrayDeque<>();
        q.offer(new Point(robotR,robotC,robotD));
        visited[robotR][robotC]=true;
        int ans=1; // 로봇청소기 청소한 공간 , 시작공간 먼저 청소하고 시작
        newmap[robotR][robotC]=ans;
        while(!q.isEmpty()){
            Point curr=q.poll();

            boolean sign=false;
            for(int d=3;d>=0;d--){
                int nr=curr.r+dir[(curr.d+d)%4][0];
                int nc=curr.c+dir[(curr.d+d)%4][1];
                int nd=(curr.d+d)%4;
                if(isOut(nr,nc)) continue;
                if(map[nr][nc]==1) continue;
                if(!visited[nr][nc] && map[nr][nc]==0){
                    visited[nr][nc]=true;
                    q.offer(new Point(nr,nc,nd));
                    sign=true;
                    ans++;
                    newmap[nr][nc]=ans;
                    break;
                }
            }
            if(!sign){
                int nr =curr.r+dir[(curr.d+2)%4][0];
                int nc =curr.c+dir[(curr.d+2)%4][1];
                int nd =curr.d;
                if(isOut(nr,nc) || map[nr][nc]==1) break;
                else q.offer(new Point(nr,nc,nd));
            }
        }
        System.out.println(ans);
    }

    private static boolean isOut(int r, int c){
        return r<0 || c<0 || r>=R || c>=C;
    }
}