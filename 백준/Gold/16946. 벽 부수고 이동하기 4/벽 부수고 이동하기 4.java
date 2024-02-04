import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C, map[][];
    static Map<Integer, Integer> costs;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        costs = new HashMap<>();
        int landNumber = 2;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j]==0) bfs(i, j, landNumber++);
            }
        }

        HashSet<Integer> duplicateChecker;
        int cost;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j]!=1) sb.append(0);
                else {
                    duplicateChecker = new HashSet<>();
                    cost = 1;
                    for (int d = 0; d < 4; d++) {
                        int nr = i + dir[d][0];
                        int nc = j + dir[d][1];
                        if (isOut(nr, nc) || map[nr][nc]==1 || !duplicateChecker.add(map[nr][nc])) continue;
                        cost += costs.get(map[nr][nc]);
                    }
                    sb.append(cost%10);
                }
            }
            sb.append('\n');
        }

        System.out.println(sb);
        br.close();
    }

    private static void bfs(int r, int c, int landNumber) {
        int cnt = 1;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r, c});
        map[r][c] = landNumber;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = curr[0] + dir[d][0];
                int nc = curr[1] + dir[d][1];
                if (isOut(nr, nc) || map[nr][nc]!=0) continue;
                map[nr][nc] = landNumber;
                cnt++;
                q.offer(new int[]{nr, nc});
            }
        }

        costs.put(landNumber, cnt);
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }

}