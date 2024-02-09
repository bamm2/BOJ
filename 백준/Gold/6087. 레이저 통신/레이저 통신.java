import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static char[][] map;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    static class Point implements Comparable<Point> {
        int r, c, cnt, dir;

        public Point(int r, int c, int cnt, int dir) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.dir = dir;
        }

        public int compareTo(Point o) {
            return Integer.compare(this.cnt, o.cnt);
        }
    }

    static int ans = Integer.MAX_VALUE;
    static Point from;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j]=='C' && from==null) {
                    map[i][j] = 'X';
                    from = new Point(i, j, -1, -1);
                }
            }
        }

        bfs();

        System.out.println(ans);
        br.close();
    }

    private static void bfs() {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        int[][][] visited = new int[4][R][C];
        for(int i=0;i<4;i++){
            for(int j=0;j<R;j++){
                for(int k=0;k<C;k++){
                    visited[i][j][k]=Integer.MAX_VALUE;
                }
            }
        }
        pq.offer(from);

        while (!pq.isEmpty()) {
            Point curr = pq.poll();
            if (ans <= curr.cnt) continue;
            if (map[curr.r][curr.c]=='C') {
                ans = Math.min(ans, curr.cnt);
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nr = curr.r + dir[d][0];
                int nc = curr.c + dir[d][1];
                if (isOut(nr, nc) || map[nr][nc]=='*') continue;
                if(d==curr.dir){
                    if(visited[d][nr][nc] <= curr.cnt) continue;
                    visited[d][nr][nc] = curr.cnt;
                    pq.offer(new Point(nr,nc,curr.cnt,d));
                }else{
                    if(visited[d][nr][nc] <= curr.cnt+1) continue;
                    visited[d][nr][nc]= curr.cnt+1;
                    pq.offer(new Point(nr,nc,curr.cnt+1,d));
                }
            }
        }
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }
}