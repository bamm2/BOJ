import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static char[][] map = new char[5][5];
    static boolean[][] visited = new boolean[5][5];
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            map[i] = br.readLine().toCharArray();
        }

        solve(0, 0, 0);
        System.out.println(cnt);
        br.close();
    }

    private static void solve(int idx, int start, int curr) {
        if (idx==7) {
            if (isSatisfied(curr / 5, curr % 5)) cnt++;
            return;
        }

        for (int i = start; i < 25; i++) {
            visited[i / 5][i % 5] = true;
            solve(idx + 1, i + 1, i);
            visited[i / 5][i % 5] = false;
        }
    }

    private static boolean isSatisfied(int r, int c) {
        int sCnt = map[r][c]=='S' ? 1:0;
        int cnt = 1;
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] check = new boolean[5][5];
        q.offer(new int[]{r, c});
        check[r][c]=true;

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = poll[0] + dir[d][0];
                int nc = poll[1] + dir[d][1];
                if (isOut(nr, nc) || !visited[nr][nc] || check[nr][nc]) continue;
                check[nr][nc] = true;
                if (map[nr][nc]=='S') sCnt++;
                cnt++;
                q.offer(new int[]{nr, nc});
            }
        }
        if (cnt!=7) return false;
        if (sCnt >= 4) return true;
        return false;
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= 5 || c >= 5;
    }
}