import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static int N, M, map[][];
    static class Point{
        int r,c;
        Point(int r,int c){
            this.r=r;
            this.c=c;
        }
    }
    static List<Point>[] list;
    static List<Point> farSwitch;
    static boolean[][] visited;
    static boolean[][] light;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        visited=new boolean[N+1][N+1];
        light=new boolean[N+1][N+1];

        farSwitch=new ArrayList<>();

        list=new ArrayList[N*N+1];
        for(int i=1;i<=N*N;i++){
            list[i]=new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine()," ");
            int fromR= Integer.parseInt(st.nextToken());
            int fromC= Integer.parseInt(st.nextToken());
            int toR= Integer.parseInt(st.nextToken());
            int toC = Integer.parseInt(st.nextToken());
            int idx = (fromR-1)*N+fromC;
            list[idx].add(new Point(toR,toC));
        }
        light[1][1]=true;
        bfs(1,1);

        System.out.println(findAns());

    }

    private static void bfs(int r,int c){
        Queue<Point> q =new ArrayDeque<>();
        q.offer(new Point(r,c));
        visited[r][c]=true;
        while (!q.isEmpty()){
            Point curr = q.poll();

            int idx = (curr.r-1)*N+curr.c;
            for(Point next : list[idx]){
                light[next.r][next.c]=true;
                farSwitch.add(new Point(next.r, next.c));
            }
            for(int d=0;d<4;d++){
                int nr =curr.r+dir[d][0];
                int nc= curr.c+dir[d][1];
                if(isOut(nr,nc)) continue;
                if(!light[nr][nc]) continue;
                if(visited[nr][nc]) continue;
                visited[nr][nc]=true;
                q.offer(new Point(nr,nc));
            }
        }
        List<Point> tmp =new ArrayList<>();
        for(Point curr : farSwitch){
            if(visited[curr.r][curr.c]) continue;
            for(int d=0;d<4;d++){
                int nr =curr.r+dir[d][0];
                int nc =curr.c+dir[d][1];
                if(isOut(nr,nc)) continue;
                if(!visited[nr][nc]) continue;
                tmp.add(new Point(curr.r,curr.c));
            }
        }
        farSwitch.clear();
        for(Point curr : tmp){
            bfs(curr.r,curr.c);
        }
    }

    private static int findAns(){
        int ans =0;
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(light[i][j]) ans++;
            }
        }
        return ans ;
    }

    private static boolean isOut(int r, int c) {
        return r <= 0 || c <= 0 || r > N || c > N;
    }
}