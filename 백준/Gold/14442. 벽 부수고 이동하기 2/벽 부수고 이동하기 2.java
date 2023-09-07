import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C, K, map[][];
    static int ans = Integer.MAX_VALUE;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static boolean[][][] visited;

    static class Point {
        int r, c, cnt, broken;

        Point(int r, int c, int cnt, int broken) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.broken = broken;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visited = new boolean[K + 1][R][C];


        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        bfs();
        if(ans==Integer.MAX_VALUE) ans =-1;
        System.out.println(ans);
    }

    private static void bfs() {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(0, 0, 1, 0));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Point curr = q.poll();
            if(curr.r==R-1 && curr.c==C-1){
                ans=Math.min(ans,curr.cnt);
            }
            for (int d = 0; d < 4; d++) {
                int nr = curr.r + dir[d][0];
                int nc = curr.c + dir[d][1];

                if(isOut(nr,nc) || visited[curr.broken][nr][nc]) continue;
                if(map[nr][nc]==1){
                    if(curr.broken+1>K) continue;
                    if(visited[curr.broken+1][nr][nc]) continue;
                    q.offer(new Point(nr,nc,curr.cnt+1,curr.broken+1));
                    visited[curr.broken+1][nr][nc]=true;
                }else{
                    q.offer(new Point(nr,nc,curr.cnt+1,curr.broken));
                    visited[curr.broken][nr][nc]=true;
                }
            }

        }
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }
}