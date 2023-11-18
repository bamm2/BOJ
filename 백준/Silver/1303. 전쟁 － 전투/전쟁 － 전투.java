import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static int[] sum = new int[2];
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine(), " ");
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (visited[i][j]) continue;
                bfs(i, j, map[i][j]);
            }
        }

        System.out.println(sum[0] + " " + sum[1]);
    }

    private static void bfs(int r, int c, char find) {
        Queue<int[]> q = new ArrayDeque<>();
        visited[r][c] = true;
        q.offer(new int[]{r, c});
        int count = 1;

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = poll[0] + dir[d][0];
                int nc = poll[1] + dir[d][1];
                if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc]!=find) continue;
                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc});
                count++;
            }
        }

        if (find=='W') {
            sum[0] += (int) Math.pow(count, 2);
        } else {
            sum[1] += (int) Math.pow(count, 2);
        }
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }
}