import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N ,map[][],copyMap[][],sum,bigThing;
    static int[][] dir ={{-1,0},{1,0},{0,1},{0,-1}};
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
        int n = Integer.parseInt(st.nextToken());
        int round = Integer.parseInt(st.nextToken());

        N=(int)Math.pow(2,n);
        map=new int[N][N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        st=new StringTokenizer(br.readLine()," ");
        while(round-->0){
            int range=(int)Math.pow(2,Integer.parseInt(st.nextToken()));
            turn(range);
            melt();
        }

        findAns();

        System.out.println(sum);
        System.out.println(bigThing);

    }

    private static void copyMap() {
        copyMap = new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                copyMap[i][j]=map[i][j];
            }
        }
    }

    private static void turn(int range) {
        copyMap();

        for(int i=0;i<N;i+=range){
            for(int j=0;j<N;j+=range){
                rotate(i,j,range);
            }
        }
    }

    private static void rotate(int r, int c,int range) {
        for(int i=0;i<range;i++){
            for(int j=0;j<range;j++){
                map[r+i][c+j]=copyMap[r+range-1-j][c+i];
            }
        }
    }

    private static void melt() {
        boolean[][] visited=new boolean[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j]==0) continue;
                visited[i][j]=true;
                int cnt =0 ;
                for(int d=0;d<4;d++) {
                    int nr = i + dir[d][0];
                    int nc = j + dir[d][1];
                    if(isOut(nr,nc)) continue;
                    if(map[nr][nc]>=1) cnt++;
                    else if(visited[nr][nc] && map[nr][nc]==0) cnt++;
                }
                if(cnt<3) map[i][j]--;
            }
        }
    }


    private static void findAns() {
        boolean[][] visited= new boolean[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(visited[i][j] || map[i][j]==0) continue;
                bfs(i,j,visited);
            }
        }
    }


    private static void bfs(int i, int j,boolean[][] visited) {
        Queue<Point> q =new ArrayDeque<>();
        q.offer(new Point(i,j));
        visited[i][j]=true;
        int cnt =0 ;
        while(!q.isEmpty()){
            Point curr = q.poll();
            sum+=map[curr.r][curr.c];
            cnt++;
            for(int d=0;d<4;d++){
                int nr =curr.r+dir[d][0];
                int nc= curr.c+dir[d][1];
                if(isOut(nr,nc) || map[nr][nc]==0 || visited[nr][nc] ) continue;
                visited[nr][nc]=true;
                q.offer(new Point(nr,nc));
            }
        }
        if(bigThing < cnt) bigThing=cnt;
    }

    private static boolean isOut(int r,int c){
        return r<0|| c<0|| r>=N || c>=N;
    }
}