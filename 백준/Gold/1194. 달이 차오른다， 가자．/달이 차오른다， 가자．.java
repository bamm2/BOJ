import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static int ans = Integer.MAX_VALUE;
    static boolean[][][] visited;
    static char[][] map;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    static class Point {
        int r, c, key, cnt;

        public Point(int r, int c, int key, int cnt) {
            this.r = r;
            this.c = c;
            this.key = key;
            this.cnt = cnt;
        }
    }

    static Queue<Point> q = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[1 << 6][R][C];


        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j]=='0') {
                    q.offer(new Point(i, j, 0, 0));
                    visited[0][i][j] = true; // 시작 위치
                }
            }
        }

        bfs();

        if (ans==Integer.MAX_VALUE) ans = -1;

        System.out.println(ans);

    }

    private static void bfs() {

        while (!q.isEmpty()) {
            Point curr = q.poll();
            if (map[curr.r][curr.c]=='1') {
                ans = Math.min(ans, curr.cnt);
                continue;
            }
            for (int d = 0; d < 4; d++) {
                int nr = curr.r + dir[d][0];
                int nc = curr.c + dir[d][1];

                if (isOut(nr, nc) || visited[curr.key][nr][nc] || map[nr][nc]=='#') continue;

                if ('a' <= map[nr][nc] && map[nr][nc] <= 'z') {
                    int nk = 1 << (map[nr][nc] - 'a');
                    nk = nk | curr.key; // 기존 키에 현재 키를 추가 
                    visited[nk][nr][nc] = true;
                    q.offer(new Point(nr, nc, nk, curr.cnt + 1));
                } else if ('A' <= map[nr][nc] && map[nr][nc] <= 'Z') {
                    // 해당 키가 존재하는지 확인
                    if ((curr.key & 1 << (map[nr][nc] - 'A'))==(int) (Math.pow(2, map[nr][nc] - 'A'))) {
                        visited[curr.key][nr][nc] = true;
                        q.offer(new Point(nr, nc, curr.key, curr.cnt + 1));
                    }
                } else { // 출구 , 민석이 첫 위치, 빈칸
                    q.offer(new Point(nr, nc, curr.key, curr.cnt + 1));
                    visited[curr.key][nr][nc] = true;
                }
            }
        }
        
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }
}