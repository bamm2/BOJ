import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int R, C ,map[][];
    static int[][] dir = { {-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,-1},{1,1}};
    static   Queue<Point> q;
    static boolean[][] visited;
    static class Point{
        int r ,c ,cnt;
        Point(int r,int c,int cnt){
            this.r=r;
            this.c=c;
            this.cnt=cnt;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine()," ");

        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visited=new boolean[R][C];

        for(int i=0;i<R;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<C;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        q= new ArrayDeque<>();

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j]==1){
                   q.offer(new Point(i,j,0));
                }
            }
        }

        bfs();

        int ans = 0;
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(ans<map[i][j]) ans = map[i][j];
            }
        }

        System.out.println(ans);

    }

    private static void bfs(){

        while (!q.isEmpty()){
            int size =q.size();

            while(size-->0) {
                Point curr =q.poll();
                for (int d = 0; d < 8; d++) {
                    int nr = curr.r + dir[d][0];
                    int nc = curr.c + dir[d][1];
                    if (isOut(nr, nc)) continue;
                    if (map[nr][nc] == 1) continue;
                    if (visited[nr][nc]) continue;
                        map[nr][nc] = curr.cnt + 1;
                        visited[nr][nc] = true;
                        q.offer(new Point(nr, nc, curr.cnt + 1));
                }
            }
        }

    }

    private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=R || c>=C ;
    }
}