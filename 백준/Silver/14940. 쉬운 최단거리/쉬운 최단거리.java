import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int R,C,map[][];
    static boolean[][] visited;
    static int[][] dir ={{-1,0},{1,0},{0,-1},{0,1}};
    static class Point{
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
        visited=new boolean[R][C];

        int startr=0;
        int startc=0;

        for(int i=0;i<R;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<C;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
                if(map[i][j]==1) map[i][j]=-2;
                if(map[i][j]==2){
                    startr=i;
                    startc=j;
                }
            }
        }

        bfs(startr,startc);

        StringBuilder sb= new StringBuilder();
        for(int[] a : map){
            for(int b : a){
                if(b==-2) sb.append(-1).append(" ");
                else sb.append(b).append(" ");
            }
            sb.append('\n');
        }

        System.out.println(sb.toString().trim());

    }

    private static void bfs(int r,int c){
        Queue<Point> q=new ArrayDeque<>();
        visited[r][c]=true;
        map[r][c]=0;
        q.offer(new Point(r,c));

        while(!q.isEmpty()){
            Point curr =q.poll();
            for(int d=0;d<4;d++){
                int nr =curr.r+dir[d][0];
                int nc =curr.c+dir[d][1];
                if(isOut(nr,nc)) continue;
                if(visited[nr][nc]) continue;
                if(map[nr][nc]==-2){
                    map[nr][nc]=map[curr.r][curr.c]+1;
                    visited[nr][nc]=true;
                    q.offer(new Point(nr,nc));
                }
            }
        }
    }

    private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=R || c>=C ;
    }
}