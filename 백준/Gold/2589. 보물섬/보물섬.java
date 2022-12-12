import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C, map[][], ans;
    static boolean[][] visited;
    static int[][] dir={{1,0},{-1,0},{0,1},{0,-1}};

    static class Point {
        int r, c, cnt;

        Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                visited = new boolean[R][C];
                if (map[i][j] == 'L') {
                    bfs(i, j);
                }
            }
        }

        System.out.println(ans);
    }

    private static void bfs(int r, int c) {
        visited[r][c] = true;
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(r, c, 0));

        while (!q.isEmpty()) {
            Point curr = q.poll();
            if (curr.cnt > ans) ans = curr.cnt;
            for(int d=0;d<4;d++){
                int nr=curr.r+dir[d][0];
                int nc=curr.c+dir[d][1];
                if(isOut(nr,nc) || map[nr][nc]=='W') continue;
                if(visited[nr][nc]) continue;
                visited[nr][nc]=true;
                q.offer(new Point(nr,nc,curr.cnt+1));
            }
        }
    }

    private static boolean isOut(int r, int c) {
        return r<0 || c<0 || r>=R || c>=C ;
    }


}