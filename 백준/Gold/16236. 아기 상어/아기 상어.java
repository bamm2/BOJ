import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, map[][], ans, sharkSize, sharkEatCnt;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static boolean[][] visited;

    static class Shark implements Comparable<Shark> {
        int r, c;

        Shark(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Shark o) {
            if (this.r == o.r) {
                return Integer.compare(this.c, o.c);
            } else {
                return Integer.compare(this.r, o.r);
            }
        }
    }

    static Queue<Shark> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];
        q = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    map[i][j] = 0;
                    q.offer(new Shark(i, j));
                }
            }
        }

        start();

        System.out.println(ans);
    }

    private static void start() {

        sharkSize = 2;
        sharkEatCnt = 0;

        while (hunt()) {
            visited = new boolean[N][N];
        }

    }

    private static boolean hunt() {

        List<Shark> minDisList = new ArrayList<>();
        int distance = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            distance++;
            while (size-- > 0) {
                Shark curr = q.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = curr.r + dir[d][0];
                    int nc = curr.c + dir[d][1];
                    if (isOut(nr, nc) || visited[nr][nc] || map[nr][nc] > sharkSize) continue;
                    visited[nr][nc] = true;
                    q.offer(new Shark(nr, nc));
                    if (map[nr][nc] != 0 && map[nr][nc] < sharkSize) minDisList.add(new Shark(nr, nc));
                }
            }
            if (minDisList.size() != 0) break;
        }


        if (minDisList.size() == 0) return false;

        Collections.sort(minDisList);
        Shark shark = minDisList.get(0);
        sharkEatCnt++;
        if (sharkEatCnt == sharkSize) {
            sharkSize++;
            sharkEatCnt = 0;
        }
        ans += distance;
        q.clear();
        q.offer(new Shark(shark.r, shark.c));
        map[shark.r][shark.c] = 0;

        return true;
    }

    private static boolean isOut(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= N;
    }
}