import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int R, C, rLen, cLen;
    static boolean[][] map, visited;
    static int[] pos, goal;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        rLen = Integer.parseInt(st.nextToken());
        cLen = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map = new boolean[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
        }

        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            if (i==0) pos = new int[]{Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};
            else goal = new int[]{Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};
        }

        System.out.println(bfs());
        br.close();
    }

    private static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{pos[0], pos[1], 0});
        visited[pos[0]][pos[1]] =true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (curr[0]==goal[0] && curr[1]==goal[1]) {
                return curr[2];
            }
            for (int d = 0; d < 4; d++) {
                int nr = curr[0] + dir[d][0];
                int nc = curr[1] + dir[d][1];
                if (!isSatisfied(nr, nc) || visited[nr][nc]) continue;
                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc, curr[2] + 1});
            }
        }
        return -1;
    }

    private static boolean isSatisfied(int r, int c) {
        for (int i = r; i < r + rLen; i++) {
            for (int j = c; j < c + cLen; j++) {
                if (isOut(i, j) || map[i][j]) return false;
            }
        }
        return true;
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }
}
