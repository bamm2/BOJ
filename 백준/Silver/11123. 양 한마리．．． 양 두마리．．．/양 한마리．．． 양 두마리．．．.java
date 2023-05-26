import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static boolean visited[][];
    static int R,C;
    static char[][] map;
    static class Point{
        int r,c;
        Point(int r,int c){
            this.r=r;
            this.c=c;
        }
    }
    static int[][] dir ={{-1,0},{1,0},{0,1},{0,-1}};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        StringBuilder sb =new StringBuilder();

        int N=Integer.parseInt(br.readLine());

        while(N-->0){
            st=new StringTokenizer(br.readLine()," ");
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            int cnt=0;
            visited=new boolean[R][C];
            map=new char[R][C];
            for(int i=0;i<R;i++){
                String s = br.readLine();
                for(int j=0;j<C;j++){
                    map[i][j]=s.charAt(j);
                }
            }

            for(int i=0;i<R;i++){
                for(int j=0;j<C;j++){
                    if(map[i][j]=='#' && !visited[i][j]){
                            bfs(i,j);
                            cnt++;
                    }
                }
            }
            sb.append(cnt).append('\n');

        }

        System.out.println(sb.toString().trim());

    }
    private static void bfs(int r ,int c){
        Queue<Point> q =new ArrayDeque<>();
        q.offer(new Point(r,c));
        visited[r][c]=true;

        while(!q.isEmpty()){
            Point curr =q.poll();
            for(int d=0;d<4;d++){
                int nr =curr.r+dir[d][0];
                int nc =curr.c+dir[d][1];
                if(isOut(nr,nc)) continue;
                if(visited[nr][nc]) continue;
                if(map[nr][nc]=='.') continue;
                visited[nr][nc]=true;
                q.offer(new Point(nr,nc));
            }
        }

    }
    private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=R || c>=C;
    }
}