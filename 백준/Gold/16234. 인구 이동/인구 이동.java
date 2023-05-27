import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N,L,R,map[][];
    static int[][] dir = { {-1,0},{1,0},{0,1},{0,-1} };
    static boolean[][] visited;
    static boolean going;
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
        N=Integer.parseInt(st.nextToken()); // N*N
        L =Integer.parseInt(st.nextToken()); // 차이가 L 이상
        R =Integer.parseInt(st.nextToken()); // 차이가 R 이하

        map=new int[N][N];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<N;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;

        while(true) {
            going=false;
            visited=new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]) continue;
                    bfs(i, j);
                }
            }
           if(!going) break; // 이동못하면 종료
            ans++;
        }

        System.out.println(ans);
    }

    private static void bfs(int r,int c){
        int sum = 0 ;
        int land = 0 ;
        Queue<Point> q=new ArrayDeque<>();
        List<Point> UnionCountry = new ArrayList<>();
        q.offer(new Point(r,c));
        visited[r][c]=true;
        sum+=map[r][c];
        land++;
        UnionCountry.add(new Point(r,c));

        while(!q.isEmpty()){
            Point curr =q.poll();
            for(int d=0;d<4;d++){
                int nr =curr.r+dir[d][0];
                int nc =curr.c+dir[d][1];
                if(isOut(nr,nc)) continue;
                if(visited[nr][nc]) continue;
                if(isMoving(curr.r,curr.c,nr,nc)){
                    going=true;
                    q.offer(new Point(nr,nc));
                    sum+=map[nr][nc];
                    land++;
                    UnionCountry.add(new Point(nr,nc));
                    visited[nr][nc]=true;
                }
            }
        }

            int peopleCnt = sum / land;
            for (Point align : UnionCountry) {
                map[align.r][align.c] = peopleCnt;
            }
    }

    private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=N || c>=N;
    }
    private static boolean isMoving(int curR,int curC,int nextR,int nextC){
        int diff =Math.abs(map[curR][curC]-map[nextR][nextC]);
        return L<=diff && diff<=R;
    }
}