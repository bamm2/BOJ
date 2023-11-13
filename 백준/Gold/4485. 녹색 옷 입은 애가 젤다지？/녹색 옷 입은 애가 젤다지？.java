import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int map[][], N, dist[][] , min;
    static String message = "Problem %d: %d";
    static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};

    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int round = 0;

        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N==0) break;
            min = Integer.MAX_VALUE;
            map = new int[N][N];
            dist = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }

            bfs();
            sb.append(String.format(message,++round,dist[N-1][N-1])).append('\n');
        }
        System.out.println(sb);
    }

    private static void bfs() {
        Queue<Point> q =new ArrayDeque<>();
        q.offer(new Point(0,0));
        dist[0][0]=map[0][0];

        while (!q.isEmpty()){
            Point curr = q.poll();
            for(int d=0;d<4;d++){
                int nr =curr.r+dir[d][0];
                int nc =curr.c+dir[d][1];
                if(isOut(nr,nc)) continue;
                if(dist[nr][nc] > dist[curr.r][curr.c]+map[nr][nc]){
                    dist[nr][nc] = dist[curr.r][curr.c]+map[nr][nc];
                    q.offer(new Point(nr,nc));
                }
            }
        }
    }

    private static boolean isOut(int r,int c){
        return r<0 || c<0 || r>=N || c>=N;
    }
}
