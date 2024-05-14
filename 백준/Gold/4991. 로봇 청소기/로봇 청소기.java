import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static int R, C, min, size;
    static char[][] map;
    static int[][] dist;
    static List<int[]> points;
    static int[] start, pos;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            if (R==0 && C==0) break;

            flag = false;
            map = new char[R][C];
            points = new ArrayList<>();

            char index = '1';
            for (int i = 0; i < R; i++) {
                String str = br.readLine();
                for (int j = 0; j < C; j++) {
                    char curr = str.charAt(j);
                    if (curr=='o') start = new int[]{i, j};
                    else if (curr=='*') {
                        curr = index++;
                        points.add(new int[]{i, j});
                    }
                    map[i][j] = curr;
                }
            }

            size = points.size();
            dist = new int[size + 1][size + 1]; // 0번인덱스 시작점
            for (int i = 0; i <= size; i++) {
                initDist(i);
                if (flag) break;
            }

            if (flag) {
                sb.append(-1).append('\n');
                continue;
            }

            min = Integer.MAX_VALUE;
            pos = new int[size];
            perm(0, 0);
            sb.append(min).append('\n');
        }
        System.out.println(sb);

        br.close();
    }

    private static void initDist(int v) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[R][C];
        if (v==0) {
            q.offer(start);
            visited[start[0]][start[1]] = true;
        } else {
            int[] dustPos = points.get(v - 1);
            q.offer(dustPos);
            visited[dustPos[0]][dustPos[1]] = true;
        }

        int meetCnt = v==0 ? 0:1;
        int moveCnt = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            moveCnt++;
            while (size-- > 0) {
                int[] curr = q.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = curr[0] + dir[d][0];
                    int nc = curr[1] + dir[d][1];
                    if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc]=='x') continue;
                    visited[nr][nc] = true;
                    if (isDust(map[nr][nc])) {
                        ++meetCnt;
                        dist[v][map[nr][nc] - '0'] = moveCnt;
                    }
                    q.offer(new int[]{nr, nc});
                    if (meetCnt==points.size()) return;
                }
            }
        }
        flag = true;
    }

    private static void perm(int idx, int flag) {
        if (idx==size) {
            findDistance();
            return;
        }

        for (int i = 0; i < size; i++) {
            if ((flag & (1 << i))!=0) continue;
            pos[idx] = i;
            perm(idx + 1, flag | (1 << i));
        }
    }

    private static void findDistance() {
        int from = 0;
        int sum = 0;
        for (int i = 0; i < size; i++) { // pos[i]: points 인덱스 번호
            int to = pos[i] + 1; // dist 0번은 시작점이여서 points 인덱스 0번이 먼지 1번
            sum += dist[from][to];
            from = to;
            if (sum >= min) return;
        }
        min = sum;
    }

    private static boolean isDust(char c) {
        return '1' <= c && c <= '9';
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }
}
