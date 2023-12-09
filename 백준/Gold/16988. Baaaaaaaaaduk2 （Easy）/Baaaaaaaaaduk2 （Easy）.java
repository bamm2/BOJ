import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C, map[][], ans;
    static boolean[][] visited;
    static Point[] selected = new Point[2];
    static List<Point> list = new ArrayList<>();
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j]==0) list.add(new Point(i, j));
            }
        }

        comb(0, 0);

        System.out.println(ans);
    }

    private static void comb(int start, int idx) {
        if (idx==2) {
            change(1);
            search();
            change(0);
            return;
        }

        for (int i = start; i < list.size(); i++) {
            selected[idx] = list.get(i);
            comb(i + 1, idx + 1);
        }
    }

    private static void change(int flag) {
        for (int i = 0; i < selected.length; i++) {
            Point point = selected[i];
            map[point.r][point.c] = flag;
        }
    }

    private static void search() {
        visited = new boolean[R][C];
        int count = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (visited[i][j] || map[i][j]!=2) continue;
                count+=bfs(i,j);
            }
        }
        ans = Math.max(count,ans);
    }

    private static int bfs(int r, int c) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(r, c));
        visited[r][c] = true;
        int cnt = 1;
        boolean flag = false;
        while (!q.isEmpty()) {
            Point curr = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = curr.r + dir[d][0];
                int nc = curr.c + dir[d][1];
                if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc]==1) continue;
                if (map[nr][nc]==0) {
                    flag=true;
                    continue;
                }
                cnt++;
                visited[nr][nc] = true;
                q.offer(new Point(nr, nc));
            }
        }

        if(flag) return 0;
        return cnt;
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }
}