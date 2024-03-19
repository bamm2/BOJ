import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static List<int[]> candidates;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            char[] arr = br.readLine().toCharArray();
            System.arraycopy(arr, 0, map[i], 0, C);
        }

        int T = Integer.parseInt(br.readLine());
        int cnt = 0;
        st = new StringTokenizer(br.readLine(), " ");
        while (cnt++ < T) {
            visited = new boolean[R][C];
            int num = Integer.parseInt(st.nextToken());
            goThrow(num, cnt % 2==1); //  true -> 왼 시작 , false -> 오 시작
        }

        result();
        br.close();
    }

    private static void goThrow(int height, boolean isLeft) {
        int rIdx = R - height; // 막대기를 던질 높이 인덱스
        int cIdx;
        if (isLeft) { // 왼쪽에서 오른쪽으로 막대기 던지기
            cIdx = 0;
            while (cIdx < C) {
                if (isMineral(rIdx, cIdx)) {
                    // 오른쪽,위 방향이랑 아래방향이랑 같은 클러스터인지 확인
                    divideCluster(rIdx, cIdx, true); // 분리체크
                    down();

                    return;
                }
                cIdx++;
            }

        } else {
            // 오른족에서 왼쪽으로 막대기 던지기
            cIdx = C - 1;
            while (cIdx >= 0) {
                if (isMineral(rIdx, cIdx)) {
                    // 왼쪽,위 방향이랑 아래방향이랑 같은 클러스터인지 확인
                    divideCluster(rIdx, cIdx, false);   // 분리 체크
                    down();

                    return;
                }
                cIdx--;
            }
        }
    }

    private static boolean isMineral(int r, int c) {
        if (map[r][c]=='x') {
            map[r][c] = '.';
            return true;
        }
        return false;
    }

    private static void divideCluster(int r, int c, boolean isLeft) {
        candidates = new ArrayList<>();
        int[][] direction;
        if (isLeft) direction = new int[][]{{-1, 0}, {0, 1}, {1, 0}}; // 왼쪽 출발
        else direction = new int[][]{{-1, 0}, {0, -1}, {1, 0}};  // 오른쪽 출발

        for(int d=0;d<3;d++){
            int nr = r+direction[d][0];
            int nc = c+direction[d][1];
            bfs(nr,nc);
        }
    }

    private static void bfs(int r, int c) {
        if (isOut(r, c) || map[r][c]=='.' || visited[r][c]) return;

        List<int[]> tmp = new ArrayList<>(); // 후보 체크
        Queue<int[]> q = new ArrayDeque<>();
        visited[r][c] = true;
        tmp.add(new int[]{r, c});
        q.offer(new int[]{r, c});

        boolean isRoot = false; // 바닥과 연결되었는지
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = curr[0] + dir[d][0];
                int nc = curr[1] + dir[d][1];
                if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc]=='.') continue;
                visited[nr][nc] = true;
                tmp.add(new int[]{nr, nc});
                q.offer(new int[]{nr, nc});
                if (nr==R - 1) isRoot = true;
            }
        }

        if (!isRoot) candidates.addAll(tmp); // 바닥과 연결되지 않았다면 내려갈 가능성있는 후보
    }

    private static void down() {
        if (candidates.isEmpty()) return; // 중력에 영향받을 후보가 없다면 반환

        visited = new boolean[R][C];
        for (int[] candidate : candidates) { // 후보들 시작 위치 체크
            visited[candidate[0]][candidate[1]] = true;
        }

        int moveDistance = 0;
        boolean flag = false;
        while (true) {
            moveDistance++;
            for (int[] candidate : candidates) {
                int nr = candidate[0] + moveDistance;
                int c = candidate[1];
                if (nr==R || !visited[nr][c] && map[nr][c]=='x') { // 후보 위치가 아니면서 x 를 만나거나 바닥까지 간 경우
                    flag = true;
                    break;
                }
            }
            if (flag) {
                moveDistance--;
                setting(moveDistance);
                return;
            }
        }
    }

    private static void setting(int moveDistance) {
        for (int[] candidate : candidates) {
            map[candidate[0]][candidate[1]] = '.';
        }

        for (int[] candidate : candidates) {
            map[candidate[0] + moveDistance][candidate[1]] = 'x';
        }
    }

    private static void result() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(map[i][j]);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }

}