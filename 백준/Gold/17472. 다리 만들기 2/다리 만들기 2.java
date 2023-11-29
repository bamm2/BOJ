import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C, map[][];
    static boolean[][] visited;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static int[] parents;

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Island implements Comparable<Island> {
        int from, to, w;

        public Island(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Island o) {
            return Integer.compare(this.w, o.w);
        }
    }

    static PriorityQueue<Island> pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        visited = new boolean[R][C];
        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j]==1) map[i][j] = -1;
            }
        }

        int islandNumber = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j]==-1 && !visited[i][j]) {
                    bfs(i, j, ++islandNumber);
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j]==0) continue;
                searchBridge(i, j, map[i][j]);
            }
        }

        make(islandNumber + 1);

        int sum = 0;
        int pick = 1;
        while (!pq.isEmpty()) {
            Island curr = pq.poll();
            if (union(curr.from, curr.to)) {
                sum += curr.w;
                pick++;
            }
            if (pick==islandNumber) break;
        }

        if (pick!=islandNumber) sum = -1;
        System.out.println(sum);

    }

    private static void make(int size) {
        parents = new int[size];
        for (int i = 0; i < size; i++) {
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

    private static void searchBridge(int r, int c, int islandNumber) {

        for (int d = 0; d < 4; d++) {
            int count = 0;
            int plusR = dir[d][0];
            int plusC = dir[d][1];
            int nr = r + plusR;
            int nc = c + plusC;
            while (true) {
                if (isOut(nr, nc) || map[nr][nc]==islandNumber) break; // 땅이 자기 번호 일 경우
                if (map[nr][nc]==0) { // 땅이 0인 경우
                    count++;
                    nr += plusR;
                    nc += plusC;
                } else { // 0 과 자기 땅이 아닌 경우
                    if (count!=1) pq.offer(new Island(islandNumber, map[nr][nc], count));
                    break;
                }
            }
        }

    }

    private static void bfs(int r, int c, int landNumber) {
        Queue<Point> q = new ArrayDeque<>();
        visited[r][c] = true;
        map[r][c] = landNumber;
        q.offer(new Point(r, c));

        while (!q.isEmpty()) {
            Point curr = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = curr.r + dir[d][0];
                int nc = curr.c + dir[d][1];
                if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc]==0) continue;
                map[nr][nc] = landNumber;
                visited[nr][nc] = true;
                q.offer(new Point(nr, nc));
            }
        }
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }
}