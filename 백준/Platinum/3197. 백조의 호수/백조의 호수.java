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
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static char[][] map;
    static int[][] markingMap;
    static boolean[][] visited;
    static int[] parents;
    static List<int[]> candidates = new ArrayList<>();
    static int[] numbers = new int[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        markingMap = new int[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            char[] arr = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                map[i][j] = arr[j];
            }
        }

        int marker = 1;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j]=='X') markingMap[i][j] = -1;
                if (!visited[i][j] && map[i][j]!='X') {
                    init(i, j, marker++);
                }
            }
        }

        make(marker);
        System.out.println(result());

        br.close();
    }

    private static void init(int r, int c, int marker) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r, c});
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (map[curr[0]][curr[1]]=='L') {
                if (numbers[0]==0) numbers[0] = marker;
                else numbers[1] = marker;
            }
            markingMap[curr[0]][curr[1]] = marker;
            boolean flag = false;
            for (int d = 0; d < 4; d++) {
                int nr = curr[0] + dir[d][0];
                int nc = curr[1] + dir[d][1];
                if (isOut(nr, nc) || visited[nr][nc]) continue;
                visited[nr][nc] = true;
                if (map[nr][nc]=='X') {
                    flag = true;
                } else {
                    q.offer(new int[]{nr, nc});
                }
            }
            if (flag) candidates.add(curr);
        }
    }

    private static int result() {
        if (numbers[0]==numbers[1]) return 0;
        int cnt = 0;
        do {
            cnt++;
            broken();
        } while (!isSatisfied());

        return cnt;
    }

    private static void broken() {
        visited = new boolean[R][C];
        List<int[]> tmp = new ArrayList<>();

        for (int[] candidate : candidates) {
            int r = candidate[0];
            int c = candidate[1];
            visited[r][c] = true;
            for (int d = 0; d < 4; d++) {
                int nr = r + dir[d][0];
                int nc = c + dir[d][1];
                if (isOut(nr, nc) || visited[nr][nc] || markingMap[nr][nc]==markingMap[r][c]) continue;
                if (markingMap[nr][nc]==-1) {
                    markingMap[nr][nc] = markingMap[r][c];
                    if (isCandidate(nr, nc)) tmp.add(new int[]{nr, nc});
                } else { // 현재 무리와 다른 번호
                    if (union(markingMap[r][c], markingMap[nr][nc])) {
                        if (find(numbers[0])==find(numbers[1])) return;
                    }
                }
            }
        }
        candidates.clear();
        candidates.addAll(tmp);
    }

    private static boolean isCandidate(int r, int c) {
        boolean flag = false;
        for (int d = 0; d < 4; d++) {
            int nr = r + dir[d][0];
            int nc = c + dir[d][1];
            if (isOut(nr, nc) || visited[nr][nc]) continue;
            if (markingMap[nr][nc]==-1) {
                flag = true;
            } else {
                if (markingMap[r][c]!=markingMap[nr][nc]) {
                    union(markingMap[r][c], markingMap[nr][nc]);
                }
            }
        }
        return flag;
    }

    private static boolean isSatisfied() {
        return find(numbers[0])==find(numbers[1]);
    }

    private static void make(int len) {
        parents = new int[len];
        for (int i = 1; i < len; i++) {
            parents[i] = i;
        }
    }

    private static int find(int a) {
        if (parents[a]==a) return a;
        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot==bRoot) return false;
        if (aRoot > bRoot) {
            int tmp = aRoot;
            aRoot = bRoot;
            bRoot = tmp;
        }
        parents[bRoot] = aRoot;
        return true;
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }
}