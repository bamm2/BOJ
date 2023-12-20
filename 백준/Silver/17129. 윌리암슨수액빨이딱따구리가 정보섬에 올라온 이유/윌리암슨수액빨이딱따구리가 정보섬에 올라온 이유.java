import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C, map[][];
    static boolean[][] visited;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        visited = new boolean[R][C];

        Queue<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j) - '0';
                if (map[i][j]==2) {
                    q.offer(new int[]{i, j, 0});
                    visited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (find(curr[0],curr[1])) {
                System.out.println("TAK");
                System.out.println(curr[2]);
                return;
            }
            for (int d = 0; d < 4; d++) {
                int nr = curr[0] + dir[d][0];
                int nc = curr[1] + dir[d][1];
                if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc]==1) continue;
                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc, curr[2] + 1});
            }
        }

        System.out.println("NIE");

    }

    private static boolean find(int r, int c) {
        return map[r][c]!=2 && map[r][c]!=0;
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }
}